# SauceDemo Automated Test Suite (Selenium + Java)

Automated Positive and Negative UI test suite for [saucedemo.com](https://www.saucedemo.com/), built with **Selenium WebDriver + Java + TestNG**, using the Page Object Model. 22 tests total — 12 positive, 10 negative — automating the manual test case sheet 1:1. Runs automatically on every push via GitHub Actions.

## Framework Choice + Why

**Selenium WebDriver + Java + TestNG**, with WebDriverManager for driver binaries and the Page Object Model for structure.

- **Industry default for Java shops.** Selenium is the most widely required automation tool in QA job descriptions, and pairs naturally with Java, the language most enterprise test suites are already written in — this suite slots straight into that ecosystem rather than asking a team to adopt a new stack.
- **Mature, broad browser/OS support**, including older browser versions and Selenium Grid for distributed execution across real machines/VMs — useful when a target audience still includes environments a newer, Chromium-first tool doesn't prioritize.
- **TestNG gives structure Selenium doesn't provide on its own** — annotations (`@BeforeMethod`/`@AfterMethod`), assertions, parallel execution (`parallel="classes"` in `testng.xml`), and suite XML configuration. Selenium is only the browser driver; TestNG is what turns raw browser automation into an organized test suite.
- **WebDriverManager** removes the historically painful part of Selenium setup — matching a `chromedriver` binary version to the installed browser — by resolving and downloading the right driver automatically at runtime.
- **Trade-off, honestly:** Selenium has no built-in auto-waiting (every interaction needs an explicit wait, handled here centrally in `BasePage`) and no built-in parallel-safe test runner (handled here via `ThreadLocal<WebDriver>` in `DriverManager`) — both of which a newer tool like Playwright provides out of the box. See the companion [Playwright/TypeScript suite](../saucedemo-playwright) in this portfolio for that comparison; the same 22 scenarios are automated there too.

## Project Structure

```
saucedemo-selenium-java/
├── pom.xml
├── testng.xml
├── src/
│   ├── main/java/com/framework/
│   │   ├── base/DriverManager.java       # Thread-safe WebDriver lifecycle
│   │   ├── config/ConfigReader.java      # Reads config.properties, supports -D overrides
│   │   └── pages/                        # Page Object Model — one class per screen
│   └── test/java/com/framework/
│       ├── data/TestData.java            # Shared users, product names, expected errors
│       ├── tests/
│       │   ├── BaseTest.java             # Fresh browser per test + login helper
│       │   ├── positive/                 # 12 tests
│       │   └── negative/                 # 10 tests
│       └── listeners/TestListener.java   # Logs results + screenshots on failure
└── .github/workflows/selenium-tests.yml
```

## Running Locally

```bash
mvn clean test                    # headless=false by default (watch it run)
mvn clean test -Dheadless=true    # headless, e.g. for CI parity
mvn clean test -Dbrowser=firefox  # switch browser
```

TestNG's HTML report and the emailable summary are generated in `test-output/` after each run.

## CI

Every push runs the full suite headless on `ubuntu-latest` (Chrome ships preinstalled on the runner) via GitHub Actions. The TestNG report and, on failure, screenshots are uploaded as workflow artifacts.

**Reporting**
- Current: TestNG's built-in HTML/emailable report plus Log4j2 logs and automatic failure screenshots, all uploaded as CI artifacts.
- Next: swap in **ExtentReports** or **Allure** for a richer, stakeholder-friendly report — timeline view, pass/fail trend across runs, embedded screenshots inline rather than as separate files.
- Publish the report to **GitHub Pages** on `main` so the latest run is a stable link instead of a downloadable artifact.
- Add a Slack/Teams webhook step on failure so a broken `main` build pages the team instead of waiting to be noticed.

## Manual Test Case Reference

Test descriptions (`@Test(description = "TC_POS_01: ...")`) map directly to the manual test case sheet this suite automates, for traceability between manual and automated coverage.

import com.microsoft.playwright.*
import org.testng.annotations.AfterSuite
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeSuite
import org.testng.annotations.BeforeTest

open class BaseTest {

    lateinit var playwright: Playwright
    lateinit var browser: Browser
    lateinit var context: BrowserContext
    lateinit var page: Page

    @BeforeSuite
    fun launchBrowser() {
        playwright = Playwright.create()
        browser = playwright.chromium().launch(BrowserType.LaunchOptions().setHeadless(true))
    }

    @AfterSuite
    fun closeBrowser() {
        playwright.close()
    }

    @BeforeTest
    fun createContextAndPage() {
        context = browser.newContext()
        page = context.newPage()
    }

    @AfterTest
    fun closeContext() {
        context.close()
    }
}
import com.microsoft.playwright.*
import com.microsoft.playwright.Tracing.StartOptions
import com.microsoft.playwright.Tracing.StopOptions
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.AriaRole
import org.testng.annotations.*
import java.nio.file.Paths
import kotlin.test.assertEquals


class FirstTests {

    lateinit var playwright: Playwright
    lateinit var browser: Browser
    lateinit var context: BrowserContext
    lateinit var page: Page

    @BeforeSuite
    fun launchBrowser() {
        playwright = Playwright.create()
        browser = playwright.chromium().launch(BrowserType.LaunchOptions().setHeadless(false))
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

    @Test
    fun firstTest() {
        context.tracing().start(
            StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true)
        )

        page.navigate("https://playwright.dev")
        assertEquals("Fast and reliable end-to-end testing for modern web apps | Playwright", page.title() )
        val getStarted: Locator? = page.getByRole(AriaRole.LINK, Page.GetByRoleOptions().setName("Get Started"))
        assertThat(getStarted).hasAttribute("href", "/docs/intro");
        getStarted?.click()
        assertThat(page.getByRole(AriaRole.LINK, Page.GetByRoleOptions().setName("Installation"))).isVisible()

        context.tracing().stop(
            StopOptions()
                .setPath(Paths.get("trace.zip"))
        )
    }

    @Test
    fun shouldClickButton() {

    }
}
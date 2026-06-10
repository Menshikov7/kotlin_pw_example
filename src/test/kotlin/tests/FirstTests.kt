package tests

import BaseTest
import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.AriaRole
import org.testng.annotations.Test
import kotlin.test.assertEquals

class FirstTests : BaseTest() {

    @Test
    fun firstTest() {
        page.navigate("https://playwright.dev")
        assertEquals("Fast and reliable end-to-end testing for modern web apps | Playwright", page.title())
        val getStarted: Locator? = page.getByRole(AriaRole.LINK, Page.GetByRoleOptions().setName("Get Started"))
        assertThat(getStarted).hasAttribute("href", "/docs/intro")
        getStarted?.click()
        assertThat(page.getByRole(AriaRole.LINK, Page.GetByRoleOptions().setName("Installation"))).isVisible()
    }
}
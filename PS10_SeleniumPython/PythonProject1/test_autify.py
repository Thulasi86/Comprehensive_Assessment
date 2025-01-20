import pytest
from selenium import webdriver
from selenium.webdriver import ActionChains
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


# Setup for WebDriver
@pytest.fixture
def setup():
    # Initialize the Chrome WebDriver using WebDriver Manager to handle the ChromeDriver
    driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))
    driver.maximize_window()
    driver.get("https://autify.com/")
    yield driver  # This ensures the driver quits after tests are run
    driver.quit()


# Test to verify Autify logo presence
def test_autify_logo(setup):
    driver = setup
    language_dropdown_xpath = "//*[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']"

    # Wait for the dropdown button to be visible and clickable
    language_dropdown_button = WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.XPATH, language_dropdown_xpath))
    )

    # Click the language dropdown button
    language_dropdown_button.click()
    # XPath for the Autify logo (located in the top left corner of the page)
    logo_xpath = "//*[@alt='Autify Menu Logo Icon Image']"

    # Find the logo element using XPath
    logo = driver.find_element(By.XPATH, logo_xpath)

    # Assert that the logo is displayed
    assert logo.is_displayed(), "Autify logo is not displayed."


def test_hover_language_dropdown(setup):
    driver = setup

    # XPath for the language dropdown button (assumed to be "English")
    language_dropdown_xpath = "//*[@id='w-dropdown-toggle-1']/div[1]"

    # Wait for the language dropdown button to be visible
    language_dropdown_button = WebDriverWait(driver, 10).until(
        EC.visibility_of_element_located((By.XPATH, language_dropdown_xpath))
    )

    # Create an ActionChain instance to simulate hovering over the language dropdown button
    actions = ActionChains(driver)

    # Hover over the language dropdown to display the language options
    actions.move_to_element(language_dropdown_button).perform()

    # Wait for the language options to appear (the dropdown should be visible now)
    japanese_language_xpath = "//*[@id='w-dropdown-list-1']/a/div"

    WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.XPATH, japanese_language_xpath))
    )

    # Assert that the Japanese language option is visible
    assert driver.find_element(By.XPATH, japanese_language_xpath).is_displayed(), "Japanese option not found."

    # Click on the Japanese language option
    driver.find_element(By.XPATH, japanese_language_xpath).click()
    # Wait for a new tab to open (this is assuming that clicking the language will open a new tab)
    WebDriverWait(driver, 10).until(
        lambda driver: len(driver.window_handles) > 1  # Wait until a new window/tab is opened
    )

    # Switch to the new tab
    new_tab = driver.window_handles[1]  # Get the handle for the new tab
    driver.switch_to.window(new_tab)  # Switch to the new tab

    # Wait for the page to reload and verify the page language change
    WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.XPATH, "(//*[contains(text(), '料金')])[1]"))
        # Checking for Japanese text
    )

    # Assert the page language has changed to Japanese
    assert "料金" in driver.page_source, "Page language did not change successfully to Japanese."


# Run the tests using pytest
if __name__ == "__main__":
    pytest.main()

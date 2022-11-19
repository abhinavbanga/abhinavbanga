from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from pages.Page import Page
class OverviewPage(Page):

    def __init__(self, driver):
        super().__init__(driver)
        self.cancel_button_selector = (By.CLASS_NAME, "cart_cancel_link")
        self.finish_button_selector = (
            By.CLASS_NAME, "btn_action"
        )
        self.subheader_selector = (By.CLASS_NAME, 'subheader')
        self.subtotal_selector = (By.CLASS_NAME, "summary_subtotal_label")

    def get_subheader(self):
        element = self.driver.find_element(*self.subheader_selector)
        return element.text

    def click_cancel(self):
        self.click_element(self.cancel_button_selector)
        WebDriverWait(self.driver, 5).until(EC.url_changes)

    def click_finish(self):
        self.click_element(self.finish_button_selector)
        WebDriverWait(self.driver, 5).until(EC.url_changes)

    def get_subtotal(self):
        price_text = self.driver.find_element(*self.subtotal_selector).text
        # Ignore the first 13 characters. i.e. Ignore: "Item total: $"
        return float(price_text[13:])

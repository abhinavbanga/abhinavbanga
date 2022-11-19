from selenium.webdriver.common.by import By
from pages.Page import Page
class CheckoutPage(Page):

    def __init__(self, driver):
        super().__init__(driver)
        self.firstname_textbox_selector = (By.ID, "first-name")
        self.lastname_textbox_selector = (By.ID, "last-name")
        self.postalcode_textbox_selector = (By.ID, "postal-code")
        self.cancel_button_selector = (By.CLASS_NAME, "cart_cancel_link")
        self.continue_button_selector = (By.CLASS_NAME, "btn_primary")
        self.error_message_selector = (By.CLASS_NAME, "error-button")
        self.subheader_selector = (By.CLASS_NAME, 'subheader')

    def input_first_name(self, name):
        self.send_keys_to_element(self.firstname_textbox_selector, name)

    def input_last_name(self, name):
        self.send_keys_to_element(self.lastname_textbox_selector, name)

    def input_postal_code(self, postal_code):
        self.send_keys_to_element(self.postalcode_textbox_selector, postal_code)

    def input_payment_details(self):
        pass

    def get_first_name(self):
        element = self.driver.find_element(*self.firstname_textbox_selector)
        return element.get_attribute("value")

    def get_last_name(self):
        element = self.driver.find_element(*self.lastname_textbox_selector)
        return element.get_attribute("value")

    def get_postal_code(self):
        element = self.driver.find_element(*self.postalcode_textbox_selector)
        return element.get_attribute("value")

    def is_error_message_present(self):
        error_message = self.driver.find_elements(*self.error_message_selector)
        return len(error_message) > 0

    def click_cancel(self):
        self.click_element(self.cancel_button_selector)

    def click_continue(self):
        self.click_element(self.continue_button_selector)

    def get_subheader(self):
        element = self.driver.find_element(*self.subheader_selector)
        return element.text

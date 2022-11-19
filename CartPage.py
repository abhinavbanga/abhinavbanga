from selenium.webdriver.common.by import By
from pages.Page import Page
class CartPage(Page):

    def __init__(self, driver):
        super().__init__(driver)
        self.continue_shopping_button_selector = (By.CLASS_NAME, "btn_secondary")
        self.checkout_button_selector = (By.CLASS_NAME, "checkout_button")
        self.item_price_selector = (By.CLASS_NAME, "inventory_item_price")
        self.remove_button_selector = (By.CLASS_NAME, "cart_button")
        self.subheader_selector = (By.CLASS_NAME, 'subheader')

    def click_continue_shopping(self):
        self.click_element(self.continue_shopping_button_selector)

    def click_checkout(self):
        self.click_element(self.checkout_button_selector)

    def remove_all_from_cart(self):
        remove_buttons = self.driver.find_elements(*self.remove_button_selector)
        while remove_buttons:
            remove = self.driver.find_element(*self.remove_button_selector)
            remove.click()
            remove_buttons = self.driver.find_elements(*self.remove_button_selector)

    def get_subheader(self):
        element = self.driver.find_element(*self.subheader_selector)
        return element.text

    def get_sum_prices(self):
        price_elements = self.driver.find_elements(*self.item_price_selector)
        total = 0.0
        for price in price_elements:
            total += float(price.text)
        return total

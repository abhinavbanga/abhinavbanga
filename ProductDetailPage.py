from selenium.webdriver.common.by import By
from pages.Page import Page
class ProductDetailPage(Page):

    def __init__(self, driver):
        super().__init__(driver)
        self.price_selector = (By.CLASS_NAME, "inventory_details_price")
        self.add_to_cart_button_selector = (By.CLASS_NAME, "btn_inventory")
        self.back_button_selector = (By.CLASS_NAME, "inventory_details_back_button")
        self.product_name_selector = (By.CLASS_NAME, "inventory_details_name")
        self.cart_item_count_selector = (By.CLASS_NAME, "shopping_cart_badge")

    def get_price(self):
        element = self.driver.find_element(*self.price_selector)
        return element.text

    def get_product_name(self):
        element = self.driver.find_element(*self.product_name_selector)
        return element.text

    def get_product_price(self):
        element = self.driver.find_element(*self.price_selector)
        return element.text

    def get_number_cart_items(self):
        has_items_in_cart = len(self.driver.find_elements(*self.cart_item_count_selector)) > 0
        if has_items_in_cart:
            num_items_in_cart = self.driver.find_element(*self.cart_item_count_selector).text
            return int(num_items_in_cart)
        else:
            return 0

    def click_add_to_cart(self):
        self.click_element(self.add_to_cart_button_selector)

    def click_back(self):
      self.click_element(self.back_button_selector)

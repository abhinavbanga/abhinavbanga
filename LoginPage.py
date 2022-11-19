from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from pages.Page import Page
class LoginPage(Page):

    def __init__(self, driver):
        super().__init__(driver)
        self.username_input_box_selector = (By.ID, "user-name")
        self.password_input_box_selector = (By.ID, "password")
        self.login_button_selector = (By.CLASS_NAME, "btn_action")
        self.error_message_selector = (By.XPATH, '//*[@id="login_button_container"]/div/form')
        self.init_site()

    def click_login(self):
        self.click_element(self.login_button_selector)
        WebDriverWait(self.driver, 5).until(EC.url_changes)

    def enter_password(self, password):
        self.send_keys_to_element(self.password_input_box_selector, password)

    def enter_username(self, username):
        self.send_keys_to_element(self.username_input_box_selector, username)

    def error_message_exists(self):
        error_message = self.driver.find_elements_by_xpath(self.error_message_selector[1])
        return len(error_message) > 0

    def get_error_message_text(self):
        if self.error_message_exists():
            return self.driver.find_elements_by_xpath(self.error_message_selector[1])[0].text
        return None

    def perform_complete_login(self, username, password):
        self.enter_username(username)
        self.enter_password(password)
        self.click_login()

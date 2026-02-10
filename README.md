# 🚀 SauceDemo Selenium Automation Framework

A professional Selenium WebDriver automation framework built using **Java**, **TestNG**, and **Page Object Model (POM)** design pattern.

---

## 📋 Table of Contents

- [About](#about)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running Tests](#running-tests)
- [Test Scenarios](#test-scenarios)
- [Configuration](#configuration)
- [Reports](#reports)
- [Author](#author)

---

## 📖 About

This framework automates the testing of [SauceDemo](https://www.saucedemo.com) - a demo e-commerce website. It demonstrates best practices in test automation including:

- Page Object Model (POM) design pattern
- Data-driven configuration
- Explicit waits for stability
- Comprehensive test coverage
- Clean, maintainable code structure

**Application Under Test:** https://www.saucedemo.com

---

## 🛠️ Technologies Used

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 1.8 | Programming Language |
| Selenium WebDriver | 4.38.0 | Browser Automation |
| TestNG | 7.11.0 | Testing Framework |
| Maven | 3.x | Build & Dependency Management |
| WebDriverManager | 6.3.3 | Auto Driver Management |

---

## 📁 Project Structure
```
SauceDemo-Selenium-Framework/
│
├── src/main/java/
│   ├── base/
│   │   └── BaseTest.java              # WebDriver setup & teardown
│   ├── pages/
│   │   ├── LoginPage.java             # Login page objects & methods
│   │   ├── ProductsPage.java          # Products page objects & methods
│   │   └── CartPage.java              # Cart page objects & methods
│   └── utils/
│       └── ConfigReader.java          # Configuration reader utility
│
├── src/test/java/
│   └── tests/
│       ├── LoginTest.java             # Login test scenarios
│       ├── ProductsTest.java          # Products page test scenarios
│       └── CartTest.java              # Shopping cart test scenarios
│
├── src/test/resources/
│   └── config.properties              # Test configuration file
│
├── pom.xml                            # Maven dependencies
├── testng.xml                         # TestNG suite configuration
└── README.md                          # Project documentation
```

---

## ✨ Features

✅ **Page Object Model (POM)** - Separation of test logic and page elements  
✅ **Maven Integration** - Centralized dependency management  
✅ **TestNG Framework** - Organized test execution with priorities  
✅ **WebDriverManager** - Automatic browser driver management  
✅ **Configurable** - Easy browser and URL configuration  
✅ **Explicit Waits** - Robust and stable test execution  
✅ **Cross-browser Support** - Chrome, Firefox, Edge ready  
✅ **Clean Code** - Well-documented and maintainable  

---

## ⚙️ Prerequisites

Before running this project, ensure you have:

- **Java JDK 8** or higher installed
- **Maven 3.x** installed
- **Eclipse IDE** (or any Java IDE)
- **Chrome Browser** installed
- **Git** (for cloning the repository)

---

## 📥 Installation

### 1. Clone the Repository
```bash
git clone https://github.com/YOUR_USERNAME/saucedemo-selenium-framework.git
cd saucedemo-selenium-framework
```

### 2. Import into Eclipse

1. Open Eclipse IDE
2. File → Import → Existing Maven Projects
3. Browse to project folder
4. Click Finish

### 3. Install Dependencies
```bash
mvn clean install
```

---

## ▶️ Running Tests

### Option 1: Run All Tests via TestNG Suite
```bash
mvn clean test
```

**OR** Right-click `testng.xml` → Run As → TestNG Suite

---

### Option 2: Run Individual Test Classes

**In Eclipse:**
- Right-click on test class (e.g., `LoginTest.java`)
- Run As → TestNG Test

**Via Maven:**
```bash
mvn test -Dtest=LoginTest
mvn test -Dtest=ProductsTest
mvn test -Dtest=CartTest
```

---

### Option 3: Run Single Test Method

**In Eclipse:**
- Right-click on `@Test` method
- Run As → TestNG Test

---

## 📊 Test Scenarios

### 🔐 Login Tests (3 scenarios)

| Test | Description |
|------|-------------|
| testValidLogin | Verify successful login with valid credentials |
| testInvalidLogin | Verify error message with invalid credentials |
| testEmptyCredentials | Verify error when credentials are empty |

---

### 🛍️ Products Page Tests (6 scenarios)

| Test | Description |
|------|-------------|
| testProductsPageDisplayed | Verify products page loads after login |
| testProductCount | Verify correct number of products displayed |
| testProductNamesDisplayed | Verify product names are visible |
| testAddProductToCart | Verify adding single product updates cart badge |
| testAddMultipleProducts | Verify adding multiple products |
| testLogout | Verify logout functionality |

---

### 🛒 Shopping Cart Tests (6 scenarios)

| Test | Description |
|------|-------------|
| testNavigateToCart | Verify navigation to cart page |
| testEmptyCart | Verify empty cart behavior |
| testProductInCart | Verify product appears after adding |
| testMultipleProductsInCart | Verify multiple products in cart |
| testContinueShopping | Verify Continue Shopping button |
| testCartItemDetails | Verify cart displays item names and prices |

---

## ⚙️ Configuration

Edit `src/test/resources/config.properties` to customize:
```properties
# Browser Configuration
browser=chrome          # Options: chrome, firefox, edge

# Application URL
appURL=https://www.saucedemo.com

# Wait Times (in seconds)
implicitWait=10
explicitWait=15
```

---

## 📈 Reports

After test execution, TestNG generates HTML reports:

**Location:** `test-output/index.html`

Open in browser to view detailed test results.

---

## 👤 Author

**Your Name**
- LinkedIn: [Your LinkedIn Profile]
- GitHub: [@YourUsername](https://github.com/YourUsername)
- Email: your.email@example.com

---

## 📝 License

This project is open source and available under the [MIT License](LICENSE).

---

## 🙏 Acknowledgments

- [SauceDemo](https://www.saucedemo.com) for providing the test application
- [Selenium](https://www.selenium.dev/) community
- [TestNG](https://testng.org/) framework

---

## 🚀 Future Enhancements

- [ ] Add Extent Reports
- [ ] Implement data-driven testing
- [ ] Add CI/CD integration (Jenkins/GitHub Actions)
- [ ] Cross-browser parallel execution
- [ ] Screenshot on test failure
- [ ] API testing integration

---

**⭐ If you find this project helpful, please give it a star!**
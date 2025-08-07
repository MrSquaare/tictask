import XCTest

final class TictaskUITests: XCTestCase {
    private var app: XCUIApplication!

    override func setUpWithError() throws {
        continueAfterFailure = false
        app = XCUIApplication()
        app.launch()
    }

    override func tearDownWithError() throws {
        app = nil
    }

    func testApp_shouldShowHomeScreenByDefault() throws {
        XCTAssertTrue(app.tabBars.buttons["Home"].isSelected)
        XCTAssertTrue(app.navigationBars["Home"].exists)
        XCTAssertTrue(app.cells.staticTexts["Item 1"].exists)
    }

    func testClickingSettingsTab_shouldShowSettingsScreen() throws {
        app.tabBars.buttons["Settings"].tap()

        XCTAssertTrue(app.tabBars.buttons["Settings"].isSelected)
        XCTAssertTrue(app.navigationBars["Settings"].exists)
        XCTAssertTrue(app.staticTexts["Settings Screen"].exists)
    }

    func testClickingItemOnHome_shouldShowDetailsScreen() throws {
        app.tabBars.buttons["Home"].tap()

        XCTAssertTrue(app.navigationBars["Home"].exists)
        XCTAssertTrue(app.cells.staticTexts["Item 1"].exists)

        app.cells.staticTexts["Item 1"].tap()

        XCTAssertTrue(app.navigationBars["Details: Item 1"].exists)
        XCTAssertTrue(app.staticTexts["Details Screen: Item 1"].exists)
    }

    func testNavigateToDetailsScreen_thenBack_shouldReturnToHome() throws {
        app.tabBars.buttons["Home"].tap()

        XCTAssertTrue(app.navigationBars["Home"].exists)
        XCTAssertTrue(app.cells.staticTexts["Item 1"].exists)

        app.cells.staticTexts["Item 1"].tap()

        XCTAssertTrue(app.navigationBars["Details: Item 1"].exists)
        XCTAssertTrue(app.staticTexts["Details Screen: Item 1"].exists)

        app.navigationBars["Details: Item 1"].buttons["Home"].tap()

        XCTAssertTrue(app.navigationBars["Home"].exists)
        XCTAssertTrue(app.cells.staticTexts["Item 1"].exists)
    }

    func testHomeScreen_shouldDisplayMultipleItems() throws {
        app.tabBars.buttons["Home"].tap()

        XCTAssertTrue(app.navigationBars["Home"].exists)
        XCTAssertTrue(app.cells.staticTexts["Item 1"].exists)
        XCTAssertTrue(app.cells.staticTexts["Item 2"].exists)
        XCTAssertTrue(app.cells.staticTexts["Item 3"].exists)
    }

    func testNavigateToItem1_thenBackToHome_thenToItem2_shouldShowItem2DetailsScreen() throws {
        app.tabBars.buttons["Home"].tap()

        XCTAssertTrue(app.navigationBars["Home"].exists)
        XCTAssertTrue(app.cells.staticTexts["Item 1"].exists)

        app.cells.staticTexts["Item 1"].tap()

        XCTAssertTrue(app.navigationBars["Details: Item 1"].exists)
        XCTAssertTrue(app.staticTexts["Details Screen: Item 1"].exists)

        app.navigationBars["Details: Item 1"].buttons["Home"].tap()

        XCTAssertTrue(app.navigationBars["Home"].exists)
        XCTAssertTrue(app.cells.staticTexts["Item 2"].exists)

        app.cells.staticTexts["Item 2"].tap()

        XCTAssertTrue(app.navigationBars["Details: Item 2"].exists)
        XCTAssertTrue(app.staticTexts["Details Screen: Item 2"].exists)
    }
}

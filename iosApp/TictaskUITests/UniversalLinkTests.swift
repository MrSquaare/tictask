import XCTest

final class UniversalLinkTests: XCTestCase {
    private var app: XCUIApplication!

    override func setUpWithError() throws {
        continueAfterFailure = false
        app = XCUIApplication()
    }

    override func tearDownWithError() throws {
        app = nil
    }

    func testUniversalLink_homeScreen_navigatesToHomeScreen() throws {
        try openUniversalLink(path: "home")

        XCTAssertTrue(app.navigationBars["Home"].exists)
    }

    func testUniversalLink_settingsScreen_navigatesToSettingsScreen() throws {
        try openUniversalLink(path: "settings")

        XCTAssertTrue(app.navigationBars["Settings"].exists)
    }

    func testUniversalLink_detailsScreen_navigatesToDetailsScreenWithCorrectItem() throws {
        try openUniversalLink(path: "home/0")

        XCTAssertTrue(app.navigationBars["Details: Item 1"].exists)
    }

    func testUniversalLink_unknownRoute_fallsBackToHomeScreen() throws {
        try openUniversalLink(path: "unknown")

        XCTAssertTrue(app.navigationBars["Home"].exists)
    }

    private func openUniversalLink(path: String) throws {
        guard let url = URL(string: "tictask://\(path)") else {
            XCTFail("Failed to create URL from path: \(path)")
            return
        }

        if #available(iOS 16.4, *) {
            app.open(url)
        } else {
            XCTFail("Need iOS 16.4+ to run universal link tests")
        }
    }
}

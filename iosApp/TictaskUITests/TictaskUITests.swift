//
//  TictaskUITests.swift
//  TictaskUITests
//
//  Created by Guillaume Bonnet on 24/06/2025.
//

import XCTest

final class TictaskUITests: XCTestCase {

    override func setUpWithError() throws {
        continueAfterFailure = false
    }

    @MainActor
    func testClickingButtonShouldRevealContent() throws {
        let app = XCUIApplication()
        app.launch()

        app.buttons["Click me!"].tap()
        XCTAssertTrue(app.staticTexts.containing(NSPredicate(format: "label CONTAINS %@", "SwiftUI:")).firstMatch.exists)
    }
}

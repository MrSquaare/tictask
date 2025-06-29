// swift-tools-version: 5.6

import PackageDescription

let package = Package(
  name: "tictask",
  dependencies: [
    .package(url: "https://github.com/SimplyDanny/SwiftLintPlugins", from: "0.59.1"),
    .package(url: "https://github.com/nicklockwood/SwiftFormat", from: "0.56.4"),
  ]
)
enum Items {
    static let sample = (0 ..< 20).map { index in
        Item(id: index, title: "Item \(index + 1)")
    }
}

import SwiftUI

struct HomeScreen: View {
    private let items = Items.sample

    var body: some View {
        NavigationStack {
            List(items) { item in
                NavigationLink(value: item) {
                    Text(item.title)
                        .font(.body)
                }
            }
            .navigationTitle("Home")
            .navigationBarTitleDisplayMode(.large)
            .navigationDestination(for: Item.self) { item in
                DetailsScreen(item: item)
            }
        }
    }
}

#Preview {
    HomeScreen()
}

import SwiftUI

struct HomeScreen: View {
    private let items = Items.sample

    var body: some View {
        List(items) { item in
            NavigationLink(destination: DetailsScreen(item: item)) {
                Text(item.title)
                    .font(.body)
            }
        }
        .navigationTitle("Home")
        .navigationBarTitleDisplayMode(.large)
    }
}

#Preview {
    NavigationStack {
        HomeScreen()
    }
}

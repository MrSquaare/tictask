import SwiftUI

struct HomeScreen: View {
    @State private var navigationPath: [Item] = []

    var body: some View {
        NavigationStack(path: $navigationPath) {
            List(Items.sample) { item in
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
        }.onOpenURL { url in
            let segments = url.pathComponents.filter { $0 != "/" }

            guard let idString = segments.first,
                  let id = Int(idString),
                  let item = Items.sample.first(where: { $0.id == id })
            else {
                navigationPath = []
                return
            }

            navigationPath = [item]
        }
    }
}

#Preview {
    HomeScreen()
}

import SwiftUI

struct MainTabView: View {
    @State private var selectedTab: String = "Home"

    var body: some View {
        TabView(selection: $selectedTab) {
            NavigationStack {
                HomeScreen()
            }
            .tabItem {
                Image(systemName: "house")
                Text("Home")
            }
            .tag("Home")

            NavigationStack {
                SettingsScreen()
            }
            .tabItem {
                Image(systemName: "gear")
                Text("Settings")
            }
            .tag("Settings")
        }
    }
}

#Preview {
    MainTabView()
}

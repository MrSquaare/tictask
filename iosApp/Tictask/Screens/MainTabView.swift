import SwiftUI

enum MainTab: String, Hashable {
    case home
    case settings

    var title: String {
        switch self {
        case .home:
            return "Home"
        case .settings:
            return "Settings"
        }
    }

    var icon: String {
        switch self {
        case .home:
            return "house"
        case .settings:
            return "gear"
        }
    }
}

struct MainTabView: View {
    @State private var selectedTab: MainTab = .home

    var body: some View {
        TabView(selection: $selectedTab) {
            HomeScreen()
                .tabItem {
                    Image(systemName: MainTab.home.icon)
                    Text(MainTab.home.title)
                }
                .tag(MainTab.home)

            SettingsScreen()
                .tabItem {
                    Image(systemName: MainTab.settings.icon)
                    Text(MainTab.settings.title)
                }
                .tag(MainTab.settings)
        }.onOpenURL { url in
            switch url.host {
            case "home":
                selectedTab = .home
            case "settings":
                selectedTab = .settings
            default:
                break
            }
        }
    }
}

#Preview {
    MainTabView()
}

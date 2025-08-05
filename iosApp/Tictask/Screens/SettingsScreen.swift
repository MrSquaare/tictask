import SwiftUI

struct SettingsScreen: View {
    var body: some View {
        VStack {
            Spacer()
            Text("Settings Screen")
                .font(.largeTitle)
                .fontWeight(.medium)
            Spacer()
        }
        .navigationTitle("Settings")
        .navigationBarTitleDisplayMode(.large)
    }
}

#Preview {
    SettingsScreen()
}

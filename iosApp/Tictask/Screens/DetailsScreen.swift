import SwiftUI

struct DetailsScreen: View {
    let item: Item
    
    var body: some View {
        VStack {
            Spacer()
            
            Text("Details Screen: \(item.title)")
                .font(.largeTitle)
                .fontWeight(.medium)
                .multilineTextAlignment(.center)
            
            Spacer()
        }
        .navigationTitle("Details: \(item.title)")
        .navigationBarTitleDisplayMode(.inline)
    }
}

#Preview {
    NavigationStack {
        DetailsScreen(item: Items.sample[0])
    }
}

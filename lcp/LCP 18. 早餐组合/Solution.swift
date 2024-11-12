class Solution {
    func breakfastNumber(_ staple: [Int], _ drinks: [Int], _ x: Int) -> Int {
        let mod = 1000000007
        var result = 0
        let sortedDrinks = drinks.sorted()
        
        for s in staple {
            let remaining = x - s
            if remaining >= sortedDrinks.first ?? 0 {
                var left = 0
                var right = sortedDrinks.count - 1
                
                while left < right {
                    let mid = (left + right + 1) / 2
                    if sortedDrinks[mid] <= remaining {
                        left = mid
                    } else {
                        right = mid - 1
                    }
                }
                result = (result + left + 1) % mod
            }
        }
        return result
    }
}
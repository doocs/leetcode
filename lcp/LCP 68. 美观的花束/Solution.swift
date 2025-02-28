class Solution {
    func beautifulBouquet(_ flowers: [Int], _ cnt: Int) -> Int {
        let mod = Int(1e9 + 7)
        var maxFlower = 0
        for flower in flowers {
            maxFlower = max(maxFlower, flower)
        }
        
        var flowerCount = [Int](repeating: 0, count: maxFlower + 1)
        var ans = 0
        var j = 0
        
        for i in 0..<flowers.count {
            flowerCount[flowers[i]] += 1
            
            while flowerCount[flowers[i]] > cnt {
                flowerCount[flowers[j]] -= 1
                j += 1
            }
            
            ans = (ans + (i - j + 1)) % mod
        }
        
        return ans
    }
}
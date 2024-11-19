class Solution {
    func storeWater(_ bucket: [Int], _ vat: [Int]) -> Int {
        let maxVat = vat.max() ?? 0
        if maxVat == 0 {
            return 0
        }
        
        let n = vat.count
        var ans = Int.max
        
        for x in 1...maxVat {
            var y = 0
            for i in 0..<n {
                if vat[i] > 0 {
                    y += max(0, (vat[i] + x - 1) / x - bucket[i])
                }
            }
            ans = min(ans, x + y)
        }
        
        return ans
    }
}

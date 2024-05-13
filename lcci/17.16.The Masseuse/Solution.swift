class Solution {
    func massage(_ nums: [Int]) -> Int {
        var f = 0
        var g = 0
        
        for x in nums {
            let ff = g + x
            let gg = max(f, g)
            f = ff
            g = gg
        }
        
        return max(f, g)
    }
}
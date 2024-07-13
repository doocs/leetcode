class Solution {
    func subarraySum(_ nums: [Int], _ k: Int) -> Int {
        var cnt: [Int: Int] = [0: 1]
        var ans = 0
        var s = 0
        
        for x in nums {
            s += x
            ans += cnt[s - k, default: 0]
            cnt[s, default: 0] += 1
        }
        
        return ans
    }
}
class Solution {
    func lengthOfLongestSubstring(_ s: String) -> Int {
        var ans = 0
        var j = 0
        var vis = Set<Character>()
        let sArray = Array(s)
        
        for i in 0..<sArray.count {
            while vis.contains(sArray[i]) {
                vis.remove(sArray[j])
                j += 1
            }
            vis.insert(sArray[i])
            ans = max(ans, i - j + 1)
        }
        
        return ans
    }
}
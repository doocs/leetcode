class Solution {
    func findLongestSubarray(_ array: [String]) -> [String] {
        var vis: [Int: Int] = [0: -1]
        var s = 0, mx = 0, k = 0
        
        for i in 0..<array.count {
            s += array[i].first!.isLetter ? 1 : -1
            if let j = vis[s] {
                if mx < i - j {
                    mx = i - j
                    k = j + 1
                }
            } else {
                vis[s] = i
            }
        }
        
        return Array(array[k..<(k + mx)])
    }
}

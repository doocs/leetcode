class Solution {
    func pairSums(_ nums: [Int], _ target: Int) -> [[Int]] {
        var countMap = [Int: Int]()
        var ans = [[Int]]()
        
        for x in nums {
            let y = target - x
            if let yCount = countMap[y], yCount > 0 {
                ans.append([x, y])
                countMap[y] = yCount - 1
                if countMap[y] == 0 {
                    countMap.removeValue(forKey: y)
                }
            } else {
                countMap[x, default: 0] += 1
            }
        }
        return ans
    }
}
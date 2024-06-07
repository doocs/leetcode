class Solution {
    func threeSum(_ nums: [Int]) -> [[Int]] {
        if nums.count < 3 {
            return []
        }
        let nums = nums.sorted()
        var ans = [[Int]]()
        let n = nums.count

        for i in 0..<n-2 {
            if nums[i] > 0 { break }
            if i > 0 && nums[i] == nums[i - 1] { continue }

            var j = i + 1
            var k = n - 1

            while j < k {
                let sum = nums[i] + nums[j] + nums[k]
                if sum < 0 {
                    j += 1
                } else if sum > 0 {
                    k -= 1
                } else {
                    ans.append([nums[i], nums[j], nums[k]])
                    j += 1
                    k -= 1
                    while j < k && nums[j] == nums[j - 1] { j += 1 }
                    while j < k && nums[k] == nums[k + 1] { k -= 1 }
                }
            }
        }
        return ans
    }
}

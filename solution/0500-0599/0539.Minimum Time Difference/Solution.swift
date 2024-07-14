class Solution {
    func findMinDifference(_ timePoints: [String]) -> Int {
        if timePoints.count > 1440 {
            return 0
        }

        var nums = [Int]()

        for t in timePoints {
            let time = t.split(separator: ":").map { Int($0)! }
            nums.append(time[0] * 60 + time[1])
        }

        nums.sort()
        nums.append(nums[0] + 1440)

        var ans = Int.max
        for i in 1..<nums.count {
            ans = min(ans, nums[i] - nums[i - 1])
        }

        return ans
    }
}
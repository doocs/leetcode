class Solution {
    func twoSum(nums: Array<Int64>, target: Int64): Array<Int64> {
        let d = HashMap<Int64, Int64>()
        for (i in 0..nums.size) {
            if (d.contains(target - nums[i])) {
                return [d[target - nums[i]], i]
            }
            d[nums[i]] = i
        }
        []
    }
}

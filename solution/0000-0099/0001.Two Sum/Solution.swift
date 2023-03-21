class Solution {
    func twoSum(_ nums: [Int], _ target: Int) -> [Int] {
        var m = [Int: Int]()
        var i = 0
        while true {
            let x = nums[i]
            let y = target - nums[i]
            if let j = m[target - nums[i]] {
                return [j, i]
            }
            m[nums[i]] = i
            i += 1
        }
    }
}
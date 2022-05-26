class Solution {
    func twoSum(_ nums: [Int], _ target: Int) -> [Int] {
        var map = [Int: Int]()
        var i = 0
        for num in nums {
            map[num] = i
            i = i + 1
        }
        i = 0
        for num in nums {
            if let otherIndex = map[target - num], otherIndex != i {
                return [i, otherIndex]
            }
            i = i + 1
        }
        return []
    }
}
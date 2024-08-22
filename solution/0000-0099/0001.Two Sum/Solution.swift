class Solution {
    func twoSum(_ nums: [Int], _ target: Int) -> [Int] {
        var d = [Int: Int]()
        for (i, x) in nums.enumerated() {
            let y = target - x
            if let j = d[y] {
                return [j, i]
            }
            d[x] = i
        }
        return []
    }
}

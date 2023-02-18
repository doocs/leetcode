class Solution {
    func singleNumber(_ nums: [Int]) -> Int {
        var a = nums.sorted()
        var n = a.count
        for i in stride(from: 0, through: n - 2, by: 3) {
            if a[i] != a[i + 1] {
                return a[i]
            }
        }
        return a[n - 1]
    }
}
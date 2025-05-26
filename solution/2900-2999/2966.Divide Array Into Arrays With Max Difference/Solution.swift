class Solution {
    func divideArray(_ nums: [Int], _ k: Int) -> [[Int]] {
        var sortedNums = nums.sorted()
        var ans: [[Int]] = []

        for i in stride(from: 0, to: sortedNums.count, by: 3) {
            if i + 2 >= sortedNums.count {
                return []
            }

            let t = Array(sortedNums[i..<i+3])
            if t[2] - t[0] > k {
                return []
            }

            ans.append(t)
        }

        return ans
    }
}

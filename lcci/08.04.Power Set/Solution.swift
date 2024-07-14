class Solution {
    private var ans = [[Int]]()
    private var nums: [Int] = []

    func subsets(_ nums: [Int]) -> [[Int]] {
        self.nums = nums
        dfs(0, [])
        return ans.sorted { $0.count < $1.count }
    }

    private func dfs(_ u: Int, _ t: [Int]) {
        if u == nums.count {
            ans.append(t)
            return
        }
        dfs(u + 1, t)
        var tWithCurrent = t
        tWithCurrent.append(nums[u])
        dfs(u + 1, tWithCurrent)
    }
}

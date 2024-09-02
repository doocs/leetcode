class Solution {
    func permuteUnique(_ nums: [Int]) -> [[Int]] {
        var res = [[Int]]()
        var path = [Int]()
        var used = [Bool](repeating: false, count: nums.count)
        let sortedNums = nums.sorted()
        dfs(0, sortedNums.count, sortedNums, &used, &path, &res)
        return res
    }

    private func dfs(
        _ u: Int, _ n: Int, _ nums: [Int], _ used: inout [Bool], _ path: inout [Int], _ res: inout [[Int]]
    ) {
        if u == n {
            res.append(path)
            return
        }
        for i in 0..<n {
            if used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue
            }
            path.append(nums[i])
            used[i] = true
            dfs(u + 1, n, nums, &used, &path, &res)
            used[i] = false
            path.removeLast()
        }
    }
}
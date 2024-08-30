class Solution {
    func subsets(_ nums: [Int]) -> [[Int]] {
        var res = [[Int]]()
        dfs(0, nums, [], &res)
        return res
    }
    
    private func dfs(_ i: Int, _ nums: [Int], _ current: [Int], _ res: inout [[Int]]) {
        res.append(current)
        for j in i..<nums.count {
            var newCurrent = current
            newCurrent.append(nums[j])
            dfs(j + 1, nums, newCurrent, &res)
        }
    }
}
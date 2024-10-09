class Solution {
    private var ans: [[Int]] = []
    private var target: Int = 0
    private var candidates: [Int] = []

    func combinationSum(_ candidates: [Int], _ target: Int) -> [[Int]] {
        self.ans = []
        self.target = target
        self.candidates = candidates
        dfs(0, 0, [])
        return ans
    }

    private func dfs(_ sum: Int, _ index: Int, _ current: [Int]) {
        if sum == target {
            ans.append(current)
            return
        }
        if sum > target {
            return
        }
        for i in index..<candidates.count {
            let candidate = candidates[i]
            dfs(sum + candidate, i, current + [candidate])
        }
    }
}
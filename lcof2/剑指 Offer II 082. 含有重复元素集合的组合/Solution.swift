class Solution {
    private var ans: [[Int]] = []
    private var candidates: [Int] = []
    private var target: Int = 0

    func combinationSum2(_ candidates: [Int], _ target: Int) -> [[Int]] {
        self.ans = []
        self.target = target
        self.candidates = candidates.sorted()
        dfs(0, 0, [])
        return ans
    }

    private func dfs(_ index: Int, _ sum: Int, _ current: [Int]) {
        if sum > target {
            return
        }
        if sum == target {
            ans.append(current)
            return
        }
        for i in index..<candidates.count {
            if i > index && candidates[i] == candidates[i - 1] {
                continue
            }
            dfs(i + 1, sum + candidates[i], current + [candidates[i]])
        }
    }
}
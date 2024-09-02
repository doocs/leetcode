class Solution {
    func generateParenthesis(_ n: Int) -> [String] {
        var ans = [String]()
        dfs(0, 0, n, "", &ans)
        return ans
    }

    private func dfs(_ left: Int, _ right: Int, _ n: Int, _ t: String, _ ans: inout [String]) {
        if left == n && right == n {
            ans.append(t)
            return
        }
        if left < n {
            dfs(left + 1, right, n, t + "(", &ans)
        }
        if right < left {
            dfs(left, right + 1, n, t + ")", &ans)
        }
    }
}
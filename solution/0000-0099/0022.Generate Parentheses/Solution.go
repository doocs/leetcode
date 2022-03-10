func generateParenthesis(n int) []string {
	var ans []string
	var dfs func(left, right int, t string)
	dfs = func(left, right int, t string) {
		if left == n && right == n {
			ans = append(ans, t)
			return
		}
		if left < n {
			dfs(left+1, right, t+"(")
		}
		if right < left {
			dfs(left, right+1, t+")")
		}
	}
	dfs(0, 0, "")
	return ans
}
func generateParenthesis(n int) []string {
	var ans []string
	dfs(0, 0, n, "", &ans)
	return ans
}

func dfs(left, right, n int, t string, ans *[]string) {
	if left == n && right == n {
		*ans = append(*ans, t)
		return
	}
	if left < n {
		dfs(left+1, right, n, t+"(", ans)
	}
	if right < left {
		dfs(left, right+1, n, t+")", ans)
	}
}
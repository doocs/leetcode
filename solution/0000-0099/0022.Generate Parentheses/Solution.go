func generateParenthesis(n int) []string {
	res := new([]string)
	dfs(res, "", 0, 0, n)
	return *res
}

func dfs(res *[]string, ans string, l, r, n int) {
	if len(ans) == (n << 1) {
		*res = append(*res, ans)
		return
	}
	if l < n {
		dfs(res, ans+"(", l+1, r, n)
	}
	if r < l {
		dfs(res, ans+")", l, r+1, n)
	}
}
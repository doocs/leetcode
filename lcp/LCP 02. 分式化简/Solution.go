func fraction(cont []int) []int {
	var dfs func(i int) []int
	dfs = func(i int) []int {
		if i == len(cont)-1 {
			return []int{cont[i], 1}
		}
		ans := dfs(i + 1)
		a, b := ans[0], ans[1]
		return []int{a*cont[i] + b, a}
	}
	return dfs(0)
}
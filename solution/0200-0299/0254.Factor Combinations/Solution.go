func getFactors(n int) [][]int {
	t := []int{}
	ans := [][]int{}
	var dfs func(n, i int)
	dfs = func(n, i int) {
		if len(t) > 0 {
			cp := make([]int, len(t))
			copy(cp, t)
			cp = append(cp, n)
			ans = append(ans, cp)
		}
		for j := i; j <= n/j; j++ {
			if n%j == 0 {
				t = append(t, j)
				dfs(n/j, j)
				t = t[:len(t)-1]
			}
		}
	}
	dfs(n, 2)
	return ans
}
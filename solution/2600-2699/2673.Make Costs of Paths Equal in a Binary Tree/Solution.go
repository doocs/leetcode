func minIncrements(n int, cost []int) (ans int) {
	var dfs func(int) int
	dfs = func(i int) int {
		if (i << 1) > n {
			return cost[i-1]
		}
		l, r := dfs(i<<1), dfs(i<<1|1)
		ans += max(l, r) - min(l, r)
		return cost[i-1] + max(l, r)
	}
	dfs(1)
	return ans
}
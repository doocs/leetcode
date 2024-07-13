func minCostClimbingStairs(cost []int) int {
	n := len(cost)
	f := make([]int, n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] < 0 {
			f[i] = cost[i] + min(dfs(i+1), dfs(i+2))
		}
		return f[i]
	}
	return min(dfs(0), dfs(1))
}

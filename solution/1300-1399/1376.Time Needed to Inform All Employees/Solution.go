func numOfMinutes(n int, headID int, manager []int, informTime []int) int {
	g := make(map[int][]int)
	for i, m := range manager {
		g[m] = append(g[m], i)
	}
	var dfs func(i int) int
	dfs = func(i int) int {
		ans := 0
		if v, ok := g[i]; ok {
			for _, j := range v {
				ans = max(ans, informTime[i]+dfs(j))
			}
		}
		return ans
	}
	return dfs(headID)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
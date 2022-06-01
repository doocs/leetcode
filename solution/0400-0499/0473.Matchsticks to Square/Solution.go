func makesquare(matchsticks []int) bool {
	s := 0
	for _, v := range matchsticks {
		s += v
	}
	if s%4 != 0 {
		return false
	}
	sort.Sort(sort.Reverse(sort.IntSlice(matchsticks)))
	edges := make([]int, 4)
	var dfs func(u, x int) bool
	dfs = func(u, x int) bool {
		if u == len(matchsticks) {
			return true
		}
		for i := 0; i < 4; i++ {
			if i > 0 && edges[i-1] == edges[i] {
				continue
			}
			edges[i] += matchsticks[u]
			if edges[i] <= x && dfs(u+1, x) {
				return true
			}
			edges[i] -= matchsticks[u]
		}
		return false
	}
	return dfs(0, s/4)
}

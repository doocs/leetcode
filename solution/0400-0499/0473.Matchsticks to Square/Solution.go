func makesquare(matchsticks []int) bool {
	s := 0
	for _, v := range matchsticks {
		s += v
	}
	if s%4 != 0 {
		return false
	}
	sort.Ints(matchsticks)
	edges := make([]int, 4)
	var dfs func(u, x int) bool
	dfs = func(u, x int) bool {
		if u < 0 {
			return true
		}
		for i := 0; i < 4; i++ {
			edges[i] += matchsticks[u]
			if edges[i] <= x && dfs(u-1, x) {
				return true
			}
			edges[i] -= matchsticks[u]
		}
		return false
	}
	return dfs(len(matchsticks)-1, s/4)
}
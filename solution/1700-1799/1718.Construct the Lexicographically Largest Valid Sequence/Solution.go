func constructDistancedSequence(n int) []int {
	path := make([]int, n*2)
	cnt := make([]int, n*2)
	for i := range cnt {
		cnt[i] = 2
	}
	cnt[1] = 1
	var dfs func(u int) bool
	dfs = func(u int) bool {
		if u == n*2 {
			return true
		}
		if path[u] > 0 {
			return dfs(u + 1)
		}
		for i := n; i > 1; i-- {
			if cnt[i] > 0 && u+i < n*2 && path[u+i] == 0 {
				cnt[i] = 0
				path[u], path[u+i] = i, i
				if dfs(u + 1) {
					return true
				}
				cnt[i] = 2
				path[u], path[u+i] = 0, 0
			}
		}
		if cnt[1] > 0 {
			cnt[1] = 0
			path[u] = 1
			if dfs(u + 1) {
				return true
			}
			cnt[1] = 1
			path[u] = 0
		}
		return false
	}
	dfs(1)
	return path[1:]
}
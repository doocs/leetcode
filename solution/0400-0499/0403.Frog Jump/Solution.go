func canCross(stones []int) bool {
	n := len(stones)
	f := make([][]int, n)
	pos := map[int]int{}
	for i := range f {
		pos[stones[i]] = i
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(int, int) bool
	dfs = func(i, k int) bool {
		if i == n-1 {
			return true
		}
		if f[i][k] != -1 {
			return f[i][k] == 1
		}
		for j := k - 1; j <= k+1; j++ {
			if j > 0 {
				if p, ok := pos[stones[i]+j]; ok {
					if dfs(p, j) {
						f[i][k] = 1
						return true
					}
				}
			}
		}
		f[i][k] = 0
		return false
	}
	return dfs(0, 0)
}
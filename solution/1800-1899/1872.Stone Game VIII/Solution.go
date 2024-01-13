func stoneGameVIII(stones []int) int {
	n := len(stones)
	f := make([]int, n)
	for i := range f {
		f[i] = -1
	}
	for i := 1; i < n; i++ {
		stones[i] += stones[i-1]
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n-1 {
			return stones[i]
		}
		if f[i] == -1 {
			f[i] = max(dfs(i+1), stones[i]-dfs(i+1))
		}
		return f[i]
	}
	return dfs(1)
}
func maximumScore(nums []int, multipliers []int) int {
	n, m := len(nums), len(multipliers)
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, m)
		for j := range f[i] {
			f[i][j] = 1 << 30
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i >= m || j >= m || i+j >= m {
			return 0
		}
		if f[i][j] != 1<<30 {
			return f[i][j]
		}
		k := i + j
		a := dfs(i+1, j) + nums[i]*multipliers[k]
		b := dfs(i, j+1) + nums[n-j-1]*multipliers[k]
		f[i][j] = max(a, b)
		return f[i][j]
	}
	return dfs(0, 0)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
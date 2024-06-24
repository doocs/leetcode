func maximumTotalCost(nums []int) int64 {
	n := len(nums)
	f := make([][2]int64, n)
	for i := range f {
		f[i] = [2]int64{-1e18, -1e18}
	}
	var dfs func(int, int) int64
	dfs = func(i, j int) int64 {
		if i >= n {
			return 0
		}
		if f[i][j] != -1e18 {
			return f[i][j]
		}
		f[i][j] = int64(nums[i]) + dfs(i+1, 1)
		if j > 0 {
			f[i][j] = max(f[i][j], int64(-nums[i])+dfs(i+1, 0))
		}
		return f[i][j]
	}
	return dfs(0, 0)
}
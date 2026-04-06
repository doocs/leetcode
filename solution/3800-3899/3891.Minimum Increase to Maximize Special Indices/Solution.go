func minIncrease(nums []int) int64 {
	n := len(nums)

	f := make([][]int64, n)
	for i := range f {
		f[i] = []int64{-1, -1}
	}

	var dfs func(i, j int) int64
	dfs = func(i, j int) int64 {
		if i >= n-1 {
			return 0
		}
		if f[i][j] != -1 {
			return f[i][j]
		}

		cost := max(0, max(nums[i-1], nums[i+1])+1-nums[i])
		ans := int64(cost) + dfs(i+2, j)

		if j > 0 {
			if t := dfs(i+1, 0); t < ans {
				ans = t
			}
		}

		f[i][j] = ans
		return ans
	}

	return dfs(1, (n&1)^1)
}

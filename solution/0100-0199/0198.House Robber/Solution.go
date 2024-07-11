func rob(nums []int) int {
	n := len(nums)
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
			f[i] = max(nums[i]+dfs(i+2), dfs(i+1))
		}
		return f[i]
	}
	return dfs(0)
}

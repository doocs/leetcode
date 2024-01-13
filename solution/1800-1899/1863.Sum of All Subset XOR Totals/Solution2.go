func subsetXORSum(nums []int) (ans int) {
	n := len(nums)
	var dfs func(int, int)
	dfs = func(i, s int) {
		if i >= n {
			ans += s
			return
		}
		dfs(i+1, s)
		dfs(i+1, s^nums[i])
	}
	dfs(0, 0)
	return
}
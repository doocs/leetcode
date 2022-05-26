func subsets(nums []int) [][]int {
	var ans [][]int
	var dfs func(u int, t []int)
	dfs = func(u int, t []int) {
		if u == len(nums) {
			cp := make([]int, len(t))
			copy(cp, t)
			ans = append(ans, cp)
			return
		}
		t = append(t, nums[u])
		dfs(u+1, t)
		t = t[:len(t)-1]
		dfs(u+1, t)

	}
	var t []int
	dfs(0, t)
	return ans
}
func subsets(nums []int) [][]int {
	var ans [][]int
	var dfs func(u int, t []int)
	dfs = func(u int, t []int) {
		if u == len(nums) {
			ans = append(ans, append([]int(nil), t...))
			return
		}
		dfs(u+1, t)
		t = append(t, nums[u])
		dfs(u+1, t)
		t = t[:len(t)-1]
	}
	var t []int
	dfs(0, t)
	return ans
}
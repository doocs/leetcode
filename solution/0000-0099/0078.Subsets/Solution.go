func subsets(nums []int) (ans [][]int) {
	t := []int{}
	var dfs func(int)
	dfs = func(i int) {
		if i == len(nums) {
			ans = append(ans, append([]int(nil), t...))
			return
		}
		dfs(i + 1)
		t = append(t, nums[i])
		dfs(i + 1)
		t = t[:len(t)-1]
	}
	dfs(0)
	return
}
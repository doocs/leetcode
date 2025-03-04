func permuteUnique(nums []int) (ans [][]int) {
	slices.Sort(nums)
	n := len(nums)
	t := make([]int, n)
	vis := make([]bool, n)
	var dfs func(int)
	dfs = func(i int) {
		if i == n {
			ans = append(ans, slices.Clone(t))
			return
		}
		for j := 0; j < n; j++ {
			if vis[j] || (j > 0 && nums[j] == nums[j-1] && !vis[j-1]) {
				continue
			}
			vis[j] = true
			t[i] = nums[j]
			dfs(i + 1)
			vis[j] = false
		}
	}
	dfs(0)
	return
}
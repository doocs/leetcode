func findSubsequences(nums []int) [][]int {
	var ans [][]int
	var dfs func(u, last int, t []int)
	dfs = func(u, last int, t []int) {
		if u == len(nums) {
			if len(t) > 1 {
				cp := make([]int, len(t))
				copy(cp, t)
				ans = append(ans, cp)
			}
			return
		}
		if nums[u] >= last {
			t = append(t, nums[u])
			dfs(u+1, nums[u], t)
			t = t[:len(t)-1]
		}
		if nums[u] != last {
			dfs(u+1, last, t)
		}
	}
	var t []int
	dfs(0, -1000, t)
	return ans
}
func countMaxOrSubsets(nums []int) int {
	mx, ans := 0, 0
	var dfs func(u, t int)
	dfs = func(u, t int) {
		if u == len(nums) {
			if t > mx {
				mx, ans = t, 1
			} else if t == mx {
				ans++
			}
			return
		}
		dfs(u+1, t)
		dfs(u+1, t|nums[u])
	}
	dfs(0, 0)
	return ans
}
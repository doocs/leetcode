func subsetsWithDup(nums []int) [][]int {
	sort.Ints(nums)
	var ans [][]int
	var dfs func(u int, t []int)
	dfs = func(u int, t []int) {
		ans = append(ans, append([]int(nil), t...))
		for i := u; i < len(nums); i++ {
			if i != u && nums[i] == nums[i-1] {
				continue
			}
			t = append(t, nums[i])
			dfs(i+1, t)
			t = t[:len(t)-1]
		}
	}
	var t []int
	dfs(0, t)
	return ans
}
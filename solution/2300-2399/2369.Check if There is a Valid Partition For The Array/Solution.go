func validPartition(nums []int) bool {
	n := len(nums)
	f := make([]int, n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) bool
	dfs = func(i int) bool {
		if i == n {
			return true
		}
		if f[i] != -1 {
			return f[i] == 1
		}
		a := i+1 < n && nums[i] == nums[i+1]
		b := i+2 < n && nums[i] == nums[i+1] && nums[i+1] == nums[i+2]
		c := i+2 < n && nums[i+1]-nums[i] == 1 && nums[i+2]-nums[i+1] == 1
		f[i] = 0
		if a && dfs(i+2) || b && dfs(i+3) || c && dfs(i+3) {
			f[i] = 1
		}
		return f[i] == 1
	}
	return dfs(0)
}
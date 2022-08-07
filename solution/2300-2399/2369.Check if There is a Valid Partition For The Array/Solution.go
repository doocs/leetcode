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
		res := false
		f[i] = 0
		if i < n-1 && nums[i] == nums[i+1] {
			res = res || dfs(i+2)
		}
		if i < n-2 && nums[i] == nums[i+1] && nums[i+1] == nums[i+2] {
			res = res || dfs(i+3)
		}
		if i < n-2 && nums[i+1]-nums[i] == 1 && nums[i+2]-nums[i+1] == 1 {
			res = res || dfs(i+3)
		}
		if res {
			f[i] = 1
		}
		return res
	}
	return dfs(0)
}
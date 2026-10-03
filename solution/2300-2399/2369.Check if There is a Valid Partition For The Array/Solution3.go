func validPartition(nums []int) bool {
	n := len(nums)
	f := make([]bool, n+1)
	f[n] = true
	for i := n - 1; i >= 0; i-- {
		a := i+1 < n && nums[i] == nums[i+1]
		b := i+2 < n && nums[i] == nums[i+1] && nums[i+1] == nums[i+2]
		c := i+2 < n && nums[i+1]-nums[i] == 1 && nums[i+2]-nums[i+1] == 1
		f[i] = (a && f[i+2]) || ((b || c) && f[i+3])
	}
	return f[0]
}

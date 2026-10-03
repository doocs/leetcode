func minIncrease(nums []int) int64 {
	n := len(nums)
	f := make([][2]int64, n+1)
	for i := n - 2; i >= 1; i-- {
		cost := int64(max(0, max(nums[i-1], nums[i+1])+1-nums[i]))
		f[i][0] = cost + f[i+2][0]
		t := cost + f[i+2][1]
		if f[i+1][0] < t {
			t = f[i+1][0]
		}
		f[i][1] = t
	}
	return f[1][(n&1)^1]
}

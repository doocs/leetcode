func pivotIndex(nums []int) int {
	n := len(nums)
	sum := make([]int, n+1)
	for i := 1; i <= n; i++ {
		sum[i] = sum[i-1] + nums[i-1]
	}
	for i := 0; i < n; i++ {
		if sum[i] == sum[n]-sum[i+1] {
			return i
		}
	}
	return -1
}

func resultsArray(nums []int, k int) (ans []int) {
	n := len(nums)
	f := make([]int, n)
	f[0] = 1
	for i := 1; i < n; i++ {
		if nums[i] == nums[i-1]+1 {
			f[i] = f[i-1] + 1
		} else {
			f[i] = 1
		}
	}
	for i := k - 1; i < n; i++ {
		if f[i] >= k {
			ans = append(ans, nums[i])
		} else {
			ans = append(ans, -1)
		}
	}
	return
}

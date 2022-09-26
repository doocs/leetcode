func goodIndices(nums []int, k int) []int {
	n := len(nums)
	decr := make([]int, n)
	incr := make([]int, n)
	for i := range decr {
		decr[i] = 1
		incr[i] = 1
	}
	for i := 2; i < n; i++ {
		if nums[i-1] <= nums[i-2] {
			decr[i] = decr[i-1] + 1
		}
	}
	for i := n - 3; i >= 0; i-- {
		if nums[i+1] <= nums[i+2] {
			incr[i] = incr[i+1] + 1
		}
	}
	ans := []int{}
	for i := k; i < n-k; i++ {
		if decr[i] >= k && incr[i] >= k {
			ans = append(ans, i)
		}
	}
	return ans
}
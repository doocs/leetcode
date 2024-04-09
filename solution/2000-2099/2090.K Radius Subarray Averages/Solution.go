func getAverages(nums []int, k int) []int {
	k = k<<1 | 1
	n := len(nums)
	ans := make([]int, n)
	for i := range ans {
		ans[i] = -1
	}
	if k > n {
		return ans
	}
	s := 0
	for _, x := range nums[:k] {
		s += x
	}
	j := k >> 1
	ans[j] = s / k
	for i := k; i < n; i++ {
		s += nums[i] - nums[i-k]
		j++
		ans[j] = s / k
	}
	return ans
}
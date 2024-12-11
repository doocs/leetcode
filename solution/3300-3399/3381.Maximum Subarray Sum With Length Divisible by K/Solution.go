func maxSubarraySum(nums []int, k int) int64 {
	inf := int64(1) << 62
	f := make([]int64, k)
	for i := range f {
		f[i] = inf
	}
	f[k-1] = 0

	var s, ans int64
	ans = -inf
	for i := 0; i < len(nums); i++ {
		s += int64(nums[i])
		ans = max(ans, s-f[i%k])
		f[i%k] = min(f[i%k], s)
	}

	return ans
}

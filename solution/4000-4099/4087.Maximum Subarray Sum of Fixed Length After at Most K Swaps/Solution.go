func maxSum(nums []int, length int, k int) int64 {
	n := len(nums)
	maxSwaps := min(k, min(length, n-length))
	ans := int64(math.MinInt64)
	for i := 0; i <= n-length; i++ {
		internal := make([]int, length)
		copy(internal, nums[i:i+length])
		external := make([]int, 0, n-length)
		external = append(external, nums[:i]...)
		external = append(external, nums[i+length:]...)
		sort.Ints(internal)
		sort.Slice(external, func(a, b int) bool { return external[a] > external[b] })
		var total int64
		for _, v := range internal {
			total += int64(v)
		}
		for j := 0; j < maxSwaps; j++ {
			gain := int64(external[j]) - int64(internal[j])
			if gain <= 0 {
				break
			}
			total += gain
		}
		ans = max(ans, total)
	}
	return ans
}

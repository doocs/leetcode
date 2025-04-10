func countSubarrays(nums []int, k int64) (ans int64) {
	s, j := 0, 0
	for i, x := range nums {
		s += x
		for int64(s*(i-j+1)) >= k {
			s -= nums[j]
			j++
		}
		ans += int64(i - j + 1)
	}
	return
}
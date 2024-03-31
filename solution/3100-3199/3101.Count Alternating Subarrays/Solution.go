func countAlternatingSubarrays(nums []int) int64 {
	ans, s := int64(1), int64(1)
	for i, x := range nums[1:] {
		if x != nums[i] {
			s++
		} else {
			s = 1
		}
		ans += s
	}
	return ans
}
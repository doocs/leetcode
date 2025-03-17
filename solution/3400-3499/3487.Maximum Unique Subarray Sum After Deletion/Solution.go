func maxSum(nums []int) (ans int) {
	mx := slices.Max(nums)
	if mx <= 0 {
		return mx
	}
	s := make(map[int]bool)
	for _, x := range nums {
		if x < 0 || s[x] {
			continue
		}
		ans += x
		s[x] = true
	}
	return
}

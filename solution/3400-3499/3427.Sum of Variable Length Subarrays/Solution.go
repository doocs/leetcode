func subarraySum(nums []int) (ans int) {
	s := make([]int, len(nums)+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	for i, x := range nums {
		ans += s[i+1] - s[max(0, i-x)]
	}
	return
}

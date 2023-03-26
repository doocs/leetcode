func minOperations(nums []int, queries []int) (ans []int64) {
	sort.Ints(nums)
	n := len(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	for _, x := range queries {
		i := sort.SearchInts(nums, x+1)
		t := s[n] - s[i] - (n-i)*x
		i = sort.SearchInts(nums, x)
		t += x*i - s[i]
		ans = append(ans, int64(t))
	}
	return
}
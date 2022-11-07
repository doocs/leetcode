func getSumAbsoluteDifferences(nums []int) []int {
	s := 0
	for _, v := range nums {
		s += v
	}
	t, n := 0, len(nums)
	ans := make([]int, n)
	for i, v := range nums {
		x := s - t - (n-i)*v + v*i - t
		t += v
		ans[i] = x
	}
	return ans
}
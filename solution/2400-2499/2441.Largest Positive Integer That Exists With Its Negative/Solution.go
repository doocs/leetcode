func findMaxK(nums []int) int {
	s := map[int]bool{}
	for _, v := range nums {
		s[v] = true
	}
	ans := -1
	for v := range s {
		if s[-v] && ans < v {
			ans = v
		}
	}
	return ans
}
func findMaxK(nums []int) int {
	ans := -1
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	for x := range s {
		if s[-x] && ans < x {
			ans = x
		}
	}
	return ans
}
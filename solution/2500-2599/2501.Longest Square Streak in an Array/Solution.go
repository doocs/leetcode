func longestSquareStreak(nums []int) int {
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	ans := -1
	for x := range s {
		t := 0
		for s[x] {
			x *= x
			t++
		}
		if t > 1 {
			ans = max(ans, t)
		}
	}
	return ans
}
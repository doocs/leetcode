func longestSquareStreak(nums []int) int {
	s := map[int]bool{}
	for _, v := range nums {
		s[v] = true
	}
	ans := -1
	for _, v := range nums {
		t := 0
		for s[v] {
			v *= v
			t++
		}
		if t > 1 && t > ans {
			ans = t
		}
	}
	return ans
}
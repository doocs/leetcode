func shortestSequence(rolls []int, k int) int {
	s := map[int]bool{}
	ans := 1
	for _, v := range rolls {
		s[v] = true
		if len(s) == k {
			ans++
			s = map[int]bool{}
		}
	}
	return ans
}
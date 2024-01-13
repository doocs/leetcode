func maximumScore(a int, b int, c int) (ans int) {
	s := []int{a, b, c}
	sort.Ints(s)
	for s[1] > 0 {
		ans++
		s[1]--
		s[2]--
		sort.Ints(s)
	}
	return
}
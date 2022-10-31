func magicalString(n int) (ans int) {
	s := []int{1, 2, 2}
	i := 2
	for len(s) < n {
		pre := s[len(s)-1]
		cur := 3 - pre
		for j := 0; j < s[i]; j++ {
			s = append(s, cur)
		}
		i++
	}
	for j := 0; j < n; j++ {
		if s[j] == 1 {
			ans++
		}
	}
	return
}
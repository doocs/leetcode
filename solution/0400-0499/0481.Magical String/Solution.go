func magicalString(n int) (ans int) {
	s := []int{1, 2, 2}
	for i := 2; len(s) < n; i++ {
		pre := s[len(s)-1]
		cur := 3 - pre
		for j := 0; j < s[i]; j++ {
			s = append(s, cur)
		}
	}
	for _, c := range s[:n] {
		if c == 1 {
			ans++
		}
	}
	return
}
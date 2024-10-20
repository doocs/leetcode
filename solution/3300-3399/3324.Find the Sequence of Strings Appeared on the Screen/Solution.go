func stringSequence(target string) (ans []string) {
	for _, c := range target {
		s := ""
		if len(ans) > 0 {
			s = ans[len(ans)-1]
		}
		for a := 'a'; a <= c; a++ {
			t := s + string(a)
			ans = append(ans, t)
		}
	}
	return
}

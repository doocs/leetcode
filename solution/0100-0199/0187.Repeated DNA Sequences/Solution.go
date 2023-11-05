func findRepeatedDnaSequences(s string) (ans []string) {
	cnt := map[string]int{}
	for i := 0; i < len(s)-10+1; i++ {
		t := s[i : i+10]
		cnt[t]++
		if cnt[t] == 2 {
			ans = append(ans, t)
		}
	}
	return
}
func findRepeatedDnaSequences(s string) []string {
	cnt := make(map[string]int)
	n := len(s) - 10
	ans := make([]string, 0)
	for i := 0; i <= n; i++ {
		sub := s[i : i+10]
		cnt[sub]++
		if cnt[sub] == 2 {
			ans = append(ans, sub)
		}
	}
	return ans
}

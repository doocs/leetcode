func cellsInRange(s string) []string {
	var ans []string
	for i := s[0]; i <= s[3]; i++ {
		for j := s[1]; j <= s[4]; j++ {
			ans = append(ans, string(i)+string(j))
		}
	}
	return ans
}
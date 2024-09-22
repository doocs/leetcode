func reportSpam(message []string, bannedWords []string) bool {
	s := map[string]bool{}
	for _, w := range bannedWords {
		s[w] = true
	}
	cnt := 0
	for _, w := range message {
		if s[w] {
			cnt++
			if cnt >= 2 {
				return true
			}
		}
	}
	return false
}

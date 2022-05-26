func printVertically(s string) []string {
	words := strings.Split(s, " ")
	m := len(words)
	var n int
	for _, word := range words {
		if n < len(word) {
			n = len(word)
		}
	}
	var ans []string
	for j := 0; j < n; j++ {
		var t []byte
		for i := 0; i < m; i++ {
			word := words[i]
			if j < len(word) {
				t = append(t, word[j])
			} else {
				t = append(t, ' ')
			}
		}
		s = string(t)
		ans = append(ans, rstrip(s))
	}
	return ans
}

func rstrip(s string) string {
	for i := len(s) - 1; i >= 0; i-- {
		if s[i] != ' ' {
			return s[:i+1]
		}
	}
	return s
}
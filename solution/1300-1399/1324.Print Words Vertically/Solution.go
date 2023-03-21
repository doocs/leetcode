func printVertically(s string) (ans []string) {
	words := strings.Split(s, " ")
	n := 0
	for _, w := range words {
		n = max(n, len(w))
	}
	for j := 0; j < n; j++ {
		t := []byte{}
		for _, w := range words {
			if j < len(w) {
				t = append(t, w[j])
			} else {
				t = append(t, ' ')
			}
		}
		for len(t) > 0 && t[len(t)-1] == ' ' {
			t = t[:len(t)-1]
		}
		ans = append(ans, string(t))
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
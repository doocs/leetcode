func clearStars(s string) string {
	g := make([][]int, 26)
	n := len(s)
	rem := make([]bool, n)
	for i, c := range s {
		if c == '*' {
			rem[i] = true
			for j := 0; j < 26; j++ {
				if len(g[j]) > 0 {
					rem[g[j][len(g[j])-1]] = true
					g[j] = g[j][:len(g[j])-1]
					break
				}
			}
		} else {
			g[c-'a'] = append(g[c-'a'], i)
		}
	}
	ans := []byte{}
	for i := range s {
		if !rem[i] {
			ans = append(ans, s[i])
		}
	}
	return string(ans)
}
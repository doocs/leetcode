func minimizeConcatenatedLength(words []string) int {
	n := len(words)
	f := make([][26][26]int, n+1)
	for i := n - 1; i > 0; i-- {
		s := words[i]
		m := len(s)
		c := int(s[0] - 'a')
		d := int(s[m-1] - 'a')
		for a := 0; a < 26; a++ {
			for b := 0; b < 26; b++ {
				x := f[i+1][a][d]
				y := f[i+1][c][b]
				if c == b {
					x--
				}
				if d == a {
					y--
				}
				f[i][a][b] = m + min(x, y)
			}
		}
	}
	a := int(words[0][0] - 'a')
	b := int(words[0][len(words[0])-1] - 'a')
	return len(words[0]) + f[1][a][b]
}

func finalString(s string) string {
	t := []rune{}
	for _, c := range s {
		if c == 'i' {
			for i, j := 0, len(t)-1; i < j; i, j = i+1, j-1 {
				t[i], t[j] = t[j], t[i]
			}
		} else {
			t = append(t, c)
		}
	}
	return string(t)
}
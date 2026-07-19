func rearrangeString(s string, x byte, y byte) string {
	t := []byte(s)
	i := 0
	for j, c := range t {
		if c == y {
			t[i], t[j] = t[j], t[i]
			i++
		}
	}
	return string(t)
}

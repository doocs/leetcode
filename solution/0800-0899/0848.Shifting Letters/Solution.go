func shiftingLetters(s string, shifts []int) string {
	t := 0
	n := len(s)
	cs := []byte(s)
	for i := n - 1; i >= 0; i-- {
		t += shifts[i]
		j := (int(cs[i]-'a') + t) % 26
		cs[i] = byte('a' + j)
	}
	return string(cs)
}
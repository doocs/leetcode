func reverseWords(s string) string {
	t := []byte(s)
	for i := 0; i < len(t); i++ {
		j := i
		for j < len(t) && t[j] != ' ' {
			j++
		}
		for st, ed := i, j-1; st < ed; st, ed = st+1, ed-1 {
			t[st], t[ed] = t[ed], t[st]
		}
		i = j
	}
	return string(t)
}
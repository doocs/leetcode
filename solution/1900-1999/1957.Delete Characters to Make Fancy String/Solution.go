func makeFancyString(s string) string {
	ans := []byte{}
	for i, ch := range s {
		if c := byte(ch); i < 2 || c != s[i-1] || c != s[i-2] {
			ans = append(ans, c)
		}
	}
	return string(ans)
}

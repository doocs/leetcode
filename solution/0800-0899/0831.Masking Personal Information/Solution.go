func maskPII(s string) string {
	i := strings.Index(s, "@")
	if i != -1 {
		s = strings.ToLower(s)
		return s[0:1] + "*****" + s[i-1:]
	}
	t := []rune{}
	for _, c := range s {
		if c >= '0' && c <= '9' {
			t = append(t, c)
		}
	}
	s = string(t)
	cnt := len(s) - 10
	suf := "***-***-" + s[len(s)-4:]
	if cnt == 0 {
		return suf
	}
	return "+" + strings.Repeat("*", cnt) + "-" + suf
}
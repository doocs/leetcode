func hasMatch(s string, p string) bool {
	i := 0
	for _, t := range strings.Split(p, "*") {
		j := strings.Index(s[i:], t)
		if j == -1 {
			return false
		}
		i += j + len(t)
	}
	return true
}

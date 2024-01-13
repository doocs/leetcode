func replaceSpace(s string) string {
	ans := strings.Builder{}
	for _, c := range s {
		if c == ' ' {
			ans.WriteString("%20")
		} else {
			ans.WriteRune(c)
		}
	}
	return ans.String()
}
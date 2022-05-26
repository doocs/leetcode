func reverseWords(s string) string {
	s = strings.Trim(s, " ")
	n := len(s) - 1
	builder := new(strings.Builder)
	for i, j := n, n; i >= 0; j = i {
		for i >= 0 && s[i] != ' ' {
			i--
		}
		if builder.Len() != 0 {
			builder.WriteRune(' ')
		}
		builder.WriteString(s[i+1 : j+1])
		for i >= 0 && s[i] == ' ' {
			i--
		}
	}
	return builder.String()
}

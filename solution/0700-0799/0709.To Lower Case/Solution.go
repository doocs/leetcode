func toLowerCase(s string) string {
	sb := &strings.Builder{}
	sb.Grow(len(s))
	for _, c := range s {
		if c >= 'A' && c <= 'Z' {
			c |= 32
		}
		sb.WriteRune(c)
	}
	return sb.String()
}
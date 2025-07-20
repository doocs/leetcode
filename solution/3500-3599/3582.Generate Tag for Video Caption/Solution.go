func generateTag(caption string) string {
	words := strings.Fields(caption)
	var builder strings.Builder
	builder.WriteString("#")

	for i, word := range words {
		word = strings.ToLower(word)
		if i == 0 {
			builder.WriteString(word)
		} else {
			runes := []rune(word)
			if len(runes) > 0 {
				runes[0] = unicode.ToUpper(runes[0])
			}
			builder.WriteString(string(runes))
		}
		if builder.Len() >= 100 {
			break
		}
	}

	ans := builder.String()
	if len(ans) > 100 {
		ans = ans[:100]
	}
	return ans
}

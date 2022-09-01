func repeatedSubstringPattern(s string) bool {
	return strings.Index(s[1:]+s, s) < len(s)-1
}
func makeStringsEqual(s string, target string) bool {
	return strings.Contains(s, "1") == strings.Contains(target, "1")
}
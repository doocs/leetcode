func largestGoodInteger(num string) string {
	for c := '9'; c >= '0'; c-- {
		if s := strings.Repeat(string(c), 3); strings.Contains(num, s) {
			return s
		}
	}
	return ""
}
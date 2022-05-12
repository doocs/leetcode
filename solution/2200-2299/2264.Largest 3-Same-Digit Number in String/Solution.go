func largestGoodInteger(num string) string {
	for c := '9'; c >= '0'; c-- {
		t := strings.Repeat(string(c), 3)
		if strings.Contains(num, t) {
			return t
		}
	}
	return ""
}
func queryString(s string, n int) bool {
	for i := n; i > n/2; i-- {
		if !strings.Contains(s, strconv.FormatInt(int64(i), 2)) {
			return false
		}
	}
	return true
}

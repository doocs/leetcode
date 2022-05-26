func checkRecord(s string) bool {
	return strings.Count(s, "A") < 2 && !strings.Contains(s, "LLL")
}

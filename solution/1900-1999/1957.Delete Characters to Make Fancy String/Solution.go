func makeFancyString(s string) string {
	ans := []rune{}
	for _, c := range s {
		if n := len(ans); n < 2 || c != ans[n-1] || c != ans[n-2] {
			ans = append(ans, c)
		}
	}
	return string(ans)
}

func removeStars(s string) string {
	ans := []rune{}
	for _, c := range s {
		if c == '*' {
			ans = ans[:len(ans)-1]
		} else {
			ans = append(ans, c)
		}
	}
	return string(ans)
}
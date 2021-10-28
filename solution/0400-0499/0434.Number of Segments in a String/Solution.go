func countSegments(s string) int {
	res := 0
	for i, c := range s {
		if c != ' ' && (i == 0 || s[i-1] == ' ') {
			res++
		}
	}
	return res
}
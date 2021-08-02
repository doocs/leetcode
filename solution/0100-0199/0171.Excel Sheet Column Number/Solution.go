func titleToNumber(columnTitle string) int {
	res := 0
	for _, c := range columnTitle {
		res = res*26 + int(c-'A'+1)
	}
	return res
}
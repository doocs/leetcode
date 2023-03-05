func convertToTitle(columnNumber int) string {
	res := []rune{}
	for columnNumber != 0 {
		columnNumber -= 1
		res = append([]rune{rune(columnNumber%26 + int('A'))}, res...)
		columnNumber /= 26
	}
	return string(res)
}

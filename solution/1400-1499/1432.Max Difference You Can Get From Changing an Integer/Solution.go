func maxDiff(num int) int {
	a, b := num, num
	s := strconv.Itoa(num)
	for i := range s {
		if s[i] != '9' {
			a, _ = strconv.Atoi(strings.ReplaceAll(s, string(s[i]), "9"))
			break
		}
	}
	if s[0] > '1' {
		b, _ = strconv.Atoi(strings.ReplaceAll(s, string(s[0]), "1"))
	} else {
		for i := 1; i < len(s); i++ {
			if s[i] != '0' && s[i] != '1' {
				b, _ = strconv.Atoi(strings.ReplaceAll(s, string(s[i]), "0"))
				break
			}
		}
	}
	return a - b
}
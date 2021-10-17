func areNumbersAscending(s string) bool {
	curr := 0
	for _, t := range strings.Split(s, " ") {
		if unicode.IsDigit(rune(t[0])) {
			x, _ := strconv.Atoi(t)
			if curr >= x {
				return false
			}
			curr = x
		}
	}
	return true
}
func areNumbersAscending(s string) bool {
	pre := 0
	for _, t := range strings.Split(s, " ") {
		if t[0] <= '9' {
			cur, _ := strconv.Atoi(t)
			if pre >= cur {
				return false
			}
			pre = cur
		}
	}
	return true
}
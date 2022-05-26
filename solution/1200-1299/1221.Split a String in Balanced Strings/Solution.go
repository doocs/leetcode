func balancedStringSplit(s string) int {
	n, res := 0, 0
	for _, c := range s {
		if c == 'L' {
			n++
		} else {
			n--
		}
		if n == 0 {
			res++
		}
	}
	return res
}
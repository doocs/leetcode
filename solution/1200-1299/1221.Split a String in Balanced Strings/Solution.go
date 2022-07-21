func balancedStringSplit(s string) int {
	ans, l := 0, 0
	for _, c := range s {
		if c == 'L' {
			l++
		} else {
			l--
		}
		if l == 0 {
			ans++
		}
	}
	return ans
}
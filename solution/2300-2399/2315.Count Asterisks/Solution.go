func countAsterisks(s string) int {
	ans, t := 0, 0
	for _, c := range s {
		if c == '|' {
			t ^= 1
		} else if c == '*' {
			if t == 0 {
				ans++
			}
		}
	}
	return ans
}
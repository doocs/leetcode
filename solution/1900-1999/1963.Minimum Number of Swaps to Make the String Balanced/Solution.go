func minSwaps(s string) int {
	x := 0
	for _, c := range s {
		if c == '[' {
			x++
		} else if x > 0 {
			x--
		}
	}
	return (x + 1) / 2
}
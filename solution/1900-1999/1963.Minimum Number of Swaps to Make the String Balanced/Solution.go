func minSwaps(s string) int {
	ans := 0
	for _, c := range s {
		if c == '[' {
			ans++
		} else if ans > 0 {
			ans--
		}
	}
	return (ans + 1) >> 1
}
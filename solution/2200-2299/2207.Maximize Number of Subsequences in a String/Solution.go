func maximumSubsequenceCount(text string, pattern string) (ans int64) {
	x, y := 0, 0
	for _, c := range text {
		if byte(c) == pattern[1] {
			y++
			ans += int64(x)
		}
		if byte(c) == pattern[0] {
			x++
		}
	}
	ans += int64(max(x, y))
	return
}

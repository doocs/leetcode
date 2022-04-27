func minBitFlips(start int, goal int) int {
	t := start ^ goal
	ans := 0
	for t != 0 {
		ans += t & 1
		t >>= 1
	}
	return ans
}
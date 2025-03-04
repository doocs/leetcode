func minBitFlips(start int, goal int) int {
	return bits.OnesCount(uint(start ^ goal))
}

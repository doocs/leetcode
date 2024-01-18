func kthGrammar(n int, k int) int {
	return bits.OnesCount(uint(k-1)) & 1
}
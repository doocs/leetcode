func findComplement(num int) int {
	return num ^ ((1 << bits.Len(uint(num))) - 1)
}
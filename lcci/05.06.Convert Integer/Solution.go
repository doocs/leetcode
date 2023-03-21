func convertInteger(A int, B int) int {
	return bits.OnesCount32(uint32(A ^ B))
}
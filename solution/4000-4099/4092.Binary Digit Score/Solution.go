func calculateScore(a int, b int) int {
	s1 := bits.OnesCount(uint(a))
	s2 := bits.OnesCount(uint(b))
	if s1 >= s2 {
		return s1 * s2
	}
	return s2 / s1
}

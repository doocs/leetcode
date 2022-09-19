func minimumMoves(s string) int {
	ans := 0
	for i := 0; i < len(s); i++ {
		if s[i] == 'X' {
			ans++
			i += 2
		}
	}
	return ans
}
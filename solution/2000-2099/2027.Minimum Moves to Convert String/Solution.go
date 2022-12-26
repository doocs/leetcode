func minimumMoves(s string) (ans int) {
	for i := 0; i < len(s); i++ {
		if s[i] == 'X' {
			ans++
			i += 2
		}
	}
	return
}
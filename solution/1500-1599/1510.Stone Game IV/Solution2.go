func winnerSquareGame(n int) bool {
	f := make([]bool, n+1)
	for i := 1; i <= n; i++ {
		for j := 1; j <= i/j; j++ {
			if !f[i-j*j] {
				f[i] = true
				break
			}
		}
	}
	return f[n]
}
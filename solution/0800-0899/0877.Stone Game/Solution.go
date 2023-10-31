func stoneGame(piles []int) bool {
	n := len(piles)
	f := make([][]int, n)
	for i, x := range piles {
		f[i] = make([]int, n)
		f[i][i] = x
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			f[i][j] = max(piles[i]-f[i+1][j], piles[j]-f[i][j-1])
		}
	}
	return f[0][n-1] > 0
}
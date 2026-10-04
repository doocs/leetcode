func minimumWhiteTiles(floor string, numCarpets int, carpetLen int) int {
	n := len(floor)
	s := make([]int, n+1)
	for i := 0; i < n; i++ {
		s[i+1] = s[i]
		if floor[i] == '1' {
			s[i+1]++
		}
	}
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, numCarpets+1)
	}
	for i := n - 1; i >= 0; i-- {
		for j := 0; j <= numCarpets; j++ {
			if floor[i] == '0' {
				f[i][j] = f[i+1][j]
			} else if j == 0 {
				f[i][j] = s[n] - s[i]
			} else {
				cover := 0
				if i+carpetLen <= n {
					cover = f[i+carpetLen][j-1]
				}
				f[i][j] = min(1+f[i+1][j], cover)
			}
		}
	}
	return f[0][numCarpets]
}

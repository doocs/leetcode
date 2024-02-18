func mostFrequentPrime(mat [][]int) int {
	m, n := len(mat), len(mat[0])
	cnt := make(map[int]int)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			for a := -1; a <= 1; a++ {
				for b := -1; b <= 1; b++ {
					if a == 0 && b == 0 {
						continue
					}
					x, y, v := i+a, j+b, mat[i][j]
					for x >= 0 && x < m && y >= 0 && y < n {
						v = v*10 + mat[x][y]
						if isPrime(v) {
							cnt[v]++
						}
						x += a
						y += b
					}
				}
			}
		}
	}
	ans, mx := -1, 0
	for v, x := range cnt {
		if mx < x || (mx == x && ans < v) {
			mx = x
			ans = v
		}
	}
	return ans
}

func isPrime(n int) bool {
	for i := 2; i <= n/i; i++ {
		if n%i == 0 {
			return false
		}
	}
	return true
}
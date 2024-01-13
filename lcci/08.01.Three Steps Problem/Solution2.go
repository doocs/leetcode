const mod = 1e9 + 7

func waysToStep(n int) (ans int) {
	if n < 4 {
		return int(math.Pow(2, float64(n-1)))
	}
	a := [][]int{{1, 1, 0}, {1, 0, 1}, {1, 0, 0}}
	res := pow(a, n-4)
	for _, x := range res[0] {
		ans = (ans + x) % mod
	}
	return
}

func mul(a, b [][]int) [][]int {
	m, n := len(a), len(b[0])
	c := make([][]int, m)
	for i := range c {
		c[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			for k := 0; k < len(b); k++ {
				c[i][j] = (c[i][j] + a[i][k]*b[k][j]%mod) % mod
			}
		}
	}
	return c
}

func pow(a [][]int, n int) [][]int {
	res := [][]int{{4, 2, 1}}
	for n > 0 {
		if n&1 == 1 {
			res = mul(res, a)
		}
		a = mul(a, a)
		n >>= 1
	}
	return res
}
type matrix [2][2]int

func climbStairs(n int) int {
	a := matrix{{1, 1}, {1, 0}}
	return pow(a, n-1)[0][0]
}

func mul(a, b matrix) (c matrix) {
	m, n := len(a), len(b[0])
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			for k := 0; k < len(a[0]); k++ {
				c[i][j] += a[i][k] * b[k][j]
			}
		}
	}
	return
}

func pow(a matrix, n int) matrix {
	res := matrix{{1, 1}, {0, 0}}
	for n > 0 {
		if n&1 == 1 {
			res = mul(res, a)
		}
		a = mul(a, a)
		n >>= 1
	}
	return res
}
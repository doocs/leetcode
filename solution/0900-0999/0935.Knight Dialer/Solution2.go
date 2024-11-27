const mod = 1e9 + 7

func knightDialer(n int) int {
	base := [][]int{
		{0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
		{0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
		{0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
		{1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{1, 1, 0, 0, 0, 0, 0, 1, 0, 0},
		{0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
		{0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
		{0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
	}

	res := pow(base, n-1)
	ans := 0
	for _, x := range res[0] {
		ans = (ans + x) % mod
	}
	return ans
}

func mul(a, b [][]int) [][]int {
	m := len(a)
	n := len(b[0])
	c := make([][]int, m)
	for i := range c {
		c[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			for k := 0; k < len(b); k++ {
				c[i][j] = (c[i][j] + a[i][k]*b[k][j]) % mod
			}
		}
	}
	return c
}

func pow(a [][]int, n int) [][]int {
	size := len(a)
	res := make([][]int, 1)
	res[0] = make([]int, size)
	for i := 0; i < size; i++ {
		res[0][i] = 1
	}

	for n > 0 {
		if n%2 == 1 {
			res = mul(res, a)
		}
		a = mul(a, a)
		n /= 2
	}

	return res
}

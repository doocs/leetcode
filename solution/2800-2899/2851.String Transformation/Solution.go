func numberOfWays(s string, t string, k int64) int {
	const mod = 1_000_000_007
	add := func(x, y int) int {
		x += y
		if x >= mod {
			x -= mod
		}
		return x
	}
	mul := func(x, y int) int {
		return int(int64(x) * int64(y) % mod)
	}
	getZ := func(s string) []int {
		n := len(s)
		z := make([]int, n)
		left, right := 0, 0
		for i := 1; i < n; i++ {
			if i <= right && z[i-left] <= right-i {
				z[i] = z[i-left]
			} else {
				zI := right - i + 1
				if zI < 0 {
					zI = 0
				}
				for i+zI < n && s[i+zI] == s[zI] {
					zI++
				}
				z[i] = zI
			}
			if i+z[i]-1 > right {
				left = i
				right = i + z[i] - 1
			}
		}
		return z
	}
	matrixMultiply := func(a, b [][]int) [][]int {
		m, n, p := len(a), len(a[0]), len(b[0])
		r := make([][]int, m)
		for i := range r {
			r[i] = make([]int, p)
			for j := 0; j < p; j++ {
				for k := 0; k < n; k++ {
					r[i][j] = add(r[i][j], mul(a[i][k], b[k][j]))
				}
			}
		}
		return r
	}
	matrixPower := func(a [][]int, y int64) [][]int {
		n := len(a)
		r := make([][]int, n)
		x := make([][]int, n)
		for i := 0; i < n; i++ {
			r[i] = make([]int, n)
			x[i] = make([]int, n)
			r[i][i] = 1
			copy(x[i], a[i])
		}
		for y > 0 {
			if y&1 == 1 {
				r = matrixMultiply(r, x)
			}
			x = matrixMultiply(x, x)
			y >>= 1
		}
		return r
	}
	n := len(s)
	dp := matrixPower([][]int{{0, 1}, {n - 1, n - 2}}, k)[0]
	s += t + t
	z := getZ(s)
	result := 0
	for i := n; i < n+n; i++ {
		if z[i] >= n {
			if i-n == 0 {
				result = add(result, dp[0])
			} else {
				result = add(result, dp[1])
			}
		}
	}
	return result
}

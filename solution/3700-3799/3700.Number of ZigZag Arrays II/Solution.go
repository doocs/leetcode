func zigZagArrays(n int, l int, r int) int {
	const mod = 1_000_000_007
	m := r - l + 1
	size := 2 * m
	trans := make([][]int, size)
	for i := range trans {
		trans[i] = make([]int, size)
	}
	for x := 0; x < m; x++ {
		for y := 0; y < x; y++ {
			trans[y][m+x] = 1
		}
	}
	for x := 0; x < m; x++ {
		for y := x + 1; y < m; y++ {
			trans[m+y][x] = 1
		}
	}
	power := matrixPow(trans, n-1, mod)
	init := make([]int, size)
	for i := range init {
		init[i] = 1
	}
	result := mulVec(power, init, mod)
	ans := 0
	for _, v := range result {
		ans = (ans + v) % mod
	}
	return ans
}

func mulVec(mat [][]int, vec []int, mod int) []int {
	n := len(mat)
	res := make([]int, n)
	for i := 0; i < n; i++ {
		sum := 0
		for j := 0; j < n; j++ {
			sum = (sum + mat[i][j]*vec[j]) % mod
		}
		res[i] = sum
	}
	return res
}

func mulMat(a, b [][]int, mod int) [][]int {
	n := len(a)
	res := make([][]int, n)
	for i := range res {
		res[i] = make([]int, n)
	}
	for i := 0; i < n; i++ {
		for k := 0; k < n; k++ {
			if a[i][k] == 0 {
				continue
			}
			aik := a[i][k]
			for j := 0; j < n; j++ {
				if b[k][j] == 0 {
					continue
				}
				res[i][j] = (res[i][j] + aik*b[k][j]) % mod
			}
		}
	}
	return res
}

func matrixPow(mat [][]int, exp int, mod int) [][]int {
	n := len(mat)
	res := make([][]int, n)
	for i := range res {
		res[i] = make([]int, n)
		res[i][i] = 1
	}
	for exp > 0 {
		if exp&1 == 1 {
			res = mulMat(res, mat, mod)
		}
		mat = mulMat(mat, mat, mod)
		exp >>= 1
	}
	return res
}

func lengthAfterTransformations(s string, t int, nums []int) int {
	const MOD = 1e9 + 7
	const M = 26

	cnt := make([]int, M)
	for _, c := range s {
		cnt[int(c-'a')]++
	}

	matrix := make([][]int, M)
	for i := 0; i < M; i++ {
		matrix[i] = make([]int, M)
		for j := 1; j <= nums[i]; j++ {
			matrix[i][(i+j)%M] = 1
		}
	}

	matmul := func(a, b [][]int) [][]int {
		n, p, q := len(a), len(b), len(b[0])
		res := make([][]int, n)
		for i := 0; i < n; i++ {
			res[i] = make([]int, q)
			for k := 0; k < p; k++ {
				if a[i][k] != 0 {
					for j := 0; j < q; j++ {
						res[i][j] = (res[i][j] + a[i][k]*b[k][j]%MOD) % MOD
					}
				}
			}
		}
		return res
	}

	matpow := func(mat [][]int, power int) [][]int {
		res := make([][]int, M)
		for i := 0; i < M; i++ {
			res[i] = make([]int, M)
			res[i][i] = 1
		}
		for power > 0 {
			if power%2 == 1 {
				res = matmul(res, mat)
			}
			mat = matmul(mat, mat)
			power /= 2
		}
		return res
	}

	cntMat := [][]int{make([]int, M)}
	copy(cntMat[0], cnt)

	factor := matpow(matrix, t)
	result := matmul(cntMat, factor)

	ans := 0
	for _, v := range result[0] {
		ans = (ans + v) % MOD
	}
	return ans
}

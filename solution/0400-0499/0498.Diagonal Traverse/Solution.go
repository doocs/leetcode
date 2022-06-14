func findDiagonalOrder(mat [][]int) []int {
	m, n := len(mat), len(mat[0])
	var ans []int
	for k := 0; k < m+n-1; k++ {
		var t []int
		i, j := k-n+1, n-1
		if k < n {
			i, j = 0, k
		}
		for i < m && j >= 0 {
			t = append(t, mat[i][j])
			i++
			j--
		}
		if k%2 == 0 {
			p, q := 0, len(t)-1
			for p < q {
				t[p], t[q] = t[q], t[p]
				p++
				q--
			}
		}
		for _, v := range t {
			ans = append(ans, v)
		}
	}
	return ans
}
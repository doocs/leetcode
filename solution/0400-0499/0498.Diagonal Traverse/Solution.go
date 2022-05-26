func findDiagonalOrder(mat [][]int) []int {
	m, n := len(mat), len(mat[0])
	var ans []int
	for i := 0; i < m+n; i++ {
		var t []int
		r, c := i-n+1, n-1
		if i < n {
			r, c = 0, i
		}
		for r < m && c >= 0 {
			t = append(t, mat[r][c])
			r += 1
			c -= 1
		}
		if i%2 == 0 {
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
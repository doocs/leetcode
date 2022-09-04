func numSpecial(mat [][]int) int {
	m, n := len(mat), len(mat[0])
	r, c := make([]int, m), make([]int, n)
	for i, row := range mat {
		for j, v := range row {
			r[i] += v
			c[j] += v
		}
	}
	ans := 0
	for i, x := range r {
		for j, y := range c {
			if mat[i][j] == 1 && x == 1 && y == 1 {
				ans++
			}
		}
	}
	return ans
}
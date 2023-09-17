func multiply(mat1 [][]int, mat2 [][]int) [][]int {
	m, n := len(mat1), len(mat2[0])
	ans := make([][]int, m)
	for i := range ans {
		ans[i] = make([]int, n)
	}
	f := func(mat [][]int) [][][2]int {
		m, n := len(mat), len(mat[0])
		g := make([][][2]int, m)
		for i := range g {
			g[i] = make([][2]int, 0, n)
			for j := range mat[i] {
				if mat[i][j] != 0 {
					g[i] = append(g[i], [2]int{j, mat[i][j]})
				}
			}
		}
		return g
	}
	g1, g2 := f(mat1), f(mat2)
	for i := range g1 {
		for _, p := range g1[i] {
			k, x := p[0], p[1]
			for _, q := range g2[k] {
				j, y := q[0], q[1]
				ans[i][j] += x * y
			}
		}
	}
	return ans
}
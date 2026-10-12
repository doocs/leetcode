func numSubmat(mat [][]int) (ans int) {
	m, n := len(mat), len(mat[0])
	g := make([][]int, m)
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			if mat[i][j] == 1 {
				if j == 0 {
					g[i][j] = 1
				} else {
					g[i][j] = 1 + g[i][j-1]
				}
			}
		}
	}
	for j := 0; j < n; j++ {
		stk := [][3]int{}
		for i := 0; i < m; i++ {
			cur := g[i][j]
			for len(stk) > 0 && stk[len(stk)-1][0] >= cur {
				stk = stk[:len(stk)-1]
			}
			cnt := cur * (i + 1)
			if len(stk) > 0 {
				cnt = stk[len(stk)-1][2] + cur*(i-stk[len(stk)-1][1])
			}
			ans += cnt
			stk = append(stk, [3]int{cur, i, cnt})
		}
	}
	return
}

func minFlips(mat [][]int) int {
	m, n := len(mat), len(mat[0])
	state := 0
	for i, row := range mat {
		for j, v := range row {
			if v == 1 {
				state |= 1 << (i*n + j)
			}
		}
	}
	q := []int{state}
	vis := map[int]bool{state: true}
	ans := 0
	dirs := []int{0, -1, 0, 1, 0, 0}
	for len(q) > 0 {
		for t := len(q); t > 0; t-- {
			state = q[0]
			if state == 0 {
				return ans
			}
			q = q[1:]
			for i := 0; i < m; i++ {
				for j := 0; j < n; j++ {
					nxt := state
					for k := 0; k < 5; k++ {
						x, y := i+dirs[k], j+dirs[k+1]
						if x < 0 || x >= m || y < 0 || y >= n {
							continue
						}
						if (nxt & (1 << (x*n + y))) != 0 {
							nxt -= 1 << (x*n + y)
						} else {
							nxt |= 1 << (x*n + y)
						}
					}
					if !vis[nxt] {
						vis[nxt] = true
						q = append(q, nxt)
					}
				}
			}
		}
		ans++
	}
	return -1
}
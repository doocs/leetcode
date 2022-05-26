func removeOnes(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	state := 0
	for i, row := range grid {
		for j, v := range row {
			if v == 1 {
				state |= 1 << (i*n + j)
			}
		}
	}
	q := []int{state}
	vis := map[int]bool{state: true}
	ans := 0
	for len(q) > 0 {
		for k := len(q); k > 0; k-- {
			state = q[0]
			if state == 0 {
				return ans
			}
			q = q[1:]
			for i, row := range grid {
				for j, v := range row {
					if v == 0 {
						continue
					}
					nxt := state
					for r := 0; r < m; r++ {
						nxt &= ^(1 << (r*n + j))
					}
					for c := 0; c < n; c++ {
						nxt &= ^(1 << (i*n + c))
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
func minMoves(classroom []string, energy int) int {
	m, n := len(classroom), len(classroom[0])
	d := make([][]int, m)
	for i := range d {
		d[i] = make([]int, n)
	}
	x, y, cnt := 0, 0, 0
	for i := 0; i < m; i++ {
		row := classroom[i]
		for j := 0; j < n; j++ {
			c := row[j]
			if c == 'S' {
				x, y = i, j
			} else if c == 'L' {
				d[i][j] = cnt
				cnt++
			}
		}
	}
	if cnt == 0 {
		return 0
	}

	vis := make([][][][]bool, m)
	for i := range vis {
		vis[i] = make([][][]bool, n)
		for j := range vis[i] {
			vis[i][j] = make([][]bool, energy+1)
			for e := range vis[i][j] {
				vis[i][j][e] = make([]bool, 1<<cnt)
			}
		}
	}
	type state struct {
		i, j, curEnergy, mask int
	}
	q := []state{{x, y, energy, (1 << cnt) - 1}}
	vis[x][y][energy][(1<<cnt)-1] = true
	dirs := []int{-1, 0, 1, 0, -1}
	ans := 0

	for len(q) > 0 {
		t := q
		q = []state{}
		for _, s := range t {
			i, j, curEnergy, mask := s.i, s.j, s.curEnergy, s.mask
			if mask == 0 {
				return ans
			}
			if curEnergy <= 0 {
				continue
			}
			for k := 0; k < 4; k++ {
				nx, ny := i+dirs[k], j+dirs[k+1]
				if nx >= 0 && nx < m && ny >= 0 && ny < n && classroom[nx][ny] != 'X' {
					var nxtEnergy int
					if classroom[nx][ny] == 'R' {
						nxtEnergy = energy
					} else {
						nxtEnergy = curEnergy - 1
					}
					nxtMask := mask
					if classroom[nx][ny] == 'L' {
						nxtMask &= ^(1 << d[nx][ny])
					}
					if !vis[nx][ny][nxtEnergy][nxtMask] {
						vis[nx][ny][nxtEnergy][nxtMask] = true
						q = append(q, state{nx, ny, nxtEnergy, nxtMask})
					}
				}
			}
		}
		ans++
	}
	return -1
}

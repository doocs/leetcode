func shortestPathAllKeys(grid []string) int {
	m, n := len(grid), len(grid[0])
	var k, si, sj int
	for i, row := range grid {
		for j, c := range row {
			if c >= 'a' && c <= 'z' {
				// 累加钥匙数量
				k++
			} else if c == '@' {
				// 起点
				si, sj = i, j
			}
		}
	}
	type tuple struct{ i, j, state int }
	q := []tuple{tuple{si, sj, 0}}
	vis := map[tuple]bool{tuple{si, sj, 0}: true}
	dirs := []int{-1, 0, 1, 0, -1}
	ans := 0
	for len(q) > 0 {
		for t := len(q); t > 0; t-- {
			p := q[0]
			q = q[1:]
			i, j, state := p.i, p.j, p.state
			// 找到所有钥匙，返回当前步数
			if state == 1<<k-1 {
				return ans
			}
			// 往四个方向搜索
			for h := 0; h < 4; h++ {
				x, y := i+dirs[h], j+dirs[h+1]
				// 在边界范围内
				if x >= 0 && x < m && y >= 0 && y < n {
					c := grid[x][y]
					// 是墙，或者是锁，但此时没有对应的钥匙，无法通过
					if c == '#' || (c >= 'A' && c <= 'Z' && (state>>(c-'A')&1 == 0)) {
						continue
					}
					nxt := state
					// 是钥匙，更新状态
					if c >= 'a' && c <= 'z' {
						nxt |= 1 << (c - 'a')
					}
					// 此状态未访问过，入队
					if !vis[tuple{x, y, nxt}] {
						vis[tuple{x, y, nxt}] = true
						q = append(q, tuple{x, y, nxt})
					}
				}
			}
		}
		// 步数加一
		ans++
	}
	return -1
}
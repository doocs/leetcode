func findPath(grid [][]int, k int) [][]int {
	_ = k
	m := len(grid)
	n := len(grid[0])
	var st uint64
	path := [][]int{}
	dirs := []int{-1, 0, 1, 0, -1}

	f := func(i, j int) int { return i*n + j }

	var dfs func(int, int, int) bool
	dfs = func(i, j, v int) bool {
		path = append(path, []int{i, j})
		if len(path) == m*n {
			return true
		}
		idx := f(i, j)
		st |= 1 << idx
		if grid[i][j] == v {
			v++
		}
		for t := 0; t < 4; t++ {
			a, b := dirs[t], dirs[t+1]
			x, y := i+a, j+b
			if 0 <= x && x < m && 0 <= y && y < n {
				idx2 := f(x, y)
				if (st>>idx2)&1 == 0 && (grid[x][y] == 0 || grid[x][y] == v) {
					if dfs(x, y, v) {
						return true
					}
				}
			}
		}
		path = path[:len(path)-1]
		st ^= 1 << idx
		return false
	}

	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 0 || grid[i][j] == 1 {
				if dfs(i, j, 1) {
					return path
				}
				path = path[:0]
				st = 0
			}
		}
	}
	return [][]int{}
}

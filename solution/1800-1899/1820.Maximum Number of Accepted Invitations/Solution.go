func maximumInvitations(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	var vis map[int]bool
	match := make([]int, n)
	for i := range match {
		match[i] = -1
	}
	var find func(i int) bool
	find = func(i int) bool {
		for j, v := range grid[i] {
			if v == 1 && !vis[j] {
				vis[j] = true
				if match[j] == -1 || find(match[j]) {
					match[j] = i
					return true
				}
			}
		}
		return false
	}
	ans := 0
	for i := 0; i < m; i++ {
		vis = map[int]bool{}
		if find(i) {
			ans++
		}
	}
	return ans
}
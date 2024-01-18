func numberOfCleanRooms(room [][]int) (ans int) {
	m, n := len(room), len(room[0])
	vis := make([][][4]bool, m)
	for i := range vis {
		vis[i] = make([][4]bool, n)
	}
	dirs := [5]int{0, 1, 0, -1, 0}
	var i, j, k int
	for !vis[i][j][k] {
		vis[i][j][k] = true
		if room[i][j] == 0 {
			ans++
			room[i][j] = -1
		}
		x, y := i+dirs[k], j+dirs[k+1]
		if x >= 0 && x < m && y >= 0 && y < n && room[x][y] != 1 {
			i, j = x, y
		} else {
			k = (k + 1) % 4
		}
	}
	return
}
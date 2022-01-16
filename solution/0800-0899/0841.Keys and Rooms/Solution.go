func canVisitAllRooms(rooms [][]int) bool {
	n := len(rooms)
	vis := make(map[int]bool)
	var dfs func(u int)
	dfs = func(u int) {
		if u == n || vis[u] {
			return
		}
		vis[u] = true
		for _, v := range rooms[u] {
			dfs(v)
		}
	}
	dfs(0)
	return len(vis) == n
}
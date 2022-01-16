func canVisitAllRooms(rooms [][]int) bool {
	vis := make(map[int]bool)
	var dfs func(u int)
	dfs = func(u int) {
		if vis[u] {
			return
		}
		vis[u] = true
		for _, v := range rooms[u] {
			dfs(v)
		}
	}
	dfs(0)
	return len(vis) == len(rooms)
}
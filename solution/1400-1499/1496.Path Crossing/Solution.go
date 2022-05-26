func isPathCrossing(path string) bool {
	x, y := 0, 0
	vis := make(map[int]bool)
	vis[0] = true
	for _, c := range path {
		if c == 'N' {
			y++
		} else if c == 'S' {
			y--
		} else if c == 'E' {
			x++
		} else {
			x--
		}
		t := x*20000 + y
		if vis[t] {
			return true
		}
		vis[t] = true
	}
	return false
}
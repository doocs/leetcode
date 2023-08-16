func robot(command string, obstacles [][]int, x int, y int) bool {
	type pair struct{ i, j int }
	vis := map[pair]bool{}
	i, j := 0, 0
	vis[pair{0, 0}] = true
	for _, c := range command {
		if c == 'U' {
			j++
		} else {
			i++
		}
		vis[pair{i, j}] = true
	}
	k := min(x/i, y/j)
	if !vis[pair{x - k*i, y - k*j}] {
		return false
	}
	for _, ob := range obstacles {
		if ob[0] > x || ob[1] > y {
			continue
		}
		k := min(ob[0]/i, ob[1]/j)
		if vis[pair{ob[0] - k*i, ob[1] - k*j}] {
			return false
		}
	}
	return true
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
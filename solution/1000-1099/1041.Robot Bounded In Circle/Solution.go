func isRobotBounded(instructions string) bool {
	dist := [4]int{}
	k := 0
	for _, c := range instructions {
		if c == 'L' {
			k = (k + 1) % 4
		} else if c == 'R' {
			k = (k + 3) % 4
		} else {
			dist[k]++
		}
	}
	return (dist[0] == dist[2] && dist[1] == dist[3]) || k != 0
}
func escapeGhosts(ghosts [][]int, target []int) bool {
	tx, ty := target[0], target[1]
	for _, g := range ghosts {
		x, y := g[0], g[1]
		if abs(tx-x)+abs(ty-y) <= abs(tx)+abs(ty) {
			return false
		}
	}
	return true
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
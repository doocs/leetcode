func bestTower(towers [][]int, center []int, radius int) []int {
	cx, cy := center[0], center[1]
	idx := -1
	for i, a := range towers {
		x, y, q := a[0], a[1], a[2]
		dist := abs(x-cx) + abs(y-cy)
		if dist > radius {
			continue
		}
		if idx == -1 ||
			towers[idx][2] < q ||
			(towers[idx][2] == q &&
				(x < towers[idx][0] ||
					(x == towers[idx][0] && y < towers[idx][1]))) {
			idx = i
		}
	}
	if idx == -1 {
		return []int{-1, -1}
	}
	return []int{towers[idx][0], towers[idx][1]}
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

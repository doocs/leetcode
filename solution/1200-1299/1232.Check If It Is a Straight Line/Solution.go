func checkStraightLine(coordinates [][]int) bool {
	x1, y1 := coordinates[0][0], coordinates[0][1]
	x2, y2 := coordinates[1][0], coordinates[1][1]
	for i := 2; i < len(coordinates); i++ {
		x, y := coordinates[i][0], coordinates[i][1]
		if (x-x1)*(y2-y1) != (y-y1)*(x2-x1) {
			return false
		}
	}
	return true
}
func checkOverlap(radius int, xCenter int, yCenter int, x1 int, y1 int, x2 int, y2 int) bool {
	dx, dy := 0, 0
	if x1 > xCenter {
		dx = x1 - xCenter
	} else if x2 < xCenter {
		dx = x2 - xCenter
	}
	if y1 > yCenter {
		dy = y1 - yCenter
	} else if y2 < yCenter {
		dy = y2 - yCenter
	}
	return dx*dx+dy*dy <= radius*radius
}
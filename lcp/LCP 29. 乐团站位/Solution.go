func orchestraLayout(num int, xPos int, yPos int) int {
	n := int64(num)
	x := int64(xPos)
	y := int64(yPos)

	layer := min(min(x, y), min(n-1-x, n-1-y))
	side := n - 2*layer
	start := ((4*(layer%9))%9*((n-layer)%9))%9 + 1

	var offset int64
	if x == layer {
		offset = y - layer
	} else if y == n-layer-1 {
		offset = side - 1 + x - layer
	} else if x == n-layer-1 {
		offset = 2*side - 2 + n - layer - 1 - y
	} else {
		offset = 3*side - 3 + n - layer - 1 - x
	}

	return int((start-1+offset)%9 + 1)
}

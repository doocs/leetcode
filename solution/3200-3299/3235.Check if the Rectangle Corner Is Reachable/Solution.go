func canReachCorner(xCorner int, yCorner int, circles [][]int) bool {
	inCircle := func(x, y, cx, cy, r int) bool {
		dx, dy := x-cx, y-cy
		return dx*dx+dy*dy <= r*r
	}

	crossLeftTop := func(cx, cy, r int) bool {
		a := abs(cx) <= r && cy >= 0 && cy <= yCorner
		b := abs(cy-yCorner) <= r && cx >= 0 && cx <= xCorner
		return a || b
	}

	crossRightBottom := func(cx, cy, r int) bool {
		a := abs(cx-xCorner) <= r && cy >= 0 && cy <= yCorner
		b := abs(cy) <= r && cx >= 0 && cx <= xCorner
		return a || b
	}

	vis := make([]bool, len(circles))

	var dfs func(int) bool
	dfs = func(i int) bool {
		c := circles[i]
		x1, y1, r1 := c[0], c[1], c[2]
		if crossRightBottom(x1, y1, r1) {
			return true
		}
		vis[i] = true
		for j, c2 := range circles {
			if vis[j] {
				continue
			}
			x2, y2, r2 := c2[0], c2[1], c2[2]
			if (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2) > (r1+r2)*(r1+r2) {
				continue
			}
			if x1*r2+x2*r1 < (r1+r2)*xCorner && y1*r2+y2*r1 < (r1+r2)*yCorner && dfs(j) {
				return true
			}
		}
		return false
	}

	for i, c := range circles {
		x, y, r := c[0], c[1], c[2]
		if inCircle(0, 0, x, y, r) || inCircle(xCorner, yCorner, x, y, r) {
			return false
		}
		if !vis[i] && crossLeftTop(x, y, r) && dfs(i) {
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

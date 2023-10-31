func isReachableAtTime(sx int, sy int, fx int, fy int, t int) bool {
	if sx == fx && sy == fy {
		return t != 1
	}
	dx := abs(sx - fx)
	dy := abs(sy - fy)
	return max(dx, dy) <= t
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
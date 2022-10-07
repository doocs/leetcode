func minDistance(height int, width int, tree []int, squirrel []int, nuts [][]int) int {
	f := func(a, b []int) int {
		return abs(a[0]-b[0]) + abs(a[1]-b[1])
	}
	ans := math.MaxInt32
	s := 0
	for _, a := range nuts {
		s += f(a, tree)
	}
	s *= 2
	for _, a := range nuts {
		c := f(a, tree)
		d := f(a, squirrel) + c
		ans = min(ans, s+d-c*2)
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
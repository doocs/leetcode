func minDistance(height int, width int, tree []int, squirrel []int, nuts [][]int) int {
	tr, tc := tree[0], tree[1]
	sr, sc := squirrel[0], squirrel[1]
	s := 0
	for _, e := range nuts {
		s += abs(e[0]-tr) + abs(e[1]-tc)
	}
	s <<= 1
	ans := math.MaxInt32
	for _, e := range nuts {
		a := abs(e[0]-tr) + abs(e[1]-tc)
		b := abs(e[0]-sr) + abs(e[1]-sc)
		ans = min(ans, s-a+b)
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

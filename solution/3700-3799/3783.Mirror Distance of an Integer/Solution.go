func mirrorDistance(n int) int {
	reverse := func(x int) int {
		y := 0
		for ; x > 0; x /= 10 {
			y = y*10 + x%10
		}
		return y
	}
	return abs(n - reverse(n))
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func minSideJumps(obstacles []int) int {
	f := [3]int{1, 0, 1}
	const inf = 1 << 30
	for _, v := range obstacles[1:] {
		for j := 0; j < 3; j++ {
			if v == j+1 {
				f[j] = inf
				break
			}
		}
		x := min(f[0], min(f[1], f[2])) + 1
		for j := 0; j < 3; j++ {
			if v != j+1 {
				f[j] = min(f[j], x)
			}
		}
	}
	return min(f[0], min(f[1], f[2]))
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
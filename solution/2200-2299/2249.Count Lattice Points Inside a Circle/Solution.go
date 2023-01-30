func countLatticePoints(circles [][]int) (ans int) {
	mx, my := 0, 0
	for _, c := range circles {
		mx = max(mx, c[0]+c[2])
		my = max(my, c[1]+c[2])
	}
	for i := 0; i <= mx; i++ {
		for j := 0; j <= my; j++ {
			for _, c := range circles {
				dx, dy := i-c[0], j-c[1]
				if dx*dx+dy*dy <= c[2]*c[2] {
					ans++
					break
				}
			}
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
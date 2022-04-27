func countLatticePoints(circles [][]int) int {
	ans := 0
	for i := 0; i <= 200; i++ {
		for j := 0; j <= 200; j++ {
			for _, c := range circles {
				x, y, r := c[0]-i, c[1]-j, c[2]
				if x*x+y*y <= r*r {
					ans++
					break
				}
			}
		}
	}
	return ans
}
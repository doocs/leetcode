func bestCoordinate(towers [][]int, radius int) []int {
	ans := []int{0, 0}
	mx := 0
	for i := 0; i < 51; i++ {
		for j := 0; j < 51; j++ {
			t := 0
			for _, e := range towers {
				d := math.Sqrt(float64((i-e[0])*(i-e[0]) + (j-e[1])*(j-e[1])))
				if d <= float64(radius) {
					t += int(float64(e[2]) / (1 + d))
				}
			}
			if mx < t {
				mx = t
				ans = []int{i, j}
			}
		}
	}
	return ans
}
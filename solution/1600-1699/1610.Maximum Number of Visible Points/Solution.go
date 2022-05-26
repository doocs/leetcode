func visiblePoints(points [][]int, angle int, location []int) int {
	same := 0
	v := []float64{}
	for _, p := range points {
		if p[0] == location[0] && p[1] == location[1] {
			same++
		} else {
			v = append(v, math.Atan2(float64(p[1]-location[1]), float64(p[0]-location[0])))
		}
	}
	sort.Float64s(v)
	for _, deg := range v {
		v = append(v, deg+2*math.Pi)
	}

	mx := 0
	t := float64(angle) * math.Pi / 180
	for i, j := 0, 0; j < len(v); j++ {
		for i < j && v[j]-v[i] > t {
			i++
		}
		mx = max(mx, j-i+1)
	}
	return same + mx
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
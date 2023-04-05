func temperatureTrend(temperatureA []int, temperatureB []int) int {
	ans, f := 0, 0
	for i := range temperatureA[1:] {
		x := temperatureA[i+1] - temperatureA[i]
		y := temperatureB[i+1] - temperatureB[i]
		if (x == 0 && y == 0) || x*y > 0 {
			f++
			ans = max(ans, f)
		} else {
			f = 0
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
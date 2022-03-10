func minSpeedOnTime(dist []int, hour float64) int {
	n := len(dist)
	left, right := 1, int(1e7)
	check := func(speed float64) bool {
		var cost float64
		for _, v := range dist[:n-1] {
			cost += math.Ceil(float64(v) / speed)
		}
		cost += float64(dist[n-1]) / speed
		return cost <= hour

	}
	for left < right {
		mid := (left + right) >> 1
		if check(float64(mid)) {
			right = mid
		} else {
			left = mid + 1
		}
	}
	if check(float64(left)) {
		return left
	}
	return -1
}
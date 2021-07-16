func minSpeedOnTime(dist []int, hour float64) int {
	n := len(dist)
	left, right := 1, int(1e7)
	for left < right {
		mid := (left + right) >> 1
		if arriveOnTime(dist, n, float64(mid), hour) {
			right = mid
		} else {
			left = mid + 1
		}
	}
	if arriveOnTime(dist, n, float64(left), hour) {
		return left
	}
	return -1
}

func arriveOnTime(dist []int, n int, speed, hour float64) bool {
	var cost float64
	for _, v := range dist[:n-1] {
		cost += math.Ceil(float64(v) / speed)
	}
	cost += float64(dist[n-1]) / speed
	return cost <= hour
}
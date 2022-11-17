func minmaxGasDist(stations []int, k int) float64 {
	check := func(x float64) bool {
		s := 0
		for i, v := range stations[:len(stations)-1] {
			s += int(float64(stations[i+1]-v) / x)
		}
		return s <= k
	}
	var left, right float64 = 0, 1e8
	for right-left > 1e-6 {
		mid := (left + right) / 2.0
		if check(mid) {
			right = mid
		} else {
			left = mid
		}
	}
	return left
}
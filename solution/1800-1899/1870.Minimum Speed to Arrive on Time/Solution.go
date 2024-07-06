func minSpeedOnTime(dist []int, hour float64) int {
	if float64(len(dist)) > math.Ceil(hour) {
		return -1
	}
	const m int = 1e7
	n := len(dist)
	ans := sort.Search(m+1, func(v int) bool {
		v++
		s := 0.0
		for i, d := range dist {
			t := float64(d) / float64(v)
			if i == n-1 {
				s += t
			} else {
				s += math.Ceil(t)
			}
		}
		return s <= hour
	}) + 1
	if ans > m {
		return -1
	}
	return ans
}

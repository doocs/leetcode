func asteroidsDestroyed(mass int, asteroids []int) bool {
	m := mass
	sort.Ints(asteroids)
	for _, v := range asteroids {
		if m < v {
			return false
		}
		m += v
	}
	return true
}
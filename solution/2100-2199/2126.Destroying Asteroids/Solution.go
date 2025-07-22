func asteroidsDestroyed(mass int, asteroids []int) bool {
	sort.Ints(asteroids)
	for _, x := range asteroids {
		if mass < x {
			return false
		}
		mass += x
	}
	return true
}

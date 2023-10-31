func distanceBetweenBusStops(distance []int, start int, destination int) int {
	s := 0
	for _, x := range distance {
		s += x
	}
	a, n := 0, len(distance)
	for start != destination {
		a += distance[start]
		start = (start + 1) % n
	}
	return min(a, s-a)
}
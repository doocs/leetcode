func distanceBetweenBusStops(distance []int, start int, destination int) int {
	if start > destination {
		return distanceBetweenBusStops(distance, destination, start)
	}
	a, b := 0, 0
	for i, v := range distance {
		if i >= start && i < destination {
			a += v
		} else {
			b += v
		}
	}
	if a < b {
		return a
	}
	return b
}
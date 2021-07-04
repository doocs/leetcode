func eliminateMaximum(dist []int, speed []int) int {
	n := len(dist)
	times := make([]int, n)
	for i := 0; i < n; i++ {
		times[i] = (dist[i] - 1) / speed[i]
	}
	sort.Ints(times)
	for i := 0; i < n; i++ {
		if times[i] < i {
			return i
		}
	}
	return n
}
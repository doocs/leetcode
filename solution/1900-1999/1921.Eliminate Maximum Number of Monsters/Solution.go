func eliminateMaximum(dist []int, speed []int) int {
	n := len(dist)
	times := make([]int, n)
	for i, d := range dist {
		times[i] = (d - 1) / speed[i]
	}
	sort.Ints(times)
	for i, t := range times {
		if t < i {
			return i
		}
	}
	return n
}
func checkDistances(s string, distance []int) bool {
	d := make([]int, 26)
	for i, c := range s {
		j := c - 'a'
		if d[j] > 0 && i-d[j] != distance[j] {
			return false
		}
		d[j] = i + 1
	}
	return true
}
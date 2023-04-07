func checkDistances(s string, distance []int) bool {
	d := [26]int{}
	for i, c := range s {
		c -= 'a'
		if d[c] > 0 && i-d[c] != distance[c] {
			return false
		}
		d[c] = i + 1
	}
	return true
}
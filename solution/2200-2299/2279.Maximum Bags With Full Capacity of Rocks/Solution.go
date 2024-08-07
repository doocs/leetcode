func maximumBags(capacity []int, rocks []int, additionalRocks int) int {
	for i, x := range rocks {
		capacity[i] -= x
	}
	sort.Ints(capacity)
	for i, x := range capacity {
		additionalRocks -= x
		if additionalRocks < 0 {
			return i
		}
	}
	return len(capacity)
}

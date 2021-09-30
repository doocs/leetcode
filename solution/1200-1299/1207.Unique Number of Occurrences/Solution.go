func uniqueOccurrences(arr []int) bool {
	counter := make(map[int]int)
	for _, e := range arr {
		counter[e]++
	}
	s := make(map[int]bool)
	for _, num := range counter {
		if s[num] {
			return false
		}
		s[num] = true
	}
	return true
}
func countElements(arr []int) int {
	s := make(map[int]bool)
	for _, num := range arr {
		s[num] = true
	}
	res := 0
	for _, num := range arr {
		if s[num+1] {
			res++
		}
	}
	return res
}
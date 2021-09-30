func heightChecker(heights []int) int {
	expected := make([]int, len(heights))
	copy(expected, heights)
	sort.Ints(expected)
	res := 0
	for i, h := range heights {
		if h != expected[i] {
			res++
		}
	}
	return res
}
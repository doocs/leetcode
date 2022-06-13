func heightChecker(heights []int) int {
	expected := make([]int, len(heights))
	copy(expected, heights)
	sort.Ints(expected)
	ans := 0
	for i, v := range heights {
		if v != expected[i] {
			ans++
		}
	}
	return ans
}
func findNumbers(nums []int) int {
	s := 0
	for _, num := range nums {
		if (len(strconv.Itoa(num)) & 1) == 0 {
			s++
		}
	}
	return s
}
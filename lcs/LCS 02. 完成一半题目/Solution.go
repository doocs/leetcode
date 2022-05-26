func halfQuestions(questions []int) int {
	counter := make([]int, 1010)
	for _, q := range questions {
		counter[q]++
	}
	n := len(questions) >> 1
	sort.Ints(counter)
	ans := 0
	for i := len(counter) - 1; n > 0; i-- {
		ans++
		n -= counter[i]
	}
	return ans
}
func halfQuestions(questions []int) int {
	counter := make([]int, 1010)
	for _, e := range questions {
		counter[e]++
	}
	n := len(questions) >> 1
	sort.Ints(counter)
	res := 0
	for i := len(counter) - 1; i >= 0; i-- {
		res++
		if counter[i] >= n {
			return res
		}
		n -= counter[i]
	}
	return res
}
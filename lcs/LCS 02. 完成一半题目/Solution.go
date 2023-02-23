func halfQuestions(questions []int) (ans int) {
	cnt := make([]int, 1010)
	for _, x := range questions {
		cnt[x]++
	}
	n := len(questions) >> 1
	sort.Ints(cnt)
	for i := len(cnt) - 1; n > 0; i-- {
		ans++
		n -= cnt[i]
	}
	return
}
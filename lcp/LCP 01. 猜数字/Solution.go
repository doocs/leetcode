func game(guess []int, answer []int) int {
	times := 0
	for i := 0; i < 3; i++ {
		if guess[i] == answer[i] {
			times++
		}
	}
	return times
}
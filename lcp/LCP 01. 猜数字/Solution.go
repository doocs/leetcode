func game(guess []int, answer []int) (ans int) {
	for i, a := range guess {
		if a == answer[i] {
			ans++
		}
	}
	return
}
func calculateTime(keyboard string, word string) int {
	index := map[byte]int{}
	for i := 0; i < len(keyboard); i++ {
		index[keyboard[i]] = i
	}
	res := 0
	t := 0
	for i := 0; i < len(word); i++ {
		res += abs(index[word[i]] - t)
		t = index[word[i]]
	}
	return res
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
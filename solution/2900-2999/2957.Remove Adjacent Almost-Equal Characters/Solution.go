func removeAlmostEqualCharacters(word string) (ans int) {
	for i := 1; i < len(word); i++ {
		if abs(int(word[i])-int(word[i-1])) < 2 {
			ans++
			i++
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
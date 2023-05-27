func calculateTime(keyboard string, word string) (ans int) {
	pos := [26]int{}
	for i, c := range keyboard {
		pos[c-'a'] = i
	}
	i := 0
	for _, c := range word {
		j := pos[c-'a']
		ans += abs(i - j)
		i = j
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
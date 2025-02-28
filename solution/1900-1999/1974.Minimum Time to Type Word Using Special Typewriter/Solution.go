func minTimeToType(word string) int {
	ans := len(word)
	a := rune('a')
	for _, c := range word {
		d := int(max(a-c, c-a))
		ans += min(d, 26-d)
		a = c
	}
	return ans
}

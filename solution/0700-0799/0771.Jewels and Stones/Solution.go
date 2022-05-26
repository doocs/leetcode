func numJewelsInStones(jewels string, stones string) int {
	s := make(map[rune]bool)
	for _, c := range jewels {
		s[c] = true
	}
	res := 0
	for _, c := range stones {
		if s[c] {
			res++
		}
	}
	return res
}
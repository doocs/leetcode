func numJewelsInStones(jewels string, stones string) (ans int) {
	s := [128]int{}
	for _, c := range jewels {
		s[c] = 1
	}
	for _, c := range stones {
		ans += s[c]
	}
	return
}
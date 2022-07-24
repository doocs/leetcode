func bestHand(ranks []int, suits []byte) string {
	s := map[byte]bool{}
	for _, v := range suits {
		s[v] = true
	}
	if len(s) == 1 {
		return "Flush"
	}
	cnt := make([]int, 20)
	for _, v := range ranks {
		cnt[v]++
		if cnt[v] >= 3 {
			return "Three of a Kind"
		}
	}
	for _, v := range cnt {
		if v == 2 {
			return "Pair"
		}
	}
	return "High Card"
}
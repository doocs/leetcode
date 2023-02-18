func bestHand(ranks []int, suits []byte) string {
	s := map[byte]struct{}{}
	for _, c := range suits {
		s[c] = struct{}{}
	}
	if len(s) == 1 {
		return "Flush"
	}
	cnt := [14]int{}
	pair := false
	for _, x := range ranks {
		cnt[x]++
		if cnt[x] == 3 {
			return "Three of a Kind"
		}
		pair = pair || cnt[x] == 2
	}
	if pair {
		return "Pair"
	}
	return "High Card"
}
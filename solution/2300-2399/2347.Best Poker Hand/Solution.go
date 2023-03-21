func bestHand(ranks []int, suits []byte) string {
	flush := true
	for i := 1; i < 5 && flush; i++ {
		flush = suits[i] == suits[i-1]
	}
	if flush {
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
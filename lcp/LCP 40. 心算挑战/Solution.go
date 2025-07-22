func maxmiumScore(cards []int, cnt int) int {
	sort.Ints(cards)
	ans := 0
	n := len(cards)
	for i := 0; i < cnt; i++ {
		ans += cards[n-1-i]
	}
	if ans%2 == 0 {
		return ans
	}
	const inf = 1 << 29
	mx1, mx2 := -inf, -inf
	for _, x := range cards[:n-cnt] {
		if x%2 == 1 {
			mx1 = x
		} else {
			mx2 = x
		}
	}
	mi1, mi2 := inf, inf
	for i := n - 1; i >= n-cnt; i-- {
		if cards[i]%2 == 1 {
			mi2 = cards[i]
		} else {
			mi1 = cards[i]
		}
	}
	ans = max(-1, max(ans-mi1+mx1, ans-mi2+mx2))
	if ans < 0 {
		return 0
	}
	return ans
}

func maxmiumScore(cards []int, cnt int) int {
	ans := 0
	sort.Slice(cards, func(i, j int) bool { return cards[i] > cards[j] })
	for _, v := range cards[:cnt] {
		ans += v
	}
	if ans%2 == 0 {
		return ans
	}
	inf := 0x3f3f3f3f
	a, b, c, d := inf, inf, -inf, -inf
	for _, v := range cards[:cnt] {
		if v%2 == 1 {
			a = min(a, v)
		} else {
			b = min(b, v)
		}
	}
	for _, v := range cards[cnt:] {
		if v%2 == 0 {
			c = max(c, v)
		} else {
			d = max(d, v)
		}
	}
	return max(0, max(ans-a+c, ans-b+d))
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
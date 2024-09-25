func maxCoins(piles []int) (ans int) {
	sort.Ints(piles)
	for i := len(piles) / 3; i < len(piles); i += 2 {
		ans += piles[i]
	}
	return
}

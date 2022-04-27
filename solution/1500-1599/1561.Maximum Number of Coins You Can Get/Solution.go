func maxCoins(piles []int) int {
	sort.Ints(piles)
	ans, n := 0, len(piles)
	for i := n - 2; i >= n/3; i -= 2 {
		ans += piles[i]
	}
	return ans
}
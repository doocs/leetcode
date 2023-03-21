func minimumCardPickup(cards []int) int {
	last := map[int]int{}
	n := len(cards)
	ans := n + 1
	for i, x := range cards {
		if j, ok := last[x]; ok && ans > i-j+1 {
			ans = i - j + 1
		}
		last[x] = i
	}
	if ans > n {
		return -1
	}
	return ans
}
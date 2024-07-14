func countCompleteDayPairs(hours []int) (ans int) {
	cnt := [24]int{}
	for _, x := range hours {
		ans += cnt[(24-x%24)%24]
		cnt[x%24]++
	}
	return
}
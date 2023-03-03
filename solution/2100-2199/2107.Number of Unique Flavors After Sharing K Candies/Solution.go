func shareCandies(candies []int, k int) (ans int) {
	cnt := map[int]int{}
	for _, c := range candies[k:] {
		cnt[c]++
	}
	ans = len(cnt)
	for i := k; i < len(candies); i++ {
		cnt[candies[i]]--
		if cnt[candies[i]] == 0 {
			delete(cnt, candies[i])
		}
		cnt[candies[i-k]]++
		ans = max(ans, len(cnt))
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
func numPairsDivisibleBy60(time []int) (ans int) {
	cnt := [60]int{}
	for _, t := range time {
		cnt[t%60]++
	}
	for x := 1; x < 30; x++ {
		ans += cnt[x] * cnt[60-x]
	}
	ans += cnt[0] * (cnt[0] - 1) / 2
	ans += cnt[30] * (cnt[30] - 1) / 2
	return
}
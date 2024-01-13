func numPairsDivisibleBy60(time []int) (ans int) {
	cnt := [60]int{}
	for _, x := range time {
		x %= 60
		y := (60 - x) % 60
		ans += cnt[y]
		cnt[x]++
	}
	return
}
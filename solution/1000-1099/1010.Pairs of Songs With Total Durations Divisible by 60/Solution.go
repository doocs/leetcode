func numPairsDivisibleBy60(time []int) (ans int) {
	cnt := [501]int{}
	for _, t := range time {
		s := 60
		for i := 0; i < 17; i++ {
			if s-t >= 0 && s-t < 501 {
				ans += cnt[s-t]
			}
			s += 60
		}
		cnt[t]++
	}
	return
}
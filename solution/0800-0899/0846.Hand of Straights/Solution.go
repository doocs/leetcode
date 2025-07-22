func isNStraightHand(hand []int, groupSize int) bool {
	if len(hand)%groupSize != 0 {
		return false
	}
	sort.Ints(hand)
	cnt := map[int]int{}
	for _, x := range hand {
		cnt[x]++
	}
	for _, x := range hand {
		if cnt[x] > 0 {
			for y := x; y < x+groupSize; y++ {
				if cnt[y] == 0 {
					return false
				}
				cnt[y]--
			}
		}
	}
	return true
}

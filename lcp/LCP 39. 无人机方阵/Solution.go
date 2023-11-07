func minimumSwitchingTimes(source [][]int, target [][]int) (ans int) {
	cnt := map[int]int{}
	for _, row := range source {
		for _, x := range row {
			cnt[x]++
		}
	}
	for _, row := range target {
		for _, x := range row {
			cnt[x]--
		}
	}
	for _, v := range cnt {
		if v > 0 {
			ans += v
		}
	}
	return
}
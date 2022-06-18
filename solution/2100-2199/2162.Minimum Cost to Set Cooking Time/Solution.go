func minCostSetTime(startAt int, moveCost int, pushCost int, targetSeconds int) int {
	m, s := targetSeconds/60, targetSeconds%60
	f := func(m, s int) int {
		if m < 0 || m > 99 || s < 0 || s > 99 {
			return 0x3f3f3f3f
		}
		arr := []int{m / 10, m % 10, s / 10, s % 10}
		i := 0
		for ; i < 4 && arr[i] == 0; i++ {
		}
		t := 0
		prev := startAt
		for ; i < 4; i++ {
			if arr[i] != prev {
				t += moveCost
			}
			t += pushCost
			prev = arr[i]
		}
		return t
	}
	return min(f(m, s), f(m-1, s+60))
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
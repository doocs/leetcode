func removeInterval(intervals [][]int, toBeRemoved []int) (ans [][]int) {
	x, y := toBeRemoved[0], toBeRemoved[1]
	for _, e := range intervals {
		a, b := e[0], e[1]
		if a >= y || b <= x {
			ans = append(ans, e)
		} else {
			if a < x {
				ans = append(ans, []int{a, x})
			}
			if b > y {
				ans = append(ans, []int{y, b})
			}
		}
	}
	return
}
func numFriendRequests(ages []int) (ans int) {
	cnt := [121]int{}
	for _, x := range ages {
		cnt[x]++
	}
	for ax, x := range cnt {
		for ay, y := range cnt {
			if ay <= ax/2+7 || ay > ax || (ay > 100 && ax < 100) {
				continue
			}
			if ax == ay {
				ans += x * (x - 1)
			} else {
				ans += x * y
			}
		}
	}

	return
}

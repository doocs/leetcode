func catchMaximumAmountofPeople(team []int, dist int) (ans int) {
	n := len(team)
	for i, j := 0, 0; i < n; i++ {
		if team[i] == 1 {
			for j < n && (team[j] == 1 || i-j > dist) {
				j++
			}
			if j < n && abs(i-j) <= dist {
				ans++
				j++
			}
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
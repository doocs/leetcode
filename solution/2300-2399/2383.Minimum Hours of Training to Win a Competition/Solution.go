func minNumberOfHours(x int, y int, energy []int, experience []int) (ans int) {
	for i, dx := range energy {
		dy := experience[i]
		if x <= dx {
			ans += dx + 1 - x
			x = dx + 1
		}
		if y <= dy {
			ans += dy + 1 - y
			y = dy + 1
		}
		x -= dx
		y += dy
	}
	return
}

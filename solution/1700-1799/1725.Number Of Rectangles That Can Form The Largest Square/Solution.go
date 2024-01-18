func countGoodRectangles(rectangles [][]int) (ans int) {
	mx := 0
	for _, e := range rectangles {
		x := min(e[0], e[1])
		if mx < x {
			mx = x
			ans = 1
		} else if mx == x {
			ans++
		}
	}
	return
}
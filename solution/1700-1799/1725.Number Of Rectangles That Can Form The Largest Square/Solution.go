func countGoodRectangles(rectangles [][]int) int {
	ans, mx := 0, 0
	for _, r := range rectangles {
		t := r[0]
		if t > r[1] {
			t = r[1]
		}
		if mx < t {
			mx, ans = t, 1
		} else if mx == t {
			ans++
		}
	}
	return ans
}
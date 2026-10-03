func bestLine(points [][]int) []int {
	n := len(points)
	ans := []int{0, 0}
	type pair struct{ x, y int }
	mx := 0
	for i := 0; i < n; i++ {
		x1, y1 := points[i][0], points[i][1]
		cnt := map[pair][]int{}
		var dup []int
		for j := i + 1; j < n; j++ {
			dx, dy := points[j][0]-x1, points[j][1]-y1
			if dx == 0 && dy == 0 {
				dup = append(dup, j)
				continue
			}
			g := gcd(dx, dy)
			dx /= g
			dy /= g
			if dx < 0 || (dx == 0 && dy < 0) {
				dx, dy = -dx, -dy
			}
			k := pair{dx, dy}
			cnt[k] = append(cnt[k], j)
		}
		consider := func(b, c int) {
			if c > mx || (c == mx && (i < ans[0] || (i == ans[0] && b < ans[1]))) {
				mx = c
				ans[0], ans[1] = i, b
			}
		}
		if len(cnt) == 0 {
			if len(dup) > 0 {
				consider(dup[0], len(dup)+1)
			}
			continue
		}
		for _, js := range cnt {
			b := js[0]
			if len(dup) > 0 && dup[0] < b {
				b = dup[0]
			}
			consider(b, len(js)+len(dup)+1)
		}
	}
	return ans
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}

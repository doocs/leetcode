func maxPoints(points [][]int) int {
	type pair struct {
		first  int
		second int
	}
	n := len(points)
	if n <= 2 {
		return n
	}
	ans := 0
	for i := 0; i < n-1; i++ {
		freq := make(map[pair]int)
		for j := i + 1; j < n; j++ {
			x1, y1, x2, y2 := points[i][0], points[i][1], points[j][0], points[j][1]
			dx, dy := x2-x1, y2-y1
			g := gcd(dx, dy)
			p := pair{dx / g, dy / g}
			freq[p]++
			ans = max(ans, freq[p]+1)
		}
	}
	return ans
}

func gcd(a, b int) int {
	for b != 0 {
		a, b = b, a%b
	}
	return a
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func shortestDistanceColor(colors []int, queries [][]int) (ans []int) {
	n := len(colors)
	const inf = 1 << 30
	right := make([][3]int, n+1)
	left := make([][3]int, n+1)
	right[n] = [3]int{inf, inf, inf}
	left[0] = [3]int{-inf, -inf, -inf}
	for i := n - 1; i >= 0; i-- {
		for j := 0; j < 3; j++ {
			right[i][j] = right[i+1][j]
		}
		right[i][colors[i]-1] = i
	}
	for i := 1; i <= n; i++ {
		for j := 0; j < 3; j++ {
			left[i][j] = left[i-1][j]
		}
		left[i][colors[i-1]-1] = i - 1
	}
	for _, q := range queries {
		i, c := q[0], q[1]-1
		d := min(i-left[i+1][c], right[i][c]-i)
		if d > n {
			d = -1
		}
		ans = append(ans, d)
	}
	return
}
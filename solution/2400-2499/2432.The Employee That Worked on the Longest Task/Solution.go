func hardestWorker(n int, logs [][]int) int {
	ans, mx, last := 0, 0, 0
	for _, e := range logs {
		uid, t := e[0], e[1]
		x := t - last
		if mx < x {
			mx, ans = x, uid
		} else if mx == x && ans > uid {
			ans = uid
		}
		last = t
	}
	return ans
}
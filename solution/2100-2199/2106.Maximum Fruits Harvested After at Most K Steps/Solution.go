func maxTotalFruits(fruits [][]int, startPos int, k int) int {
	var q [][]int
	i, n := 0, len(fruits)
	ans := 0
	for i < n && fruits[i][0] <= startPos {
		if startPos-fruits[i][0] <= k {
			ans += fruits[i][1]
			q = append(q, fruits[i])
		}
		i++
	}
	t := ans
	for i < n && fruits[i][0]-startPos <= k {
		for len(q) > 0 && q[0][0] < startPos && fruits[i][0]-q[0][0]+min(startPos-q[0][0], fruits[i][0]-startPos) > k {
			t -= q[0][1]
			q = q[1:]
		}
		t += fruits[i][1]
		ans = max(ans, t)
		i++
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
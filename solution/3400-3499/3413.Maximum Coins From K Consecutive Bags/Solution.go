func maximumCoins(coins [][]int, k int) int64 {
	sort.Slice(coins, func(i, j int) bool {
		return coins[i][0] < coins[j][0]
	})

	n := len(coins)
	var total int64 = 0
	var ans int64 = 0
	i := 0
	l0, r0, c0 := coins[0][0], coins[0][1], coins[0][2]

	for _, seg := range coins {
		l, r, c := seg[0], seg[1], seg[2]

		for i < n && r-coins[i][0]+1 >= k {
			l0, r0, c0 = coins[i][0], coins[i][1], coins[i][2]
			ans = max64(ans, total+int64(max(k+l0-l, 0))*int64(c))
			total -= int64(r0-l0+1) * int64(c0)
			i++
		}

		total += int64(r-l+1) * int64(c)

		if i < n && coins[i][0] != l0 {
			startL := r - k + 1
			ans = max64(ans, total+int64(max(r0-startL+1, 0))*int64(c0))
		}
	}
	if total > ans {
		ans = total
	}
	return ans
}

func max64(a, b int64) int64 {
	if a > b {
		return a
	}
	return b
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

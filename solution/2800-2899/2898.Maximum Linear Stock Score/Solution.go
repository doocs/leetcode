func maxScore(prices []int) (ans int64) {
	cnt := map[int]int{}
	for i, x := range prices {
		cnt[x-i] += x
	}
	for _, v := range cnt {
		ans = max(ans, int64(v))
	}
	return
}

func max(a, b int64) int64 {
	if a > b {
		return a
	}
	return b
}
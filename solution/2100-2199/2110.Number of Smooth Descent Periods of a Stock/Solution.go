func getDescentPeriods(prices []int) (ans int64) {
	n := len(prices)
	for i, j := 0, 0; i < n; i = j {
		j = i + 1
		for j < n && prices[j-1]-prices[j] == 1 {
			j++
		}
		cnt := j - i
		ans += int64((1 + cnt) * cnt / 2)
	}
	return
}
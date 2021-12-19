func getDescentPeriods(prices []int) int64 {
	var ans int64
	for i, n := 0, len(prices); i < n; {
		j := i
		for ; j+1 < n && prices[j]-prices[j+1] == 1; j++ {
		}
		t := j - i + 1
		ans += int64(t * (t + 1) / 2)
		i = j + 1
	}
	return ans
}
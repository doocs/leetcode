func maxValue(n int, index int, maxSum int) int {
	sum := func(x, cnt int) int {
		if x >= cnt {
			return (x + x - cnt + 1) * cnt / 2
		}
		return (x+1)*x/2 + cnt - x
	}
	return sort.Search(maxSum, func(x int) bool {
		x++
		return sum(x-1, index)+sum(x, n-index) > maxSum
	})
}
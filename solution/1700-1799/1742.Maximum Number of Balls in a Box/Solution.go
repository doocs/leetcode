func countBalls(lowLimit int, highLimit int) int {
	counter := make([]int, 60)
	ans := 0
	for i := lowLimit; i <= highLimit; i++ {
		s, j := 0, i
		for j > 0 {
			s += (j % 10)
			j /= 10
		}
		counter[s]++
		if counter[s] > ans {
			ans = counter[s]
		}
	}
	return ans
}
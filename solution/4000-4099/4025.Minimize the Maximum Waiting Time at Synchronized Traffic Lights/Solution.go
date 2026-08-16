func minPenalty(period int, lights []int, arrivalTime []int) int {
	mx := slices.Max(lights)
	ans := 0

	for _, x := range arrivalTime {
		r := x % period

		if r >= mx {
			ans = max(ans, period-r)
		}
	}

	return ans
}

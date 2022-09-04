func maximumRobots(chargeTimes []int, runningCosts []int, budget int64) int {
	s := int64(0)
	ans, j := 0, 0
	q := []int{}
	for i, a := range chargeTimes {
		for len(q) > 0 && chargeTimes[q[len(q)-1]] <= a {
			q = q[:len(q)-1]
		}
		q = append(q, i)
		s += int64(runningCosts[i])
		for len(q) > 0 && int64(chargeTimes[q[0]])+int64(i-j+1)*s > budget {
			if q[0] == j {
				q = q[1:]
			}
			s -= int64(runningCosts[j])
			j++
		}
		ans = max(ans, i-j+1)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
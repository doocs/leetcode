func minCost(colors string, neededTime []int) (ans int) {
	n := len(colors)
	for i, j := 0, 0; i < n; i = j {
		j = i
		s, mx := 0, 0
		for j < n && colors[j] == colors[i] {
			s += neededTime[j]
			if mx < neededTime[j] {
				mx = neededTime[j]
			}
			j++
		}
		if j-i > 1 {
			ans += s - mx
		}
	}
	return
}
func maximumGap(skill string, station string) int {
	n, m := len(skill), len(station)

	suf := make([]int, n)
	j := m - 1

	for i := n - 1; i > 0; i-- {
		for station[j] != skill[i] {
			j--
		}

		suf[i] = j
		j--
	}

	ans := 0
	pre := 0

	for i := 0; i < n-1; i++ {
		for station[pre] != skill[i] {
			pre++
		}

		ans = max(ans, suf[i+1]-pre)

		pre++
	}

	return ans
}

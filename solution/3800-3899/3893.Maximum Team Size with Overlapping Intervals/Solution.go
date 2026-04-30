func maximumTeamSize(startTime []int, endTime []int) int {
	n := len(startTime)
	intervals := make([][2]int, n)
	for i := 0; i < n; i++ {
		intervals[i] = [2]int{startTime[i], endTime[i]}
	}

	sort.Ints(startTime)
	sort.Ints(endTime)

	ans := 0
	for _, it := range intervals {
		l, r := it[0], it[1]

		i := sort.Search(len(endTime), func(k int) bool { return endTime[k] > l-1 })
		j := sort.Search(len(startTime), func(k int) bool { return startTime[k] > r })

		ans = max(ans, j-i)
	}

	return ans
}

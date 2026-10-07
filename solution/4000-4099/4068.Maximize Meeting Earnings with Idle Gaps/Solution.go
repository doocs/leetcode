func maxEarnings(meetings [][]int) int64 {
	n := len(meetings)
	sort.Slice(meetings, func(i, j int) bool {
		return meetings[i][1] < meetings[j][1]
	})

	preMax := make([]int64, n+1)
	for i := range preMax {
		preMax[i] = -1 << 60
	}

	var ans int64

	for i, meeting := range meetings {
		start, end, revenue := meeting[0], meeting[1], meeting[2]

		val := int64(revenue)
		if start >= meetings[0][1] {
			j := sort.Search(i, func(j int) bool {
				return meetings[j][1] > start
			})
			val += preMax[j] + int64(start)
		}

		ans = max(ans, val)
		preMax[i+1] = max(preMax[i], val-int64(end))
	}

	return ans
}

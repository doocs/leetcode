func merge(intervals [][]int) [][]int {
	var res [][]int
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][0] < intervals[j][0]
	})
	st, ed := -1, -1
	for _, e := range intervals {
		if ed < e[0] {
			if st != -1 {
				res = append(res, []int{st, ed})
			}
			st, ed = e[0], e[1]
		} else {
			ed = max(ed, e[1])
		}
	}
	if st != -1 {
		res = append(res, []int{st, ed})
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
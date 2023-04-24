func insert(intervals [][]int, newInterval []int) (ans [][]int) {
	st, ed := newInterval[0], newInterval[1]
	insert := false
	for _, interval := range intervals {
		s, e := interval[0], interval[1]
		if ed < s {
			if !insert {
				ans = append(ans, []int{st, ed})
				insert = true
			}
			ans = append(ans, interval)
		} else if e < st {
			ans = append(ans, interval)
		} else {
			st = min(st, s)
			ed = max(ed, e)
		}
	}
	if !insert {
		ans = append(ans, []int{st, ed})
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
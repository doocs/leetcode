func minSelection(availableIntervals [][]int, targetIntervals [][]int) int {
	sort.Slice(availableIntervals, func(a, b int) bool { return availableIntervals[a][0] < availableIntervals[b][0] })
	sort.Slice(targetIntervals, func(a, b int) bool { return targetIntervals[a][0] < targetIntervals[b][0] })
	ans, cur, i := 0, 0, 0
	for _, t := range targetIntervals {
		l, r := t[0], t[1]
		for cur < r {
			uncover := max(l, cur)
			nxt := cur
			for i < len(availableIntervals) && availableIntervals[i][0] <= uncover {
				nxt = max(nxt, availableIntervals[i][1])
				i++
			}
			if nxt < uncover || nxt == cur {
				return -1
			}
			cur = nxt
			ans++
		}
	}
	return ans
}

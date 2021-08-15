func intervalIntersection(firstList [][]int, secondList [][]int) [][]int {
	i, j := 0, 0
	var res [][]int
	for i < len(firstList) && j < len(secondList) {
		l, r := max(firstList[i][0], secondList[j][0]), min(firstList[i][1], secondList[j][1])
		if l <= r {
			res = append(res, []int{l, r})
		}
		if firstList[i][1] < secondList[j][1] {
			i++
		} else {
			j++
		}
	}
	return res
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
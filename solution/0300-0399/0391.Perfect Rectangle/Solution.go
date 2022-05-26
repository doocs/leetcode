type pair struct {
	first  int
	second int
}

func isRectangleCover(rectangles [][]int) bool {
	area := 0
	minX, minY := rectangles[0][0], rectangles[0][1]
	maxX, maxY := rectangles[0][2], rectangles[0][3]

	cnt := make(map[pair]int)
	for _, r := range rectangles {
		area += (r[2] - r[0]) * (r[3] - r[1])

		minX = min(minX, r[0])
		minY = min(minY, r[1])
		maxX = max(maxX, r[2])
		maxY = max(maxY, r[3])

		cnt[pair{r[0], r[1]}]++
		cnt[pair{r[0], r[3]}]++
		cnt[pair{r[2], r[3]}]++
		cnt[pair{r[2], r[1]}]++
	}

	if area != (maxX-minX)*(maxY-minY) ||
		cnt[pair{minX, minY}] != 1 ||
		cnt[pair{minX, maxY}] != 1 ||
		cnt[pair{maxX, maxY}] != 1 ||
		cnt[pair{maxX, minY}] != 1 {
		return false
	}

	delete(cnt, pair{minX, minY})
	delete(cnt, pair{minX, maxY})
	delete(cnt, pair{maxX, maxY})
	delete(cnt, pair{maxX, minY})

	for _, c := range cnt {
		if c != 2 && c != 4 {
			return false
		}
	}
	return true
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

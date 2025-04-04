type Pair struct {
	val int
	typ int // 1 = start, 0 = end
}

func countLineIntersections(coords []Pair) bool {
	lines := 0
	overlap := 0
	for _, p := range coords {
		if p.typ == 0 {
			overlap--
		} else {
			overlap++
		}
		if overlap == 0 {
			lines++
		}
	}
	return lines >= 3
}

func checkValidCuts(n int, rectangles [][]int) bool {
	var xCoords []Pair
	var yCoords []Pair

	for _, rect := range rectangles {
		x1, y1, x2, y2 := rect[0], rect[1], rect[2], rect[3]

		yCoords = append(yCoords, Pair{y1, 1}) // start
		yCoords = append(yCoords, Pair{y2, 0}) // end

		xCoords = append(xCoords, Pair{x1, 1})
		xCoords = append(xCoords, Pair{x2, 0})
	}

	sort.Slice(yCoords, func(i, j int) bool {
		if yCoords[i].val == yCoords[j].val {
			return yCoords[i].typ < yCoords[j].typ // end before start
		}
		return yCoords[i].val < yCoords[j].val
	})

	sort.Slice(xCoords, func(i, j int) bool {
		if xCoords[i].val == xCoords[j].val {
			return xCoords[i].typ < xCoords[j].typ
		}
		return xCoords[i].val < xCoords[j].val
	})

	return countLineIntersections(yCoords) || countLineIntersections(xCoords)
}
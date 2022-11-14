func maximumUnits(boxTypes [][]int, truckSize int) (ans int) {
	sort.Slice(boxTypes, func(i, j int) bool { return boxTypes[i][1] > boxTypes[j][1] })
	for _, e := range boxTypes {
		a, b := e[0], e[1]
		ans += b * min(truckSize, a)
		truckSize -= a
		if truckSize <= 0 {
			break
		}
	}
	return
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
func maximumUnits(boxTypes [][]int, truckSize int) int {
	sort.Slice(boxTypes, func(i, j int) bool { return boxTypes[i][1] > boxTypes[j][1] })
	ans := 0
	for _, v := range boxTypes {
		a := min(v[0], truckSize)
		truckSize -= a
		ans += a * v[1]
		if truckSize == 0 {
			break
		}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
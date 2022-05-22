func minimumLines(stockPrices [][]int) int {
	ans := 0
	sort.Slice(stockPrices, func(i, j int) bool { return stockPrices[i][0] < stockPrices[j][0] })
	for i, dx, dy := 1, 0, 1; i < len(stockPrices); i++ {
		x, y := stockPrices[i-1][0], stockPrices[i-1][1]
		x1, y1 := stockPrices[i][0], stockPrices[i][1]
		dx1, dy1 := x1-x, y1-y
		if dy*dx1 != dx*dy1 {
			ans++
		}
		dx, dy = dx1, dy1
	}
	return ans
}
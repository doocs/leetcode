func minCost(startPos []int, homePos []int, rowCosts []int, colCosts []int) int {
	x0, y0 := startPos[0], startPos[1]
	x1, y1 := homePos[0], homePos[1]
	calc := func(nums []int, i int, j int) int {
		res := 0
		for k := i; k <= j; k++ {
			res += nums[k]
		}
		return res
	}
	dx := 0
	if x0 < x1 {
		dx = calc(rowCosts, x0+1, x1)
	} else {
		dx = calc(rowCosts, x1, x0-1)
	}
	dy := 0
	if y0 < y1 {
		dy = calc(colCosts, y0+1, y1)
	} else {
		dy = calc(colCosts, y1, y0-1)
	}
	return dx + dy
}

func minCost(startPos []int, homePos []int, rowCosts []int, colCosts []int) (ans int) {
	i, j := startPos[0], startPos[1]
	x, y := homePos[0], homePos[1]
	if i < x {
		ans += sum(rowCosts, i+1, x+1)
	} else {
		ans += sum(rowCosts, x, i)
	}
	if j < y {
		ans += sum(colCosts, j+1, y+1)
	} else {
		ans += sum(colCosts, y, j)
	}
	return
}

func sum(nums []int, i, j int) (s int) {
	for k := i; k < j; k++ {
		s += nums[k]
	}
	return
}
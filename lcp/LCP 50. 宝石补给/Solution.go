func giveGem(gem []int, operations [][]int) int {
	for _, op := range operations {
		x, y := op[0], op[1]
		v := gem[x] >> 1
		gem[y] += v
		gem[x] -= v
	}
	return slices.Max(gem) - slices.Min(gem)
}
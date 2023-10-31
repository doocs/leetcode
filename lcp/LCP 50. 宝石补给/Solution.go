func giveGem(gem []int, operations [][]int) int {
	for _, op := range operations {
		x, y := op[0], op[1]
		v := gem[x] >> 1
		gem[y] += v
		gem[x] -= v
	}
	mx, mi := 0, 1<<30
	for _, x := range gem {
		mx = max(mx, x)
		mi = min(mi, x)
	}
	return mx - mi
}
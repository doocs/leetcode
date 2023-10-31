func maxNumberOfAlloys(n int, k int, budget int, composition [][]int, stock []int, cost []int) int {
	isValid := func(target int) bool {
		for _, currMachine := range composition {
			remain := budget
			for i, x := range currMachine {
				need := max(0, x*target-stock[i])
				remain -= need * cost[i]
			}
			if remain >= 0 {
				return true
			}
		}
		return false
	}

	l, r := 0, budget+stock[0]
	for l < r {
		mid := (l + r + 1) >> 1
		if isValid(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}
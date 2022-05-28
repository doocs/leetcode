func maximumGood(statements [][]int) int {
	n := len(statements)
	check := func(mask int) int {
		cnt := 0
		for i, s := range statements {
			if ((mask >> i) & 1) == 1 {
				for j, v := range s {
					if v < 2 && ((mask>>j)&1) != v {
						return 0
					}
				}
				cnt++
			}
		}
		return cnt
	}
	ans := 0
	for mask := 1; mask < 1<<n; mask++ {
		ans = max(ans, check(mask))
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
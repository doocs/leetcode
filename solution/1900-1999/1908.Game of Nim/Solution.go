func nimGame(piles []int) bool {
	memo := map[int]bool{}
	p := make([]int, 8)
	p[0] = 1
	for i := 1; i < 8; i++ {
		p[i] = p[i-1] * 8
	}
	f := func(piles []int) int {
		st := 0
		for i, x := range piles {
			st += x * p[i]
		}
		return st
	}
	var dfs func(piles []int) bool
	dfs = func(piles []int) bool {
		st := f(piles)
		if v, ok := memo[st]; ok {
			return v
		}
		for i, x := range piles {
			for j := 1; j <= x; j++ {
				piles[i] -= j
				if !dfs(piles) {
					piles[i] += j
					memo[st] = true
					return true
				}
				piles[i] += j
			}
		}
		memo[st] = false
		return false
	}
	return dfs(piles)
}
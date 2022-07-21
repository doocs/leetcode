func canWin(currentState string) bool {
	n := len(currentState)
	memo := map[int]bool{}
	mask := 0
	for i, c := range currentState {
		if c == '+' {
			mask |= 1 << i
		}
	}
	var dfs func(int) bool
	dfs = func(mask int) bool {
		if v, ok := memo[mask]; ok {
			return v
		}
		for i := 0; i < n-1; i++ {
			if (mask&(1<<i)) == 0 || (mask&(1<<(i+1))) == 0 {
				continue
			}
			if dfs(mask ^ (1 << i) ^ (1 << (i + 1))) {
				continue
			}
			memo[mask] = true
			return true
		}
		memo[mask] = false
		return false
	}
	return dfs(mask)
}
func canIWin(maxChoosableInteger int, desiredTotal int) bool {
	s := (1 + maxChoosableInteger) * maxChoosableInteger / 2
	if s < desiredTotal {
		return false
	}
	memo := map[int]bool{}
	var dfs func(int, int) bool
	dfs = func(state, t int) bool {
		if v, ok := memo[state]; ok {
			return v
		}
		res := false
		for i := 1; i <= maxChoosableInteger; i++ {
			if (state>>i)&1 == 1 {
				continue
			}
			if t+i >= desiredTotal || !dfs(state|1<<i, t+i) {
				res = true
				break
			}
		}
		memo[state] = res
		return res
	}
	return dfs(0, 0)
}
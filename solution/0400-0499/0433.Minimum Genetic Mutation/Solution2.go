func minMutation(start string, end string, bank []string) int {
	s := make(map[string]bool)
	for _, b := range bank {
		s[b] = true
	}
	ans := math.MaxInt32
	s[start] = false
	seq := []rune{'A', 'C', 'G', 'T'}
	var dfs func(start string, t int)
	dfs = func(start string, t int) {
		if start == end {
			if ans > t {
				ans = t
			}
			return
		}
		for i, x := range start {
			for _, y := range seq {
				if x == y {
					continue
				}
				nxt := start[:i] + string(y) + start[i+1:]
				if s[nxt] {
					s[nxt] = false
					dfs(nxt, t+1)
				}
			}
		}
	}
	dfs(start, 0)
	if ans == math.MaxInt32 {
		return -1
	}
	return ans
}
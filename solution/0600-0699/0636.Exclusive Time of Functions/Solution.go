func exclusiveTime(n int, logs []string) []int {
	ans := make([]int, n)
	stk := []int{}
	curr := 1
	for _, log := range logs {
		t := strings.Split(log, ":")
		fid, _ := strconv.Atoi(t[0])
		ts, _ := strconv.Atoi(t[2])
		if t[1][0] == 's' {
			if len(stk) > 0 {
				ans[stk[len(stk)-1]] += ts - curr
			}
			stk = append(stk, fid)
			curr = ts
		} else {
			fid := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			ans[fid] += ts - curr + 1
			curr = ts + 1
		}
	}
	return ans
}
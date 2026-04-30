func longestBalanced(s string) int {
	cnt0 := strings.Count(s, "0")
	cnt1 := len(s) - cnt0
	pos := map[int][]int{0: {-1}}
	ans, pre := 0, 0
	for i, c := range s {
		if c == '1' {
			pre++
		} else {
			pre--
		}
		pos[pre] = append(pos[pre], i)

		ans = max(ans, i-pos[pre][0])
		if p, ok := pos[pre-2]; ok {
			if (i-p[0]-2)/2 < cnt0 {
				ans = max(ans, i-p[0])
			} else if len(p) > 1 {
				ans = max(ans, i-p[1])
			}
		}

		if p, ok := pos[pre+2]; ok {
			if (i-p[0]-2)/2 < cnt1 {
				ans = max(ans, i-p[0])
			} else if len(p) > 1 {
				ans = max(ans, i-p[1])
			}
		}
	}
	return ans
}
func maxEqualRowsAfterFlips(matrix [][]int) int {
	ans := 0
	cnt := map[string]int{}
	for _, row := range matrix {
		s := []byte{}
		for _, v := range row {
			if row[0] == 1 {
				v ^= 1
			}
			s = append(s, byte(v+'0'))
		}
		t := string(s)
		cnt[t]++
		ans = max(ans, cnt[t])
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
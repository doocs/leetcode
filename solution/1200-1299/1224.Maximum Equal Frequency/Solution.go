func maxEqualFreq(nums []int) int {
	cnt := map[int]int{}
	ccnt := map[int]int{}
	ans, mx := 0, 0
	for i, v := range nums {
		i++
		if cnt[v] > 0 {
			ccnt[cnt[v]]--
		}
		cnt[v]++
		mx = max(mx, cnt[v])
		ccnt[cnt[v]]++
		if mx == 1 {
			ans = i
		} else if ccnt[mx]*mx+ccnt[mx-1]*(mx-1) == i && ccnt[mx] == 1 {
			ans = i
		} else if ccnt[mx]*mx+1 == i && ccnt[1] == 1 {
			ans = i
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
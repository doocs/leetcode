func isFascinating(n int) bool {
	s := strconv.Itoa(n) + strconv.Itoa(n*2) + strconv.Itoa(n*3)
	cnt := [10]int{}
	for _, c := range s {
		cnt[c-'0']++
		if cnt[c-'0'] > 1 {
			return false
		}
	}
	return cnt[0] == 0 && len(s) == 9
}
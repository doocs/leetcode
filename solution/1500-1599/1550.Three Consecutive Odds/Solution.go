func threeConsecutiveOdds(arr []int) bool {
	cnt := 0
	for _, x := range arr {
		if x&1 == 1 {
			cnt++
			if cnt == 3 {
				return true
			}
		} else {
			cnt = 0
		}
	}
	return false
}

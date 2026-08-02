func minInitialStrength(monsters []int, boosts [][]int) int64 {
	n := len(monsters)
	d := make([]int64, n+1)
	for _, b := range boosts {
		d[b[0]] += int64(b[2])
		d[b[1]+1] -= int64(b[2])
	}

	check := func(v int64) bool {
		var bonus int64
		for i, a := range monsters {
			bonus += d[i]
			if v+bonus < int64(a) {
				return false
			}
			v -= int64(a)
			if v < 0 {
				v = 0
			}
		}
		return true
	}

	var left, right int64 = 0, 1000000000000000
	for left < right {
		mid := (left + right) / 2
		if check(mid) {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}

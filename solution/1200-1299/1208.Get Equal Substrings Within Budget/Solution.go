func equalSubstring(s string, t string, maxCost int) int {
	n := len(s)
	presum := make([]int, n+1)
	for i, c := range s {
		presum[i+1] = presum[i] + abs(int(c)-int(t[i]))
	}

	left, right := 0, n
	check := func(l int) bool {
		i := 0
		for i+l-1 < n {
			j := i + l - 1
			if presum[j+1]-presum[i] <= maxCost {
				return true
			}
			i++
		}
		return false
	}
	for left < right {
		mid := (left + right + 1) >> 1
		if check(mid) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}

func abs(x int) int {
	if x > 0 {
		return x
	}
	return -x
}
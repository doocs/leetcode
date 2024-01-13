func equalSubstring(s string, t string, maxCost int) int {
	n := len(s)
	f := make([]int, n+1)
	for i, a := range s {
		f[i+1] = f[i] + abs(int(a)-int(t[i]))
	}
	check := func(x int) bool {
		for i := 0; i+x-1 < n; i++ {
			if f[i+x]-f[i] <= maxCost {
				return true
			}
		}
		return false
	}
	l, r := 0, n
	for l < r {
		mid := (l + r + 1) >> 1
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
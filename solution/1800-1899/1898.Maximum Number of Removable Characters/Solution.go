func maximumRemovals(s string, p string, removable []int) int {
	check := func(k int) bool {
		ids := make(map[int]bool)
		for _, r := range removable[:k] {
			ids[r] = true
		}
		var i, j int
		for i < len(s) && j < len(p) {
			if !ids[i] && s[i] == p[j] {
				j++
			}
			i++
		}
		return j == len(p)
	}

	left, right := 0, len(removable)
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
func findContinuousSequence(target int) (ans [][]int) {
	l, r := 1, 2
	for l < r {
		s := (l + r) * (r - l + 1) / 2
		if s == target {
			t := make([]int, r-l+1)
			for i := range t {
				t[i] = l + i
			}
			ans = append(ans, t)
			l++
		} else if s < target {
			r++
		} else {
			l++
		}
	}
	return
}
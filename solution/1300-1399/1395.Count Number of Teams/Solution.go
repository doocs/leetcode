func numTeams(rating []int) (ans int) {
	n := len(rating)
	for i, b := range rating {
		l, r := 0, 0
		for _, a := range rating[:i] {
			if a < b {
				l++
			}
		}
		for _, c := range rating[i+1:] {
			if c < b {
				r++
			}
		}
		ans += l * r
		ans += (i - l) * (n - i - 1 - r)
	}
	return
}
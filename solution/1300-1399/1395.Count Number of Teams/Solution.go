func numTeams(rating []int) int {
	n, ans := len(rating), 0
	for j := 1; j < n-1; j++ {
		ia, ib, ka, kb := 0, 0, 0, 0
		for i := 0; i < j; i++ {
			if rating[i] < rating[j] {
				ia++
			} else if rating[i] > rating[j] {
				ib++
			}
		}
		for k := j + 1; k < n; k++ {
			if rating[j] < rating[k] {
				ka++
			} else if rating[j] > rating[k] {
				kb++
			}
		}
		ans += ia*ka + ib*kb
	}
	return ans
}
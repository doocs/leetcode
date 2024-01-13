func recoverArray(n int, sums []int) (ans []int) {
	sort.Ints(sums)
	for i := n; i > 0; i-- {
		k := 1 << i
		d := sums[k-1] - sums[k-2]
		cnt := map[int]int{}
		for _, s := range sums[:k] {
			cnt[s]++
		}
		sums1, sums2 := []int{}, []int{}
		sign := 1
		for _, s := range sums[:k] {
			if cnt[s] == 0 {
				continue
			}
			cnt[s]--
			cnt[s+d]--
			sums1 = append(sums1, s)
			sums2 = append(sums2, s+d)
			if s+d == 0 {
				sign = -1
			}
		}
		ans = append(ans, sign*d)
		if sign == -1 {
			sums1 = sums2
		}
		sums = sums1
	}
	return
}
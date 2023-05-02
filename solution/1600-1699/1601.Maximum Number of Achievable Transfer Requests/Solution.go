func maximumRequests(n int, requests [][]int) (ans int) {
	m := len(requests)
	check := func(mask int) bool {
		cnt := make([]int, n)
		for i, r := range requests {
			if mask>>i&1 == 1 {
				f, t := r[0], r[1]
				cnt[f]--
				cnt[t]++
			}
		}
		for _, v := range cnt {
			if v != 0 {
				return false
			}
		}
		return true
	}
	for mask := 0; mask < 1<<m; mask++ {
		cnt := bits.OnesCount(uint(mask))
		if ans < cnt && check(mask) {
			ans = cnt
		}
	}
	return
}
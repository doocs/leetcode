func maximumRequests(n int, requests [][]int) int {
	check := func(x int) bool {
		delta := make([]int, n)
		for i, r := range requests {
			if (x>>i)&1 == 1 {
				delta[r[0]]--
				delta[r[1]]++
			}
		}
		for _, d := range delta {
			if d != 0 {
				return false
			}
		}
		return true
	}

	ans, m := 0, len(requests)
	for mask := 0; mask < (1 << m); mask++ {
		cnt := bits.OnesCount(uint(mask))
		if cnt <= ans {
			continue
		}
		if check(mask) {
			ans = cnt
		}
	}
	return ans
}
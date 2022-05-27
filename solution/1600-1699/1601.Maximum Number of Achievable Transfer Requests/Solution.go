func maximumRequests(n int, requests [][]int) int {
	check := func(x int) bool {
		d := make([]int, n)
		for i, r := range requests {
			if (x>>i)&1 == 1 {
				d[r[0]]--
				d[r[1]]++
			}
		}
		for _, v := range d {
			if v != 0 {
				return false
			}
		}
		return true
	}

	ans, m := 0, len(requests)
	for mask := 0; mask < 1<<m; mask++ {
		cnt := bits.OnesCount(uint(mask))
		if ans < cnt && check(mask) {
			ans = cnt
		}
	}
	return ans
}
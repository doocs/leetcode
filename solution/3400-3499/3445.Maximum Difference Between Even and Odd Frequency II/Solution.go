func maxDifference(s string, k int) int {
	n := len(s)
	inf := math.MaxInt32 / 2
	ans := -inf

	for a := 0; a < 5; a++ {
		for b := 0; b < 5; b++ {
			if a == b {
				continue
			}
			curA, curB := 0, 0
			preA, preB := 0, 0
			t := [2][2]int{{inf, inf}, {inf, inf}}
			l := -1

			for r := 0; r < n; r++ {
				if s[r] == byte('0'+a) {
					curA++
				}
				if s[r] == byte('0'+b) {
					curB++
				}

				for r-l >= k && curB-preB >= 2 {
					t[preA&1][preB&1] = min(t[preA&1][preB&1], preA-preB)
					l++
					if s[l] == byte('0'+a) {
						preA++
					}
					if s[l] == byte('0'+b) {
						preB++
					}
				}

				ans = max(ans, curA-curB-t[curA&1^1][curB&1])
			}
		}
	}

	return ans
}
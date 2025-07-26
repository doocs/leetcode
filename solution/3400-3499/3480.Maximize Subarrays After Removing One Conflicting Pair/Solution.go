func maxSubarrays(n int, conflictingPairs [][]int) (ans int64) {
	g := make([][]int, n+1)
	for _, pair := range conflictingPairs {
		a, b := pair[0], pair[1]
		if a > b {
			a, b = b, a
		}
		g[a] = append(g[a], b)
	}

	cnt := make([]int64, n+2)
	var add int64
	b1, b2 := n+1, n+1

	for a := n; a > 0; a-- {
		for _, b := range g[a] {
			if b < b1 {
				b2 = b1
				b1 = b
			} else if b < b2 {
				b2 = b
			}
		}
		ans += int64(b1 - a)
		cnt[b1] += int64(b2 - b1)
		if cnt[b1] > add {
			add = cnt[b1]
		}
	}

	ans += add
	return ans
}

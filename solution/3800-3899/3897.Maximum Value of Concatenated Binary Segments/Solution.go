const MOD int = 1_000_000_007

func maxValue(nums1 []int, nums0 []int) int {
	type pair struct{ x, y int }

	pairs := make([]pair, len(nums1))
	b := 0
	for i := range nums1 {
		pairs[i] = pair{nums1[i], nums0[i]}
		b += nums1[i] + nums0[i]
	}

	group := func(p pair) int {
		if p.y == 0 {
			return 0
		}
		if p.x > 0 {
			return 1
		}
		return 2
	}

	sort.Slice(pairs, func(i, j int) bool {
		a, b := pairs[i], pairs[j]
		g1, g2 := group(a), group(b)
		if g1 != g2 {
			return g1 < g2
		}
		if g1 == 0 {
			return a.x > b.x
		}
		if g1 == 1 {
			if a.x != b.x {
				return a.x > b.x
			}
			return a.y < b.y
		}
		return a.y < b.y
	})

	p := make([]int, b)
	p[0] = 1
	for i := 1; i < b; i++ {
		p[i] = p[i-1] * 2 % MOD
	}

	ans := 0
	b--
	for _, pr := range pairs {
		cnt1, cnt0 := pr.x, pr.y
		for cnt1 > 0 {
			ans = (ans + p[b]) % MOD
			b--
			cnt1--
		}
		b -= cnt0
	}
	return ans
}

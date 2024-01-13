func countPairs(deliciousness []int) (ans int) {
	cnt := map[int]int{}
	for _, d := range deliciousness {
		cnt[d]++
	}
	const mod int = 1e9 + 7
	for i := 0; i < 22; i++ {
		s := 1 << i
		for a, m := range cnt {
			b := s - a
			if n, ok := cnt[b]; ok {
				if a == b {
					ans += m * (m - 1)
				} else {
					ans += m * n
				}
			}
		}
	}
	ans >>= 1
	return ans % mod
}
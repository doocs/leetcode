func countTrapezoids(points [][]int) int {
	const mod = 1_000_000_007
	cnt := make(map[int]int)
	for _, p := range points {
		cnt[p[1]]++
	}

	var ans, s int64
	for _, v := range cnt {
		t := int64(v) * int64(v-1) / 2
		ans = (ans + s*t) % mod
		s += t
	}
	return int(ans)
}

func minOperations(queries [][]int) (ans int64) {
	f := func(x int64) (res int64) {
		var p int64 = 1
		i := int64(1)
		for p <= x {
			cnt := min(p*4-1, x) - p + 1
			res += cnt * i
			i++
			p *= 4
		}
		return
	}
	for _, q := range queries {
		l, r := int64(q[0]), int64(q[1])
		s := f(r) - f(l-1)
		mx := f(r) - f(r-1)
		ans += max((s+1)/2, mx)
	}
	return
}

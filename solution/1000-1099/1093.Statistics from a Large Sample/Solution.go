func sampleStats(count []int) []float64 {
	find := func(i int) int {
		for k, t := 0, 0; ; k++ {
			t += count[k]
			if t >= i {
				return k
			}
		}
	}
	mi, mx := 1<<30, -1
	s, cnt, mode := 0, 0, 0
	for k, x := range count {
		if x > 0 {
			mi = min(mi, k)
			mx = max(mx, k)
			s += k * x
			cnt += x
			if x > count[mode] {
				mode = k
			}
		}
	}
	var median float64
	if cnt&1 == 1 {
		median = float64(find(cnt/2 + 1))
	} else {
		median = float64(find(cnt/2)+find(cnt/2+1)) / 2
	}
	return []float64{float64(mi), float64(mx), float64(s) / float64(cnt), median, float64(mode)}
}
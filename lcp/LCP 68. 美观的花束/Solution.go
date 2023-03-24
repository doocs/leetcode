func beautifulBouquet(flowers []int, cnt int) (ans int) {
	mx := 0
	for _, x := range flowers {
		mx = max(mx, x)
	}
	d := make([]int, mx+1)
	j := 0
	const mod = 1e9 + 7
	for i, x := range flowers {
		d[x]++
		for d[x] > cnt {
			d[flowers[j]]--
			j++
		}
		ans = (ans + i - j + 1) % mod
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
func maximumTotalDamage(power []int) int64 {
	n := len(power)
	sort.Ints(power)
	cnt := map[int]int{}
	nxt := make([]int, n)
	f := make([]int64, n+1)
	for i, x := range power {
		cnt[x]++
		nxt[i] = sort.SearchInts(power, x+3)
	}
	for i := n - 1; i >= 0; i-- {
		j := i + cnt[power[i]]
		a := int64(0)
		if j <= n {
			a = f[j]
		}
		b := int64(power[i])*int64(cnt[power[i]]) + f[nxt[i]]
		f[i] = max(a, b)
	}
	return f[0]
}

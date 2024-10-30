func numberOfWays(s string) (ans int64) {
	n := len(s)
	l := [2]int{}
	r := [2]int{}
	r[0] = strings.Count(s, "0")
	r[1] = n - r[0]
	for _, c := range s {
		x := int(c - '0')
		r[x]--
		ans += int64(l[x^1] * r[x^1])
		l[x]++
	}
	return
}

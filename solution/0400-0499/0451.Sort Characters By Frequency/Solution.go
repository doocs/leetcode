func frequencySort(s string) string {
	cnt := map[byte]int{}
	for i := range s {
		cnt[s[i]]++
	}
	cs := make([]byte, 0, len(s))
	for c := range cnt {
		cs = append(cs, c)
	}
	sort.Slice(cs, func(i, j int) bool { return cnt[cs[i]] > cnt[cs[j]] })
	ans := make([]byte, 0, len(s))
	for _, c := range cs {
		ans = append(ans, bytes.Repeat([]byte{c}, cnt[c])...)
	}
	return string(ans)
}
func largestCombination(candidates []int) (ans int) {
	mx := slices.Max(candidates)
	m := bits.Len(uint(mx))
	for i := 0; i < m; i++ {
		cnt := 0
		for _, x := range candidates {
			cnt += (x >> i) & 1
		}
		ans = max(ans, cnt)
	}
	return
}

func countPairs(deliciousness []int) (ans int) {
	mx := slices.Max(deliciousness) << 1
	const mod int = 1e9 + 7
	cnt := map[int]int{}
	for _, d := range deliciousness {
		for s := 1; s <= mx; s <<= 1 {
			ans = (ans + cnt[s-d]) % mod
		}
		cnt[d]++
	}
	return
}
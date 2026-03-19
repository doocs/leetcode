func prefixConnected(words []string, k int) int {
	cnt := make(map[string]int)
	for _, w := range words {
		if len(w) >= k {
			cnt[w[:k]]++
		}
	}
	ans := 0
	for _, v := range cnt {
		if v > 1 {
			ans++
		}
	}
	return ans
}

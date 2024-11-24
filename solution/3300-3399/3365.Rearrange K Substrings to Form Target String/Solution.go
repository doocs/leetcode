func isPossibleToRearrange(s string, t string, k int) bool {
	n := len(s)
	m := n / k
	cnt := map[string]int{}
	for i := 0; i < n; i += m {
		cnt[s[i:i+m]]++
		cnt[t[i:i+m]]--
	}
	for _, v := range cnt {
		if v != 0 {
			return false
		}
	}
	return true
}

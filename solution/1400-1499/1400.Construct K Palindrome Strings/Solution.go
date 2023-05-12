func canConstruct(s string, k int) bool {
	if len(s) < k {
		return false
	}
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	x := 0
	for _, v := range cnt {
		x += v & 1
	}
	return x <= k
}
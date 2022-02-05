func canConstruct(s string, k int) bool {
	if len(s) < k {
		return false
	}
	counter := make([]int, 26)
	for _, c := range s {
		counter[c-'a']++
	}
	cnt := 0
	for _, v := range counter {
		if v%2 == 1 {
			cnt++
		}
	}
	return cnt <= k
}
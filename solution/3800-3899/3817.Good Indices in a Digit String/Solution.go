func goodIndices(s string) (ans []int) {
	for i := range s {
		t := strconv.Itoa(i)
		k := len(t)
		if s[i+1-k:i+1] == t {
			ans = append(ans, i)
		}
	}
	return
}

func findArray(pref []int) []int {
	n := len(pref)
	ans := []int{pref[0]}
	for i := 1; i < n; i++ {
		ans = append(ans, pref[i-1]^pref[i])
	}
	return ans
}
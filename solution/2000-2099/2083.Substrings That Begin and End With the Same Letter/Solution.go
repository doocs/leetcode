func numberOfSubstrings(s string) int64 {
	var ans int64
	counter := make([]int64, 26)
	for _, c := range s {
		i := c - 'a'
		counter[i]++
		ans += counter[i]
	}
	return ans
}
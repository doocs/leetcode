func findLongestSubarray(array []string) []string {
	vis := map[int]int{0: -1}
	var s, mx, k int
	for i, x := range array {
		if x[0] >= 'A' {
			s++
		} else {
			s--
		}
		if j, ok := vis[s]; ok {
			if mx < i-j {
				mx = i - j
				k = j + 1
			}
		} else {
			vis[s] = i
		}
	}
	return array[k : k+mx]
}
func sortString(s string) string {
	counter := ['z' + 1]int{}
	for _, c := range s {
		counter[c]++
	}
	var ans []byte
	for len(ans) < len(s) {
		for i := byte('a'); i <= 'z'; i++ {
			if counter[i] > 0 {
				ans = append(ans, i)
				counter[i]--
			}
		}
		for i := byte('z'); i >= 'a'; i-- {
			if counter[i] > 0 {
				ans = append(ans, i)
				counter[i]--
			}
		}
	}
	return string(ans)
}
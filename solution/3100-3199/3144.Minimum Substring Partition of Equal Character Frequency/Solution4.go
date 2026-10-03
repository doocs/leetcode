func minimumSubstringsInPartition(s string) int {
	n := len(s)
	f := make([]int, n+1)
	for i := n - 1; i >= 0; i-- {
		cnt := [26]int{}
		freq := map[int]int{}
		ans := n - i
		for j := i; j < n; j++ {
			k := int(s[j] - 'a')
			if cnt[k] > 0 {
				freq[cnt[k]]--
				if freq[cnt[k]] == 0 {
					delete(freq, cnt[k])
				}
			}
			cnt[k]++
			freq[cnt[k]]++
			if len(freq) == 1 {
				ans = min(ans, 1+f[j+1])
			}
		}
		f[i] = ans
	}
	return f[0]
}

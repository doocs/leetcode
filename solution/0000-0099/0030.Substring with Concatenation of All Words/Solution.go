func findSubstring(s string, words []string) (ans []int) {
	cnt := make(map[string]int)
	for _, w := range words {
		cnt[w]++
	}
	m, n, k := len(s), len(words), len(words[0])
	for i := 0; i < k; i++ {
		l, r := i, i
		cnt1 := make(map[string]int)
		for r+k <= m {
			t := s[r : r+k]
			r += k

			if _, exists := cnt[t]; !exists {
				cnt1 = make(map[string]int)
				l = r
				continue
			}
			cnt1[t]++
			for cnt1[t] > cnt[t] {
				w := s[l : l+k]
				cnt1[w]--
				if cnt1[w] == 0 {
					delete(cnt1, w)
				}
				l += k
			}
			if r-l == n*k {
				ans = append(ans, l)
			}
		}
	}
	return
}

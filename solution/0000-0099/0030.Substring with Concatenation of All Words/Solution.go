func findSubstring(s string, words []string) (ans []int) {
	cnt := map[string]int{}
	for _, w := range words {
		cnt[w]++
	}
	m, n, k := len(s), len(words), len(words[0])
	for i := 0; i < k; i++ {
		cnt1 := map[string]int{}
		l, r, t := i, i, 0
		for r+k <= m {
			w := s[r : r+k]
			r += k
			if _, ok := cnt[w]; !ok {
				l, t = r, 0
				cnt1 = map[string]int{}
				continue
			}
			cnt1[w]++
			t++
			for cnt1[w] > cnt[w] {
				cnt1[s[l:l+k]]--
				l += k
				t--
			}
			if t == n {
				ans = append(ans, l)
			}
		}
	}
	return
}
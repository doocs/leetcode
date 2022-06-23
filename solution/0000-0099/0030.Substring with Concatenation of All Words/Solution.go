func findSubstring(s string, words []string) []int {
	cnt := map[string]int{}
	for _, w := range words {
		cnt[w]++
	}
	subLen := len(words[0])
	n, m := len(s), len(words)
	var ans []int
	for i := 0; i < subLen; i++ {
		cnt1 := map[string]int{}
		l, r := i, i
		t := 0
		for r+subLen <= n {
			w := s[r : r+subLen]
			r += subLen
			if _, ok := cnt[w]; !ok {
				l = r
				t = 0
				cnt1 = map[string]int{}
				continue
			}
			cnt1[w]++
			t++
			for cnt1[w] > cnt[w] {
				remove := s[l : l+subLen]
				l += subLen
				cnt1[remove]--
				t--
			}
			if t == m {
				ans = append(ans, l)
			}
		}
	}
	return ans
}
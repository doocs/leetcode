func beautySum(s string) (ans int) {
	n := len(s)
	for i := 0; i < n; i++ {
		cnt := [26]int{}
		freq := map[int]int{}
		mi, mx := 1, 1
		for j := i; j < n; j++ {
			k := int(s[j] - 'a')
			freq[cnt[k]]--
			cnt[k]++
			freq[cnt[k]]++

			if cnt[k] == 1 {
				mi = 1
			}
			if freq[mi] == 0 {
				mi++
			}
			if cnt[k] > mx {
				mx = cnt[k]
			}
			ans += mx - mi
		}
	}
	return
}
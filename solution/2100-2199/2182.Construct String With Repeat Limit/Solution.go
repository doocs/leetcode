func repeatLimitedString(s string, repeatLimit int) string {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	var ans []byte
	for i, j := 25, 24; i >= 0; i-- {
		j = min(j, i-1)
		for {
			for k := min(cnt[i], repeatLimit); k > 0; k-- {
				ans = append(ans, byte(i+'a'))
				cnt[i]--
			}
			if cnt[i] == 0 {
				break
			}
			for j >= 0 && cnt[j] == 0 {
				j--
			}
			if j < 0 {
				break
			}
			ans = append(ans, byte(j+'a'))
			cnt[j]--
		}
	}
	return string(ans)
}
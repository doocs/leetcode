func repeatLimitedString(s string, repeatLimit int) string {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	var ans []byte
	for i := 25; i >= 0; i-- {
		j := i - 1
		for {
			for k := min(cnt[i], repeatLimit); k > 0; k-- {
				cnt[i]--
				ans = append(ans, 'a'+byte(i))
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
			cnt[j]--
			ans = append(ans, 'a'+byte(j))
		}
	}
	return string(ans)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
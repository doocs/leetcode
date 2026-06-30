func numberOfSubstrings(s string) int {
	ans, l := 0, 0
	cnt := [3]int{}

	for r := 0; r < len(s); r++ {
		cnt[s[r]-'a']++

		for cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0 {
			cnt[s[l]-'a']--
			l++
		}

		ans += l
	}

	return ans
}

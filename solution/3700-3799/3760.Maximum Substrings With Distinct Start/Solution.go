func maxDistinct(s string) (ans int) {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
		if cnt[c-'a'] == 1 {
			ans++
		}
	}
	return
}

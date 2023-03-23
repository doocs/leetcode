func minNumBooths(demand []string) (ans int) {
	cnt := [26]int{}
	for _, s := range demand {
		for _, c := range s {
			if cnt[c-'a'] > 0 {
				cnt[c-'a']--
			} else {
				ans++
			}
		}
		for _, c := range s {
			cnt[c-'a']++
		}
	}
	return
}
func minimumLength(s string) (ans int) {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	for _, x := range cnt {
		if x > 0 {
			if x&1 == 1 {
				ans += 1
			} else {
				ans += 2
			}
		}
	}
	return
}
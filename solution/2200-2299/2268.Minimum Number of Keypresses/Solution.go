func minimumKeypresses(s string) (ans int) {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	sort.Ints(cnt)
	k := 1
	for i := 1; i <= 26; i++ {
		ans += k * cnt[26-i]
		if i%9 == 0 {
			k++
		}
	}
	return
}
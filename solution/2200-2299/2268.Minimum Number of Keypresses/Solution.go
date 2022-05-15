func minimumKeypresses(s string) int {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	sort.Ints(cnt)
	ans := 0
	for i, j := 1, 1; i <= 26; i++ {
		ans += j * cnt[26-i]
		if i%9 == 0 {
			j++
		}
	}
	return ans
}
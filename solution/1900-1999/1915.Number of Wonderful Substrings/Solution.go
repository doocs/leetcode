func wonderfulSubstrings(word string) (ans int64) {
	cnt := [1024]int{1}
	st := 0
	for _, c := range word {
		st ^= 1 << (c - 'a')
		ans += int64(cnt[st])
		for i := 0; i < 10; i++ {
			ans += int64(cnt[st^(1<<i)])
		}
		cnt[st]++
	}
	return
}
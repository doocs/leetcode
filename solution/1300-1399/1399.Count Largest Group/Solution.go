func countLargestGroup(n int) (ans int) {
	cnt := [40]int{}
	mx := 0
	for i := 1; i <= n; i++ {
		s := 0
		for x := i; x > 0; x /= 10 {
			s += x % 10
		}
		cnt[s]++
		if mx < cnt[s] {
			mx = cnt[s]
			ans = 1
		} else if mx == cnt[s] {
			ans++
		}
	}
	return
}
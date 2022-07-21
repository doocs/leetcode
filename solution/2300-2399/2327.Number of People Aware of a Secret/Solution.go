func peopleAwareOfSecret(n int, delay int, forget int) int {
	m := (n << 1) + 10
	d := make([]int, m)
	cnt := make([]int, m)
	mod := int(1e9) + 7
	cnt[1] = 1
	for i := 1; i <= n; i++ {
		if cnt[i] == 0 {
			continue
		}
		d[i] = (d[i] + cnt[i]) % mod
		d[i+forget] = (d[i+forget] - cnt[i] + mod) % mod
		nxt := i + delay
		for nxt < i+forget {
			cnt[nxt] = (cnt[nxt] + cnt[i]) % mod
			nxt++
		}
	}
	ans := 0
	for i := 1; i <= n; i++ {
		ans = (ans + d[i]) % mod
	}
	return ans
}
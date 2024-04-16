func threeSumMulti(arr []int, target int) (ans int) {
	const mod int = 1e9 + 7
	cnt := [101]int{}
	for _, x := range arr {
		cnt[x]++
	}
	for j, b := range arr {
		cnt[b]--
		for _, a := range arr[:j] {
			if c := target - a - b; c >= 0 && c < len(cnt) {
				ans = (ans + cnt[c]) % mod
			}
		}
	}
	return
}
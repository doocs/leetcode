func numOfSubarrays(arr []int) (ans int) {
	cnt := [2]int{1, 0}
	s := 0
	const mod int = 1e9 + 7
	for _, x := range arr {
		s = (s + x) % 2
		ans = (ans + cnt[s^1]) % mod
		cnt[s]++
	}
	return
}

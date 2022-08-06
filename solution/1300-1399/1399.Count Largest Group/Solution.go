func countLargestGroup(n int) int {
	cnt := make([]int, 40)
	mx, ans := 0, 0
	for i := 1; i <= n; i++ {
		t := 0
		j := i
		for j != 0 {
			t += j % 10
			j /= 10
		}
		cnt[t]++
		if mx < cnt[t] {
			mx = cnt[t]
			ans = 1
		} else if mx == cnt[t] {
			ans++
		}
	}
	return ans
}
func minimumSubstringsInPartition(s string) int {
	n := len(s)
	f := make([]int, n+1)
	for i := n - 1; i >= 0; i-- {
		cnt := [26]int{}
		ans := n - i
		k, m := 0, 0
		for j := i; j < n; j++ {
			x := int(s[j] - 'a')
			cnt[x]++
			if cnt[x] == 1 {
				k++
			}
			m = max(m, cnt[x])
			if j-i+1 == k*m {
				ans = min(ans, 1+f[j+1])
			}
		}
		f[i] = ans
	}
	return f[0]
}

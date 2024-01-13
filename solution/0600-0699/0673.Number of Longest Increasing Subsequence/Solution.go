func findNumberOfLIS(nums []int) (ans int) {
	n, mx := len(nums), 0
	f := make([]int, n)
	cnt := make([]int, n)
	for i, x := range nums {
		for j, y := range nums[:i] {
			if y < x {
				if f[i] < f[j]+1 {
					f[i] = f[j] + 1
					cnt[i] = cnt[j]
				} else if f[i] == f[j]+1 {
					cnt[i] += cnt[j]
				}
			}
		}
		if mx < f[i] {
			mx = f[i]
			ans = cnt[i]
		} else if mx == f[i] {
			ans += cnt[i]
		}
	}
	return
}
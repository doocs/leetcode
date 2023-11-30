func sumOfFlooredPairs(nums []int) (ans int) {
	mx := slices.Max(nums)
	cnt := make([]int, mx+1)
	s := make([]int, mx+1)
	for _, x := range nums {
		cnt[x]++
	}
	for i := 1; i <= mx; i++ {
		s[i] = s[i-1] + cnt[i]
	}
	const mod int = 1e9 + 7
	for y := 1; y <= mx; y++ {
		if cnt[y] > 0 {
			for d := 1; d*y <= mx; d++ {
				ans += d * cnt[y] * (s[min((d+1)*y-1, mx)] - s[d*y-1])
				ans %= mod
			}
		}
	}
	return
}
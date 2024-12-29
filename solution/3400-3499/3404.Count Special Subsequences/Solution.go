func numberOfSubsequences(nums []int) (ans int64) {
	n := len(nums)
	cnt := make(map[int]int)
	gcd := func(a, b int) int {
		for b != 0 {
			a, b = b, a%b
		}
		return a
	}
	for r := 4; r < n-2; r++ {
		c := nums[r]
		for s := r + 2; s < n; s++ {
			d := nums[s]
			g := gcd(c, d)
			key := ((d / g) << 12) | (c / g)
			cnt[key]++
		}
	}
	for q := 2; q < n-4; q++ {
		b := nums[q]
		for p := 0; p < q-1; p++ {
			a := nums[p]
			g := gcd(a, b)
			key := ((a / g) << 12) | (b / g)
			ans += int64(cnt[key])
		}
		c := nums[q+2]
		for s := q + 4; s < n; s++ {
			d := nums[s]
			g := gcd(c, d)
			key := ((d / g) << 12) | (c / g)
			cnt[key]--
		}
	}
	return
}

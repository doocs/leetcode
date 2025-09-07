func getLeastFrequentDigit(n int) (ans int) {
	cnt := [10]int{}
	for ; n > 0; n /= 10 {
		cnt[n%10]++
	}
	f := 1 << 30
	for x, v := range cnt {
		if v > 0 && v < f {
			f = v
			ans = x
		}
	}
	return
}

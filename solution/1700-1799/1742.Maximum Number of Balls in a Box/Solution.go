func countBalls(lowLimit int, highLimit int) (ans int) {
	cnt := [50]int{}
	for i := lowLimit; i <= highLimit; i++ {
		y := 0
		for x := i; x > 0; x /= 10 {
			y += x % 10
		}
		cnt[y]++
		if ans < cnt[y] {
			ans = cnt[y]
		}
	}
	return
}
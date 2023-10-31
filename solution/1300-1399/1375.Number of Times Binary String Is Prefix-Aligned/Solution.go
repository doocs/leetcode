func numTimesAllBlue(flips []int) (ans int) {
	mx := 0
	for i, x := range flips {
		mx = max(mx, x)
		if mx == i+1 {
			ans++
		}
	}
	return
}
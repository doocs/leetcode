func storeWater(bucket []int, vat []int) int {
	mx := 0
	for _, x := range vat {
		mx = max(mx, x)
	}
	if mx == 0 {
		return 0
	}
	ans := 1 << 30
	for x := 1; x <= mx; x++ {
		y := 0
		for i, v := range vat {
			y += max(0, (v+x-1)/x-bucket[i])
		}
		ans = min(ans, x+y)
	}
	return ans
}
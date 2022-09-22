func closestFair(n int) int {
	a, b := 0, 0
	t, k := n, 0
	for t > 0 {
		if (t%10)%2 == 1 {
			a++
		} else {
			b++
		}
		k++
		t /= 10
	}
	if a == b {
		return n
	}
	if k%2 == 1 {
		x := int(math.Pow(10, float64(k)))
		y := 0
		for i := 0; i < k>>1; i++ {
			y = y*10 + 1
		}
		return x + y
	}
	return closestFair(n + 1)
}
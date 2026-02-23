func isDigitorialPermutation(n int) bool {
	f := make([]int, 10)
	f[0] = 1
	for i := 1; i < 10; i++ {
		f[i] = f[i-1] * i
	}

	x := 0
	y := n

	for y > 0 {
		x += f[y%10]
		y /= 10
	}

	a := []byte(strconv.Itoa(x))
	b := []byte(strconv.Itoa(n))

	sort.Slice(a, func(i, j int) bool { return a[i] < a[j] })
	sort.Slice(b, func(i, j int) bool { return b[i] < b[j] })

	return string(a) == string(b)
}

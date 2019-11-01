func addBinary(a string, b string) string {
	for len(a) > len(b) {
		b = "0" + b
	}
	for len(a) < len(b) {
		a = "0" + a
	}
	zero := []byte("0")[0]
	ret := make([]byte, len(a))
	for right := len(a) - 1; right > 0; right-- {
		t := ret[right] + a[right] + b[right] - zero*2
		ret[right] = t%2 + zero
		if t >= 2 {
			ret[right-1] = 1
		}
	}
	t := ret[0] + a[0] + b[0] - zero*2
	ret[0] = t%2 + zero
	if t >= 2 {
		ret = append([]byte("1"), ret...)
	}

	return string(ret)
}

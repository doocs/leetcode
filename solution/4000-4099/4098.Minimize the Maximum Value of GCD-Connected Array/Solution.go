func minimumMaxValue(n int, index int, value int) int64 {
	if value == 1 {
		return -1
	}

	p := int64(value)
	for x := int64(2); x*x <= int64(value); x++ {
		if int64(value)%x == 0 {
			p = x
			break
		}
	}

	if int64(value)-p-(int64(index)-1)*2 <= 0 {
		return -1
	}
	if index == n-1 {
		return int64(value)
	}
	return int64(value) + p + (int64(n)-2-int64(index))*2
}

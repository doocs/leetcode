func findNthDigit(n int) int {
	if n < 10 {
		return n
	}
	n -= 10
	k, p := 2, 10
	for n >= 9*k*p {
		n -= 9 * k * p
		k++
		p *= 10
	}
	x := p + n/k
	return int(strconv.Itoa(x)[n%k] - '0')
}
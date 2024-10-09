func kthCharacter(k int64, operations []int) byte {
	n := int64(1)
	i := 0
	for n < k {
		n *= 2
		i++
	}
	d := 0
	for n > 1 {
		if k > n/2 {
			k -= n / 2
			d += operations[i-1]
		}
		n /= 2
		i--
	}
	return byte('a' + (d % 26))
}

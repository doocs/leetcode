func findInteger(k int, digit1 int, digit2 int) int {
	if digit1 == 0 && digit2 == 0 {
		return -1
	}
	if digit1 > digit2 {
		digit1, digit2 = digit2, digit1
	}
	q := []int{0}
	for {
		x := q[0]
		q = q[1:]
		if x > math.MaxInt32 {
			return -1
		}
		if x > k && x%k == 0 {
			return x
		}
		q = append(q, x*10+digit1)
		if digit1 != digit2 {
			q = append(q, x*10+digit2)
		}
	}
}
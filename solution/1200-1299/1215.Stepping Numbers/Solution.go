func countSteppingNumbers(low int, high int) []int {
	ans := []int{}
	if low == 0 {
		ans = append(ans, 0)
	}
	q := []int{1, 2, 3, 4, 5, 6, 7, 8, 9}
	for len(q) > 0 {
		v := q[0]
		q = q[1:]
		if v > high {
			break
		}
		if v >= low {
			ans = append(ans, v)
		}
		x := v % 10
		if x > 0 {
			q = append(q, v*10+x-1)
		}
		if x < 9 {
			q = append(q, v*10+x+1)
		}
	}
	return ans
}
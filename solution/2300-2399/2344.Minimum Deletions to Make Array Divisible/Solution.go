func minOperations(nums []int, numsDivide []int) int {
	x := numsDivide[0]
	for _, v := range numsDivide[1:] {
		x = gcd(x, v)
	}
	sort.Ints(nums)
	for i, v := range nums {
		if x%v == 0 {
			return i
		}
	}
	return -1
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
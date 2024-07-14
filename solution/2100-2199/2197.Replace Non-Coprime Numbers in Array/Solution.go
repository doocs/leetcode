func replaceNonCoprimes(nums []int) []int {
	stk := []int{}
	for _, x := range nums {
		stk = append(stk, x)
		for len(stk) > 1 {
			x = stk[len(stk)-1]
			y := stk[len(stk)-2]
			g := gcd(x, y)
			if g == 1 {
				break
			}
			stk = stk[:len(stk)-1]
			stk[len(stk)-1] = x * y / g
		}
	}
	return stk
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
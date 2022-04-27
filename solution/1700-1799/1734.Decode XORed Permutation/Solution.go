func decode(encoded []int) []int {
	n := len(encoded) + 1
	ans := make([]int, n)
	a, b := 0, 0
	for i := 0; i < n-1; i += 2 {
		a ^= encoded[i]
	}
	for i := 0; i < n+1; i++ {
		b ^= i
	}
	ans[n-1] = a ^ b
	for i := n - 2; i >= 0; i-- {
		ans[i] = ans[i+1] ^ encoded[i]
	}
	return ans
}
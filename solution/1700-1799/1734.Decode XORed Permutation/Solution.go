func decode(encoded []int) []int {
	n := len(encoded) + 1
	a, b := 0, 0
	for i := 0; i < n-1; i += 2 {
		a ^= encoded[i]
	}
	for i := 1; i <= n; i++ {
		b ^= i
	}
	perm := make([]int, n)
	perm[n-1] = a ^ b
	for i := n - 2; i >= 0; i-- {
		perm[i] = encoded[i] ^ perm[i+1]
	}
	return perm
}
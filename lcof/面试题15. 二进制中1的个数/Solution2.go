func hammingWeight(n uint32) (ans int) {
	for n != 0 {
		n -= n & -n
		ans++
	}
	return
}
func sumOfGoodIntegers(n int, k int) (ans int) {
	start := max(1, n-k)
	end := n + k
	for x := start; x <= end; x++ {
		if (n & x) == 0 {
			ans += x
		}
	}
	return
}
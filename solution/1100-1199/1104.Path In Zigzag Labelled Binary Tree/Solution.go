func pathInZigZagTree(label int) (ans []int) {
	x, i := 1, 1
	for x<<1 <= label {
		x <<= 1
		i++
	}
	for ; i > 0; i-- {
		ans = append(ans, label)
		label = ((1 << (i - 1)) + (1 << i) - 1 - label) >> 1
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return
}
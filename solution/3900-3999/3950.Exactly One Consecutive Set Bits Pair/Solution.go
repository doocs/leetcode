func consecutiveSetBits(n int) bool {
	vis := false
	for pre := 0; n > 0; n >>= 1 {
		cur := n & 1
		if pre == cur && cur == 1 {
			if vis {
				return false
			}
			vis = true
		}
		pre = cur
	}
	return vis
}
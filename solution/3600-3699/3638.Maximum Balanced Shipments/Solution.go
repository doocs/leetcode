func maxBalancedShipments(weight []int) (ans int) {
	mx := 0
	for _, x := range weight {
		mx = max(mx, x)
		if x < mx {
			ans++
			mx = 0
		}
	}
	return
}
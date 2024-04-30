func divisorSubstrings(num int, k int) (ans int) {
	x, p, t := 0, 1, num
	for ; k > 0; k-- {
		v := t % 10
		t /= 10
		x = p*v + x
		p *= 10
	}
	if x != 0 && num%x == 0 {
		ans++
	}
	for p /= 10; t > 0; t /= 10 {
		x /= 10
		v := t % 10
		x = p*v + x
		if x != 0 && num%x == 0 {
			ans++
		}
	}
	return
}
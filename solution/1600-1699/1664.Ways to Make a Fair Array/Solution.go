func waysToMakeFair(nums []int) (ans int) {
	x, y := 0, 0
	for i, v := range nums {
		if i%2 == 1 {
			x += v
		} else {
			y += v
		}
	}
	a, b := 0, 0
	for i, v := range nums {
		if i%2 == 1 && x-v-a+b == y-b+a {
			ans++
		}
		if i%2 == 0 && y-v-b+a == x-a+b {
			ans++
		}
		if i%2 == 1 {
			a += v
		} else {
			b += v
		}
	}
	return
}
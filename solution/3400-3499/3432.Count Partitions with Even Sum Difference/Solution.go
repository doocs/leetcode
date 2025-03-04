func countPartitions(nums []int) (ans int) {
	l, r := 0, 0
	for _, x := range nums {
		r += x
	}
	for _, x := range nums[:len(nums)-1] {
		l += x
		r -= x
		if (l-r)%2 == 0 {
			ans++
		}
	}
	return
}

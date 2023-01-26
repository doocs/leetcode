func waysToMakeFair(nums []int) (ans int) {
	var s1, s2, t1, t2 int
	for i, v := range nums {
		if i%2 == 0 {
			s1 += v
		} else {
			s2 += v
		}
	}
	for i, v := range nums {
		if i%2 == 0 && t2+s1-t1-v == t1+s2-t2 {
			ans++
		}
		if i%2 == 1 && t2+s1-t1 == t1+s2-t2-v {
			ans++
		}
		if i%2 == 0 {
			t1 += v
		} else {
			t2 += v
		}
	}
	return
}
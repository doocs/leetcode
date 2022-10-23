func makeSimilar(nums []int, target []int) int64 {
	sort.Ints(nums)
	sort.Ints(target)
	a1, a2, b1, b2 := []int{}, []int{}, []int{}, []int{}
	for _, v := range nums {
		if v%2 == 0 {
			a1 = append(a1, v)
		} else {
			a2 = append(a2, v)
		}
	}
	for _, v := range target {
		if v%2 == 0 {
			b1 = append(b1, v)
		} else {
			b2 = append(b2, v)
		}
	}
	ans := 0
	for i := 0; i < len(a1); i++ {
		ans += abs(a1[i] - b1[i])
	}
	for i := 0; i < len(a2); i++ {
		ans += abs(a2[i] - b2[i])
	}
	return int64(ans / 4)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
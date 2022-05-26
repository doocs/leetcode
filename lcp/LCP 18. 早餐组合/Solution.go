func breakfastNumber(staple []int, drinks []int, x int) int {
	res, n := 0, len(drinks)
	sort.Ints(drinks)
	for _, s := range staple {
		remain := x - s
		if remain >= drinks[0] {
			left, right := 0, n-1
			for left < right {
				mid := (left + right + 1) >> 1
				if drinks[mid] <= remain {
					left = mid
				} else {
					right = mid - 1
				}
			}
			res = (res + left + 1) % 1000000007
		}
	}
	return res
}
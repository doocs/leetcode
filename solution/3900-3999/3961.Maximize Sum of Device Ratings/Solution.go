func maxRatings(units [][]int) int64 {
	n := len(units[0])
	if n == 1 {
		var ans int64
		for _, x := range units {
			ans += int64(x[0])
		}
		return ans
	}

	var ans int64
	mn, mn2 := int(^uint(0)>>1), int(^uint(0)>>1)

	for _, x := range units {
		sort.Ints(x)
		ans += int64(x[1])
		if x[1] < mn2 {
			mn2 = x[1]
		}
		if x[0] < mn {
			mn = x[0]
		}
	}

	return ans - int64(mn2-mn)
}
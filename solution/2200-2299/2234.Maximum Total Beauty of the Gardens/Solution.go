func maximumBeauty(flowers []int, newFlowers int64, target int, full int, partial int) int64 {
	sort.Ints(flowers)
	n := len(flowers)
	s := make([]int, n+1)
	for i, x := range flowers {
		s[i+1] = s[i] + x
	}
	ans := 0
	i := n - sort.SearchInts(flowers, target)
	for x := i; x <= n; x++ {
		if x > 0 {
			newFlowers -= int64(max(target-flowers[n-x], 0))
		}
		if newFlowers < 0 {
			break
		}
		l, r := 0, n-x-1
		for l < r {
			mid := (l + r + 1) >> 1
			if int64(flowers[mid]*(mid+1)-s[mid+1]) <= newFlowers {
				l = mid
			} else {
				r = mid - 1
			}
		}
		y := 0
		if r != -1 {
			cost := flowers[l]*(l+1) - s[l+1]
			y = min(flowers[l]+int((newFlowers-int64(cost))/int64(l+1)), target-1)
		}
		ans = max(ans, x*full+y*partial)
	}
	return int64(ans)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
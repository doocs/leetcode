func minDays(bloomDay []int, m int, k int) int {
	mx := slices.Max(bloomDay)
	if l := sort.Search(mx+2, func(days int) bool {
		cnt, cur := 0, 0
		for _, x := range bloomDay {
			if x <= days {
				cur++
				if cur == k {
					cnt++
					cur = 0
				}
			} else {
				cur = 0
			}
		}
		return cnt >= m
	}); l <= mx {
		return l
	}
	return -1

}

func maxCount(banned []int, n int, maxSum int64) (ans int) {
	banned = append(banned, []int{0, n + 1}...)
	sort.Ints(banned)
	ban := []int{}
	for i, x := range banned {
		if i > 0 && x == banned[i-1] {
			continue
		}
		ban = append(ban, x)
	}
	for k := 1; k < len(ban); k++ {
		i, j := ban[k-1], ban[k]
		left, right := 0, j-i-1
		for left < right {
			mid := (left + right + 1) >> 1
			if int64((i+1+i+mid)*mid/2) <= maxSum {
				left = mid
			} else {
				right = mid - 1
			}
		}
		ans += left
		maxSum -= int64((i + 1 + i + left) * left / 2)
		if maxSum <= 0 {
			break
		}
	}
	return
}
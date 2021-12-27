func numFriendRequests(ages []int) int {
	counter := make([]int, 121)
	for _, age := range ages {
		counter[age]++
	}
	ans := 0
	for i := 1; i < 121; i++ {
		n1 := counter[i]
		for j := 1; j < 121; j++ {
			n2 := counter[j]
			if !(j <= i/2+7 || j > i || (j > 100 && i < 100)) {
				ans += n1 * n2
				if i == j {
					ans -= n2
				}
			}
		}
	}
	return ans
}
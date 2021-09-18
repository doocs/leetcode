func numFriendRequests(ages []int) int {
	counter := make([]int, 121)
	for _, age := range ages {
		counter[age]++
	}
	res := 0
	for i := 1; i < 121; i++ {
		n1 := counter[i]
		for j := 1; j < 121; j++ {
			n2 := counter[j]
			if check(i, j) {
				res += (n1 * n2)
				if i == j {
					res -= n2
				}
			}
		}
	}
	return res
}

func check(a, b int) bool {
	return (a/2+7 < b) && (a >= b) && (a >= 100 || b <= 100)
}
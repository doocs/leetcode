func getCollisionTimes(cars [][]int) []float64 {
	n := len(cars)
	ans := make([]float64, n)
	for i := range ans {
		ans[i] = -1.0
	}
	stk := []int{}
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 {
			j := stk[len(stk)-1]
			if cars[i][1] > cars[j][1] {
				t := float64(cars[j][0]-cars[i][0]) / float64(cars[i][1]-cars[j][1])
				if ans[j] < 0 || t <= ans[j] {
					ans[i] = t
					break
				}
			}
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, i)
	}
	return ans
}
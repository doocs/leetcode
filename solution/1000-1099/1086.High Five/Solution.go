func highFive(items [][]int) (ans [][]int) {
	d := make([][]int, 1001)
	for _, item := range items {
		i, x := item[0], item[1]
		d[i] = append(d[i], x)
	}
	for i := 1; i <= 1000; i++ {
		if len(d[i]) > 0 {
			sort.Ints(d[i])
			s := 0
			for j := len(d[i]) - 1; j >= len(d[i])-5; j-- {
				s += d[i][j]
			}
			ans = append(ans, []int{i, s / 5})
		}
	}
	return ans
}
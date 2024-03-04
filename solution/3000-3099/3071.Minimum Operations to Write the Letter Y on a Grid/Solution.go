func minimumOperationsToWriteY(grid [][]int) int {
	n := len(grid)
	cnt1 := [3]int{}
	cnt2 := [3]int{}
	for i, row := range grid {
		for j, x := range row {
			a := i == j && i <= n/2
			b := i+j == n-1 && i <= n/2
			c := j == n/2 && i >= n/2
			if a || b || c {
				cnt1[x]++
			} else {
				cnt2[x]++
			}
		}
	}
	ans := n * n
	for i := 0; i < 3; i++ {
		for j := 0; j < 3; j++ {
			if i != j {
				ans = min(ans, n*n-cnt1[i]-cnt2[j])
			}
		}
	}
	return ans
}
func countOfPairs(n int, x int, y int) []int {
	ans := make([]int, n)
	x, y = x-1, y-1
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			a := j - i
			b := abs(x-i) + abs(y-j) + 1
			c := abs(x-j) + abs(y-i) + 1
			ans[min(a, min(b, c))-1] += 2
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
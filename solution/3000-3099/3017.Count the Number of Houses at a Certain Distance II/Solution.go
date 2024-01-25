func countOfPairs(n int, x int, y int) []int64 {
	if x > y {
		x, y = y, x
	}
	A := make([]int64, n)
	for i := 1; i <= n; i++ {
		A[0] += 2
		A[min(int64(i-1), int64(math.Abs(float64(i-y)))+int64(x))] -= 1
		A[min(int64(n-i), int64(math.Abs(float64(i-x)))+1+int64(n-y))] -= 1
		A[min(int64(math.Abs(float64(i-x))), int64(math.Abs(float64(y-i)))+1)] += 1
		A[min(int64(math.Abs(float64(i-x)))+1, int64(math.Abs(float64(y-i))))] += 1
		r := max(int64(x-i), 0) + max(int64(i-y), 0)
		A[r+int64((y-x+0)/2)] -= 1
		A[r+int64((y-x+1)/2)] -= 1
	}
	for i := 1; i < n; i++ {
		A[i] += A[i-1]
	}

	return A
}

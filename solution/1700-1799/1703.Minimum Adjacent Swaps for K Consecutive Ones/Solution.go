func minMoves(nums []int, k int) int {
	arr := []int{}
	for i, x := range nums {
		if x != 0 {
			arr = append(arr, i)
		}
	}
	s := make([]int, len(arr)+1)
	for i, x := range arr {
		s[i+1] = s[i] + x
	}
	ans := 1 << 60
	x := (k + 1) / 2
	y := k - x
	for i := x - 1; i < len(arr)-y; i++ {
		j := arr[i]
		ls := s[i+1] - s[i+1-x]
		rs := s[i+1+y] - s[i+1]
		a := (j+j-x+1)*x/2 - ls
		b := rs - (j+1+j+y)*y/2
		ans = min(ans, a+b)
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
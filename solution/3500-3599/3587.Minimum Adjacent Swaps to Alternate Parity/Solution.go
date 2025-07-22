func minSwaps(nums []int) int {
	pos := [2][]int{}
	for i, x := range nums {
		pos[x&1] = append(pos[x&1], i)
	}
	if abs(len(pos[0])-len(pos[1])) > 1 {
		return -1
	}
	calc := func(k int) int {
		res := 0
		for i := 0; i < len(nums); i += 2 {
			res += abs(pos[k][i/2] - i)
		}
		return res
	}
	if len(pos[0]) > len(pos[1]) {
		return calc(0)
	}
	if len(pos[0]) < len(pos[1]) {
		return calc(1)
	}
	return min(calc(0), calc(1))
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

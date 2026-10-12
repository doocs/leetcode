func maxProductPair(nums []int, target int) []int {
	pos := map[int]int{}
	mx := -int(^uint(0)>>1) - 1
	ans := []int{-1, -1}
	for i, x := range nums {
		y := target - x
		if j, ok := pos[y]; x != y && ok && x*y > mx {
			mx = x * y
			if x > y {
				ans = []int{i, j}
			} else {
				ans = []int{j, i}
			}
		}
		pos[x] = i
	}
	return ans
}

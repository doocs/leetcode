func twoSum(nums []int, target int) []int {
	m := map[int]int{}
	for i := 0; ; i++ {
		x := nums[i]
		y := target - x
		if j, ok := m[y]; ok {
			return []int{j, i}
		}
		m[x] = i
	}
}
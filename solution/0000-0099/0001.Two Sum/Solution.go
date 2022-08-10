func twoSum(nums []int, target int) []int {
	m := map[int]int{}
	for i, v := range nums {
		x := target - v
		if j, ok := m[x]; ok {
			return []int{j, i}
		}
		m[v] = i
	}
	return nil
}
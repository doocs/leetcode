func twoSum(nums []int, target int) []int {
	numMap := make(map[int]int)
	for i, num := range nums {
		other := target - num
		if _, ok := numMap[other]; ok {
			return []int{numMap[other], i}
		}
		numMap[num] = i
	}
	return nil
}
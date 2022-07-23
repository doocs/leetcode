type sortInt []int

func (s sortInt) Len() int {
	return len(s)
}

func (s sortInt) Less(i, j int) bool{
	return s[i] > s[j]
}

func (s sortInt) Swap(i, j int) {
	s[i], s[j] = s[j], s[i]
}

func nextPermutation(nums []int)  {
	newNums := sortInt(nums)
	if sort.IsSorted(newNums) {
		n := sort.Reverse(newNums)
		sort.Sort(n)
		return
	}
	for i := len(nums) -2 ; i >= 0 ; i-- {
		for j := len(nums) - 1 ; j > i ; j-- {
			if nums[i] < nums[j] {
				nums[i],nums[j] = nums[j], nums[i]
				sort.Sort(sort.Reverse(newNums[i+1:]))
				return
			}
		}
	}
}

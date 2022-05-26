type NumArray struct {
	s []int
}

func Constructor(nums []int) NumArray {
	n := len(nums)
	s := make([]int, n+1)
	for i, v := range nums {
		s[i+1] = s[i] + v
	}
	return NumArray{s}
}

func (this *NumArray) SumRange(left int, right int) int {
	return this.s[right+1] - this.s[left]
}

/**
 * Your NumArray object will be instantiated and called as such:
 * obj := Constructor(nums);
 * param_1 := obj.SumRange(left,right);
 */
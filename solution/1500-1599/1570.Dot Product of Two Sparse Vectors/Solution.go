type SparseVector struct {
	d map[int]int
}

func Constructor(nums []int) SparseVector {
	d := map[int]int{}
	for i, x := range nums {
		if x != 0 {
			d[i] = x
		}
	}
	return SparseVector{d}
}

// Return the dotProduct of two sparse vectors
func (this *SparseVector) dotProduct(vec SparseVector) (ans int) {
	a, b := this.d, vec.d
	if len(a) > len(b) {
		a, b = b, a
	}
	for i, x := range a {
		if y, has := b[i]; has {
			ans += x * y
		}
	}
	return
}

/**
 * Your SparseVector object will be instantiated and called as such:
 * v1 := Constructor(nums1);
 * v2 := Constructor(nums2);
 * ans := v1.dotProduct(v2);
 */
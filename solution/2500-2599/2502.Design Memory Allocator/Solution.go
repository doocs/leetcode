type Allocator struct {
	m []int
}

func Constructor(n int) Allocator {
	return Allocator{m: make([]int, n)}
}

func (this *Allocator) Allocate(size int, mID int) int {
	cnt := 0
	for i := 0; i < len(this.m); i++ {
		if this.m[i] > 0 {
			cnt = 0
		} else if cnt++; cnt == size {
			for j := i - size + 1; j <= i; j++ {
				this.m[j] = mID
			}
			return i - size + 1
		}
	}
	return -1
}

func (this *Allocator) FreeMemory(mID int) int {
	ans := 0
	for i := 0; i < len(this.m); i++ {
		if this.m[i] == mID {
			this.m[i] = 0
			ans++
		}
	}
	return ans
}

/**
 * Your Allocator object will be instantiated and called as such:
 * obj := Constructor(n);
 * param_1 := obj.Allocate(size,mID);
 * param_2 := obj.FreeMemory(mID);
 */

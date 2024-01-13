type Allocator struct {
	m []int
}

func Constructor(n int) Allocator {
	return Allocator{make([]int, n)}
}

func (this *Allocator) Allocate(size int, mID int) int {
	cnt := 0
	for i, v := range this.m {
		if v > 0 {
			cnt = 0
		} else {
			cnt++
			if cnt == size {
				for j := i - size + 1; j <= i; j++ {
					this.m[j] = mID
				}
				return i - size + 1
			}
		}
	}
	return -1
}

func (this *Allocator) Free(mID int) (ans int) {
	for i, v := range this.m {
		if v == mID {
			this.m[i] = 0
			ans++
		}
	}
	return
}

/**
 * Your Allocator object will be instantiated and called as such:
 * obj := Constructor(n);
 * param_1 := obj.Allocate(size,mID);
 * param_2 := obj.Free(mID);
 */
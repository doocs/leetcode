type MKAverage struct {
	lo, mid, hi  *redblacktree.Tree
	q            []int
	m, k, s      int
	size1, size3 int
}

func Constructor(m int, k int) MKAverage {
	lo := redblacktree.NewWithIntComparator()
	mid := redblacktree.NewWithIntComparator()
	hi := redblacktree.NewWithIntComparator()
	return MKAverage{lo, mid, hi, []int{}, m, k, 0, 0, 0}
}

func (this *MKAverage) AddElement(num int) {
	merge := func(rbt *redblacktree.Tree, key, value int) {
		if v, ok := rbt.Get(key); ok {
			nxt := v.(int) + value
			if nxt == 0 {
				rbt.Remove(key)
			} else {
				rbt.Put(key, nxt)
			}
		} else {
			rbt.Put(key, value)
		}
	}

	if this.lo.Empty() || num <= this.lo.Right().Key.(int) {
		merge(this.lo, num, 1)
		this.size1++
	} else if this.hi.Empty() || num >= this.hi.Left().Key.(int) {
		merge(this.hi, num, 1)
		this.size3++
	} else {
		merge(this.mid, num, 1)
		this.s += num
	}
	this.q = append(this.q, num)
	if len(this.q) > this.m {
		x := this.q[0]
		this.q = this.q[1:]
		if _, ok := this.lo.Get(x); ok {
			merge(this.lo, x, -1)
			this.size1--
		} else if _, ok := this.hi.Get(x); ok {
			merge(this.hi, x, -1)
			this.size3--
		} else {
			merge(this.mid, x, -1)
			this.s -= x
		}
	}
	for ; this.size1 > this.k; this.size1-- {
		x := this.lo.Right().Key.(int)
		merge(this.lo, x, -1)
		merge(this.mid, x, 1)
		this.s += x
	}
	for ; this.size3 > this.k; this.size3-- {
		x := this.hi.Left().Key.(int)
		merge(this.hi, x, -1)
		merge(this.mid, x, 1)
		this.s += x
	}
	for ; this.size1 < this.k && !this.mid.Empty(); this.size1++ {
		x := this.mid.Left().Key.(int)
		merge(this.mid, x, -1)
		this.s -= x
		merge(this.lo, x, 1)
	}
	for ; this.size3 < this.k && !this.mid.Empty(); this.size3++ {
		x := this.mid.Right().Key.(int)
		merge(this.mid, x, -1)
		this.s -= x
		merge(this.hi, x, 1)
	}
}

func (this *MKAverage) CalculateMKAverage() int {
	if len(this.q) < this.m {
		return -1
	}
	return this.s / (this.m - 2*this.k)
}

/**
 * Your MKAverage object will be instantiated and called as such:
 * obj := Constructor(m, k);
 * obj.AddElement(num);
 * param_2 := obj.CalculateMKAverage();
 */
type MKAverage struct {
	m, k int
	s    int
	q    []int
	c    []int
}

func Constructor(m int, k int) MKAverage {
	return MKAverage{m: m, k: k, c: make([]int, 100001)}
}

func (this *MKAverage) AddElement(num int) {
	this.q = append(this.q, num)
	if len(this.q) == this.m {
		for _, x := range this.q {
			this.update(x, 1)
		}
		for i := this.k; i < this.m-this.k; i++ {
			this.s += this.kth(i)
		}
	} else if len(this.q) > this.m {
		i := this.rank(num)
		if i < this.k {
			this.s += this.kth(this.k - 1)
		} else if i <= this.m-this.k {
			this.s += num
		} else {
			this.s += this.kth(this.m - this.k)
		}
		this.update(num, 1)

		x := this.q[0]
		this.q = this.q[1:]
		i = this.rank(x)
		if i < this.k {
			this.s -= this.kth(this.k)
		} else if i <= this.m-this.k {
			this.s -= x
		} else {
			this.s -= this.kth(this.m - this.k)
		}
		this.update(x, -1)
	}
}

func (this *MKAverage) CalculateMKAverage() int {
	if len(this.q) < this.m {
		return -1
	}
	return this.s / (this.m - this.k*2)
}

func (this *MKAverage) update(x, d int) {
	for ; x < len(this.c); x += x & -x {
		this.c[x] += d
	}
}

func (this *MKAverage) query(x int) (ans int) {
	for ; x > 0; x -= x & -x {
		ans += this.c[x]
	}
	return
}

func (this *MKAverage) rank(x int) int {
	return this.query(x - 1)
}

func (this *MKAverage) kth(k int) int {
	need, idx := k+1, 0
	for p := 1 << 16; p > 0; p >>= 1 {
		nxt := idx + p
		if nxt < len(this.c) && this.c[nxt] < need {
			need -= this.c[nxt]
			idx = nxt
		}
	}
	return idx + 1
}

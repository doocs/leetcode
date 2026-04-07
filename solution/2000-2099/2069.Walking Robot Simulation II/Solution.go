type Robot struct {
	mx, my, p, cur int
	moved          bool
}

func Constructor(width int, height int) Robot {
	mx := width - 1
	my := height - 1
	return Robot{
		mx:    mx,
		my:    my,
		p:     2*mx + 2*my,
		cur:   0,
		moved: false,
	}
}

func (this *Robot) Step(num int) {
	this.moved = true
	this.cur = (this.cur + num) % this.p
}

func (this *Robot) GetPos() []int {
	d := this.cur
	mx, my := this.mx, this.my

	if 0 <= d && d <= mx {
		return []int{d, 0}
	}
	if mx < d && d <= mx+my {
		return []int{mx, d - mx}
	}
	if mx+my < d && d <= 2*mx+my {
		return []int{mx - (d - (mx + my)), my}
	}
	return []int{0, my - (d - (2*mx + my))}
}

func (this *Robot) GetDir() string {
	d := this.cur
	mx, my := this.mx, this.my

	if !this.moved {
		return "East"
	}
	if 1 <= d && d <= mx {
		return "East"
	} else if mx < d && d <= mx+my {
		return "North"
	} else if mx+my < d && d <= 2*mx+my {
		return "West"
	}
	return "South"
}

/**
 * Your Robot object will be instantiated and called as such:
 * obj := Constructor(width, height);
 * obj.Step(num);
 * param_2 := obj.GetPos();
 * param_3 := obj.GetDir();
 */

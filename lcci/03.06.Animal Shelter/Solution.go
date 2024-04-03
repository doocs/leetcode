type AnimalShelf struct {
	q [2][]int
}

func Constructor() AnimalShelf {
	return AnimalShelf{}
}

func (this *AnimalShelf) Enqueue(animal []int) {
	this.q[animal[1]] = append(this.q[animal[1]], animal[0])
}

func (this *AnimalShelf) DequeueAny() []int {
	if len(this.q[0]) == 0 || (len(this.q[1]) > 0 && this.q[0][0] > this.q[1][0]) {
		return this.DequeueDog()
	}
	return this.DequeueCat()
}

func (this *AnimalShelf) DequeueDog() []int {
	if len(this.q[1]) == 0 {
		return []int{-1, -1}
	}
	dog := this.q[1][0]
	this.q[1] = this.q[1][1:]
	return []int{dog, 1}
}

func (this *AnimalShelf) DequeueCat() []int {
	if len(this.q[0]) == 0 {
		return []int{-1, -1}
	}
	cat := this.q[0][0]
	this.q[0] = this.q[0][1:]
	return []int{cat, 0}
}

/**
 * Your AnimalShelf object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Enqueue(animal);
 * param_2 := obj.DequeueAny();
 * param_3 := obj.DequeueDog();
 * param_4 := obj.DequeueCat();
 */
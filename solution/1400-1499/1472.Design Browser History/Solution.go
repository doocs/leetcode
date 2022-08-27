type BrowserHistory struct {
	stk1 []string
	stk2 []string
}

func Constructor(homepage string) BrowserHistory {
	t := BrowserHistory{[]string{}, []string{}}
	t.Visit(homepage)
	return t
}

func (this *BrowserHistory) Visit(url string) {
	this.stk1 = append(this.stk1, url)
	this.stk2 = []string{}
}

func (this *BrowserHistory) Back(steps int) string {
	for i := 0; i < steps && len(this.stk1) > 1; i++ {
		this.stk2 = append(this.stk2, this.stk1[len(this.stk1)-1])
		this.stk1 = this.stk1[:len(this.stk1)-1]
	}
	return this.stk1[len(this.stk1)-1]
}

func (this *BrowserHistory) Forward(steps int) string {
	for i := 0; i < steps && len(this.stk2) > 0; i++ {
		this.stk1 = append(this.stk1, this.stk2[len(this.stk2)-1])
		this.stk2 = this.stk2[:len(this.stk2)-1]
	}
	return this.stk1[len(this.stk1)-1]
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * obj := Constructor(homepage);
 * obj.Visit(url);
 * param_2 := obj.Back(steps);
 * param_3 := obj.Forward(steps);
 */
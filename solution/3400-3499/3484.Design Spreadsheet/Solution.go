type Spreadsheet struct {
    d map[string]int
}

func Constructor(rows int) Spreadsheet {
    return Spreadsheet{d: make(map[string]int)}
}

func (this *Spreadsheet) SetCell(cell string, value int) {
    this.d[cell] = value
}

func (this *Spreadsheet) ResetCell(cell string) {
    delete(this.d, cell)
}

func (this *Spreadsheet) GetValue(formula string) int {
    ans := 0
    cells := strings.Split(formula[1:], "+")
    for _, cell := range cells {
        if val, err := strconv.Atoi(cell); err == nil {
            ans += val
        } else {
            ans += this.d[cell]
        }
    }
    return ans
}


/**
 * Your Spreadsheet object will be instantiated and called as such:
 * obj := Constructor(rows);
 * obj.SetCell(cell,value);
 * obj.ResetCell(cell);
 * param_3 := obj.GetValue(formula);
 */

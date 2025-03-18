---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3484.Design%20Spreadsheet/README_EN.md
tags:
    - Design
    - Array
    - Hash Table
    - String
    - Matrix
---

<!-- problem:start -->

# [3484. Design Spreadsheet](https://leetcode.com/problems/design-spreadsheet)

[中文文档](/solution/3400-3499/3484.Design%20Spreadsheet/README.md)

## Description

<!-- description:start -->

<p>A spreadsheet is a grid with 26 columns (labeled from <code>&#39;A&#39;</code> to <code>&#39;Z&#39;</code>) and a given number of <code>rows</code>. Each cell in the spreadsheet can hold an integer value between 0 and 10<sup>5</sup>.</p>

<p>Implement the <code>Spreadsheet</code> class:</p>

<ul>
	<li><code>Spreadsheet(int rows)</code> Initializes a spreadsheet with 26 columns (labeled <code>&#39;A&#39;</code> to <code>&#39;Z&#39;</code>) and the specified number of rows. All cells are initially set to 0.</li>
	<li><code>void setCell(String cell, int value)</code> Sets the value of the specified <code>cell</code>. The cell reference is provided in the format <code>&quot;AX&quot;</code> (e.g., <code>&quot;A1&quot;</code>, <code>&quot;B10&quot;</code>), where the letter represents the column (from <code>&#39;A&#39;</code> to <code>&#39;Z&#39;</code>) and the number represents a <strong>1-indexed</strong> row.</li>
	<li><code>void resetCell(String cell)</code> Resets the specified cell to 0.</li>
	<li><code>int getValue(String formula)</code> Evaluates a formula of the form <code>&quot;=X+Y&quot;</code>, where <code>X</code> and <code>Y</code> are <strong>either</strong> cell references or non-negative integers, and returns the computed sum.</li>
</ul>

<p><strong>Note:</strong> If <code>getValue</code> references a cell that has not been explicitly set using <code>setCell</code>, its value is considered 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong><br />
<span class="example-io">[&quot;Spreadsheet&quot;, &quot;getValue&quot;, &quot;setCell&quot;, &quot;getValue&quot;, &quot;setCell&quot;, &quot;getValue&quot;, &quot;resetCell&quot;, &quot;getValue&quot;]<br />
[[3], [&quot;=5+7&quot;], [&quot;A1&quot;, 10], [&quot;=A1+6&quot;], [&quot;B2&quot;, 15], [&quot;=A1+B2&quot;], [&quot;A1&quot;], [&quot;=A1+B2&quot;]]</span></p>

<p><strong>Output:</strong><br />
<span class="example-io">[null, 12, null, 16, null, 25, null, 15] </span></p>

<p><strong>Explanation</strong></p>
Spreadsheet spreadsheet = new Spreadsheet(3); // Initializes a spreadsheet with 3 rows and 26 columns<br data-end="321" data-start="318" />
spreadsheet.getValue(&quot;=5+7&quot;); // returns 12 (5+7)<br data-end="373" data-start="370" />
spreadsheet.setCell(&quot;A1&quot;, 10); // sets A1 to 10<br data-end="423" data-start="420" />
spreadsheet.getValue(&quot;=A1+6&quot;); // returns 16 (10+6)<br data-end="477" data-start="474" />
spreadsheet.setCell(&quot;B2&quot;, 15); // sets B2 to 15<br data-end="527" data-start="524" />
spreadsheet.getValue(&quot;=A1+B2&quot;); // returns 25 (10+15)<br data-end="583" data-start="580" />
spreadsheet.resetCell(&quot;A1&quot;); // resets A1 to 0<br data-end="634" data-start="631" />
spreadsheet.getValue(&quot;=A1+B2&quot;); // returns 15 (0+15)</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= rows &lt;= 10<sup>3</sup></code></li>
	<li><code>0 &lt;= value &lt;= 10<sup>5</sup></code></li>
	<li>The formula is always in the format <code>&quot;=X+Y&quot;</code>, where <code>X</code> and <code>Y</code> are either valid cell references or <strong>non-negative</strong> integers with values less than or equal to <code>10<sup>5</sup></code>.</li>
	<li>Each cell reference consists of a capital letter from <code>&#39;A&#39;</code> to <code>&#39;Z&#39;</code> followed by a row number between <code>1</code> and <code>rows</code>.</li>
	<li>At most <code>10<sup>4</sup></code> calls will be made in <strong>total</strong> to <code>setCell</code>, <code>resetCell</code>, and <code>getValue</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We use a hash table $\textit{d}$ to record the values of all cells, where the key is the cell reference and the value is the cell's value.

When calling the `setCell` method, we store the cell reference and value in the hash table.

When calling the `resetCell` method, we remove the cell reference from the hash table.

When calling the `getValue` method, we split the formula into two cell references, calculate their values, and then return their sum.

The time complexity is $O(L)$, and the space complexity is $O(L)$. Where $L$ is the length of the formula.

<!-- tabs:start -->

#### Python3

```python
class Spreadsheet:

    def __init__(self, rows: int):
        self.d = {}

    def setCell(self, cell: str, value: int) -> None:
        self.d[cell] = value

    def resetCell(self, cell: str) -> None:
        self.d.pop(cell, None)

    def getValue(self, formula: str) -> int:
        ans = 0
        for cell in formula[1:].split("+"):
            ans += int(cell) if cell[0].isdigit() else self.d.get(cell, 0)
        return ans


# Your Spreadsheet object will be instantiated and called as such:
# obj = Spreadsheet(rows)
# obj.setCell(cell,value)
# obj.resetCell(cell)
# param_3 = obj.getValue(formula)
```

#### Java

```java
class Spreadsheet {
    private Map<String, Integer> d = new HashMap<>();

    public Spreadsheet(int rows) {
    }

    public void setCell(String cell, int value) {
        d.put(cell, value);
    }

    public void resetCell(String cell) {
        d.remove(cell);
    }

    public int getValue(String formula) {
        int ans = 0;
        for (String cell : formula.substring(1).split("\\+")) {
            ans += Character.isDigit(cell.charAt(0)) ? Integer.parseInt(cell)
                                                     : d.getOrDefault(cell, 0);
        }
        return ans;
    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */
```

#### C++

```cpp
class Spreadsheet {
private:
    unordered_map<string, int> d;

public:
    Spreadsheet(int rows) {}

    void setCell(string cell, int value) {
        d[cell] = value;
    }

    void resetCell(string cell) {
        d.erase(cell);
    }

    int getValue(string formula) {
        int ans = 0;
        stringstream ss(formula.substr(1));
        string cell;
        while (getline(ss, cell, '+')) {
            if (isdigit(cell[0])) {
                ans += stoi(cell);
            } else {
                ans += d.count(cell) ? d[cell] : 0;
            }
        }
        return ans;
    }
};

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet* obj = new Spreadsheet(rows);
 * obj->setCell(cell,value);
 * obj->resetCell(cell);
 * int param_3 = obj->getValue(formula);
 */
```

#### Go

```go
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
```

#### TypeScript

```ts
class Spreadsheet {
    private d: Map<string, number>;

    constructor(rows: number) {
        this.d = new Map<string, number>();
    }

    setCell(cell: string, value: number): void {
        this.d.set(cell, value);
    }

    resetCell(cell: string): void {
        this.d.delete(cell);
    }

    getValue(formula: string): number {
        let ans = 0;
        const cells = formula.slice(1).split('+');
        for (const cell of cells) {
            ans += isNaN(Number(cell)) ? this.d.get(cell) || 0 : Number(cell);
        }
        return ans;
    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * var obj = new Spreadsheet(rows)
 * obj.setCell(cell,value)
 * obj.resetCell(cell)
 * var param_3 = obj.getValue(formula)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

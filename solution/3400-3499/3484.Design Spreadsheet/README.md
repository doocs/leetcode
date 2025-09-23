---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3484.Design%20Spreadsheet/README.md
rating: 1523
source: 第 152 场双周赛 Q2
tags:
    - 设计
    - 数组
    - 哈希表
    - 字符串
    - 矩阵
---

<!-- problem:start -->

# [3484. 设计电子表格](https://leetcode.cn/problems/design-spreadsheet)

[English Version](/solution/3400-3499/3484.Design%20Spreadsheet/README_EN.md)

## 题目描述

<!-- description:start -->

<p>电子表格是一个网格，它有 26 列（从 <code>'A'</code> 到 <code>'Z'</code>）和指定数量的 <code>rows</code>。每个单元格可以存储一个 0 到 10<sup>5</sup>&nbsp;之间的整数值。</p>

<p>请你实现一个&nbsp;<code>Spreadsheet</code> 类：</p>

<ul>
	<li><code>Spreadsheet(int rows)</code> 初始化一个具有 26 列（从 <code>'A'</code> 到 <code>'Z'</code>）和指定行数的电子表格。所有单元格最初的值都为 0 。</li>
	<li><code>void setCell(String cell, int value)</code> 设置指定单元格的值。单元格引用以 <code>"AX"</code> 的格式提供（例如，<code>"A1"</code>，<code>"B10"</code>），其中字母表示列（从 <code>'A'</code> 到 <code>'Z'</code>），数字表示从<strong>&nbsp;</strong><strong>1</strong>&nbsp;开始的行号。</li>
	<li><code>void resetCell(String cell)</code> 重置指定单元格的值为 0 。</li>
	<li><code>int getValue(String formula)</code> 计算一个公式的值，格式为 <code>"=X+Y"</code>，其中 <code>X</code> 和 <code>Y</code>&nbsp;<strong>要么</strong> 是单元格引用，要么非负整数，返回计算的和。</li>
</ul>

<p><strong>注意：</strong> 如果 <code>getValue</code> 引用一个未通过 <code>setCell</code> 明确设置的单元格，则该单元格的值默认为 0 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><br />
<span class="example-io">["Spreadsheet", "getValue", "setCell", "getValue", "setCell", "getValue", "resetCell", "getValue"]<br />
[[3], ["=5+7"], ["A1", 10], ["=A1+6"], ["B2", 15], ["=A1+B2"], ["A1"], ["=A1+B2"]]</span></p>

<p><strong>输出：</strong><br />
<span class="example-io">[null, 12, null, 16, null, 25, null, 15] </span></p>

<p><strong>解释</strong></p>
Spreadsheet spreadsheet = new Spreadsheet(3); // 初始化一个具有 3 行和 26 列的电子表格<br data-end="321" data-start="318" />
spreadsheet.getValue("=5+7"); // 返回 12 (5+7)<br data-end="373" data-start="370" />
spreadsheet.setCell("A1", 10); // 设置 A1 为 10<br data-end="423" data-start="420" />
spreadsheet.getValue("=A1+6"); // 返回 16 (10+6)<br data-end="477" data-start="474" />
spreadsheet.setCell("B2", 15); // 设置 B2 为 15<br data-end="527" data-start="524" />
spreadsheet.getValue("=A1+B2"); // 返回 25 (10+15)<br data-end="583" data-start="580" />
spreadsheet.resetCell("A1"); // 重置 A1 为 0<br data-end="634" data-start="631" />
spreadsheet.getValue("=A1+B2"); // 返回 15 (0+15)</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= rows &lt;= 10<sup>3</sup></code></li>
	<li><code>0 &lt;= value &lt;= 10<sup>5</sup></code></li>
	<li>公式保证采用 <code>"=X+Y"</code> 格式，其中 <code>X</code> 和 <code>Y</code> 要么是有效的单元格引用，要么是小于等于 <code>10<sup>5</sup></code> 的 <strong>非负</strong> 整数。</li>
	<li>每个单元格引用由一个大写字母 <code>'A'</code> 到 <code>'Z'</code> 和一个介于 <code>1</code> 和 <code>rows</code> 之间的行号组成。</li>
	<li><strong>总共</strong> 最多会对 <code>setCell</code>、<code>resetCell</code> 和 <code>getValue</code> 调用 <code>10<sup>4</sup></code> 次。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们用一个哈希表 $\textit{d}$ 来记录所有单元格的值，其中键为单元格引用，值为单元格的值。

调用 `setCell` 方法时，我们将单元格引用和值存入哈希表中。

调用 `resetCell` 方法时，我们将单元格引用从哈希表中删除。

调用 `getValue` 方法时，我们将公式分割成两个单元格引用，然后计算它们的值，最后返回它们的和。

时间复杂度 $(L)$，空间复杂度 $(L)$。其中 $L$ 为公式的长度。

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

#### Rust

```rust
use std::collections::HashMap;

struct Spreadsheet {
    d: HashMap<String, i32>,
}

impl Spreadsheet {
    fn new(_rows: i32) -> Self {
        Spreadsheet {
            d: HashMap::new(),
        }
    }

    fn set_cell(&mut self, cell: String, value: i32) {
        self.d.insert(cell, value);
    }

    fn reset_cell(&mut self, cell: String) {
        self.d.remove(&cell);
    }

    fn get_value(&self, formula: String) -> i32 {
        let mut ans = 0;
        for cell in formula[1..].split('+') {
            if cell.chars().next().unwrap().is_ascii_digit() {
                ans += cell.parse::<i32>().unwrap();
            } else {
                ans += *self.d.get(cell).unwrap_or(&0);
            }
        }
        ans
    }
}
```

#### C#

```cs
public class Spreadsheet {
    private Dictionary<string, int> d = new Dictionary<string, int>();

    public Spreadsheet(int rows) {
    }

    public void SetCell(string cell, int value) {
        d[cell] = value;
    }

    public void ResetCell(string cell) {
        d.Remove(cell);
    }

    public int GetValue(string formula) {
        int ans = 0;
        foreach (string cell in formula.Substring(1).Split('+')) {
            ans += char.IsDigit(cell[0]) ? int.Parse(cell)
                                         : (d.ContainsKey(cell) ? d[cell] : 0);
        }
        return ans;
    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.SetCell(cell,value);
 * obj.ResetCell(cell);
 * int param_3 = obj.GetValue(formula);
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

# [1476. Subrectangle Queries](https://leetcode.com/problems/subrectangle-queries)

[中文文档](/solution/1400-1499/1476.Subrectangle%20Queries/README.md)

## Description

<p>Implement the class <code>SubrectangleQueries</code>&nbsp;which receives a <code>rows x cols</code> rectangle as a matrix of integers in the constructor and supports two methods:</p>

<p>1.<code>&nbsp;updateSubrectangle(int row1, int col1, int row2, int col2, int newValue)</code></p>

<ul>
	<li>Updates all values with <code>newValue</code> in the subrectangle whose upper left coordinate is <code>(row1,col1)</code> and bottom right coordinate is <code>(row2,col2)</code>.</li>
</ul>

<p>2.<code>&nbsp;getValue(int row, int col)</code></p>

<ul>
	<li>Returns the current value of the coordinate <code>(row,col)</code> from&nbsp;the rectangle.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;SubrectangleQueries&quot;,&quot;getValue&quot;,&quot;updateSubrectangle&quot;,&quot;getValue&quot;,&quot;getValue&quot;,&quot;updateSubrectangle&quot;,&quot;getValue&quot;,&quot;getValue&quot;]
[[[[1,2,1],[4,3,4],[3,2,1],[1,1,1]]],[0,2],[0,0,3,2,5],[0,2],[3,1],[3,0,3,2,10],[3,1],[0,2]]
<strong>Output</strong>
[null,1,null,5,5,null,10,5]
<strong>Explanation</strong>
SubrectangleQueries subrectangleQueries = new SubrectangleQueries([[1,2,1],[4,3,4],[3,2,1],[1,1,1]]);  
// The initial rectangle (4x3) looks like:
// 1 2 1
// 4 3 4
// 3 2 1
// 1 1 1
subrectangleQueries.getValue(0, 2); // return 1
subrectangleQueries.updateSubrectangle(0, 0, 3, 2, 5);
// After this update the rectangle looks like:
// 5 5 5
// 5 5 5
// 5 5 5
// 5 5 5 
subrectangleQueries.getValue(0, 2); // return 5
subrectangleQueries.getValue(3, 1); // return 5
subrectangleQueries.updateSubrectangle(3, 0, 3, 2, 10);
// After this update the rectangle looks like:
// 5   5   5
// 5   5   5
// 5   5   5
// 10  10  10 
subrectangleQueries.getValue(3, 1); // return 10
subrectangleQueries.getValue(0, 2); // return 5
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input</strong>
[&quot;SubrectangleQueries&quot;,&quot;getValue&quot;,&quot;updateSubrectangle&quot;,&quot;getValue&quot;,&quot;getValue&quot;,&quot;updateSubrectangle&quot;,&quot;getValue&quot;]
[[[[1,1,1],[2,2,2],[3,3,3]]],[0,0],[0,0,2,2,100],[0,0],[2,2],[1,1,2,2,20],[2,2]]
<strong>Output</strong>
[null,1,null,100,100,null,20]
<strong>Explanation</strong>
SubrectangleQueries subrectangleQueries = new SubrectangleQueries([[1,1,1],[2,2,2],[3,3,3]]);
subrectangleQueries.getValue(0, 0); // return 1
subrectangleQueries.updateSubrectangle(0, 0, 2, 2, 100);
subrectangleQueries.getValue(0, 0); // return 100
subrectangleQueries.getValue(2, 2); // return 100
subrectangleQueries.updateSubrectangle(1, 1, 2, 2, 20);
subrectangleQueries.getValue(2, 2); // return 20
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>There will be at most <code><font face="monospace">500</font></code>&nbsp;operations considering both methods:&nbsp;<code>updateSubrectangle</code> and <code>getValue</code>.</li>
	<li><code>1 &lt;= rows, cols &lt;= 100</code></li>
	<li><code>rows ==&nbsp;rectangle.length</code></li>
	<li><code>cols == rectangle[i].length</code></li>
	<li><code>0 &lt;= row1 &lt;= row2 &lt; rows</code></li>
	<li><code>0 &lt;= col1 &lt;= col2 &lt; cols</code></li>
	<li><code>1 &lt;= newValue, rectangle[i][j] &lt;= 10^9</code></li>
	<li><code>0 &lt;= row &lt; rows</code></li>
	<li><code>0 &lt;= col &lt; cols</code></li>
</ul>

## Solutions

Use history list to save the updated record.

<!-- tabs:start -->

### **Python3**

```python
class SubrectangleQueries:
    def __init__(self, rectangle: List[List[int]]):
        self.g = rectangle
        self.ops = []

    def updateSubrectangle(
        self, row1: int, col1: int, row2: int, col2: int, newValue: int
    ) -> None:
        self.ops.append((row1, col1, row2, col2, newValue))

    def getValue(self, row: int, col: int) -> int:
        for r1, c1, r2, c2, v in self.ops[::-1]:
            if r1 <= row <= r2 and c1 <= col <= c2:
                return v
        return self.g[row][col]


# Your SubrectangleQueries object will be instantiated and called as such:
# obj = SubrectangleQueries(rectangle)
# obj.updateSubrectangle(row1,col1,row2,col2,newValue)
# param_2 = obj.getValue(row,col)
```

### **Java**

```java
class SubrectangleQueries {
    private int[][] g;
    private LinkedList<int[]> ops = new LinkedList<>();

    public SubrectangleQueries(int[][] rectangle) {
        g = rectangle;
    }

    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        ops.addFirst(new int[] {row1, col1, row2, col2, newValue});
    }

    public int getValue(int row, int col) {
        for (var op : ops) {
            if (op[0] <= row && row <= op[2] && op[1] <= col && col <= op[3]) {
                return op[4];
            }
        }
        return g[row][col];
    }
}

/**
 * Your SubrectangleQueries object will be instantiated and called as such:
 * SubrectangleQueries obj = new SubrectangleQueries(rectangle);
 * obj.updateSubrectangle(row1,col1,row2,col2,newValue);
 * int param_2 = obj.getValue(row,col);
 */
```

### **C++**

```cpp
class SubrectangleQueries {
public:
    vector<vector<int>> g;
    vector<vector<int>> ops;

    SubrectangleQueries(vector<vector<int>>& rectangle) {
        g = rectangle;
    }

    void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        ops.push_back({row1, col1, row2, col2, newValue});
    }

    int getValue(int row, int col) {
        for (int i = ops.size() - 1; ~i; --i) {
            auto op = ops[i];
            if (op[0] <= row && row <= op[2] && op[1] <= col && col <= op[3]) {
                return op[4];
            }
        }
        return g[row][col];
    }
};

/**
 * Your SubrectangleQueries object will be instantiated and called as such:
 * SubrectangleQueries* obj = new SubrectangleQueries(rectangle);
 * obj->updateSubrectangle(row1,col1,row2,col2,newValue);
 * int param_2 = obj->getValue(row,col);
 */
```

### **Go**

```go
type SubrectangleQueries struct {
	g   [][]int
	ops [][]int
}

func Constructor(rectangle [][]int) SubrectangleQueries {
	return SubrectangleQueries{rectangle, [][]int{}}
}

func (this *SubrectangleQueries) UpdateSubrectangle(row1 int, col1 int, row2 int, col2 int, newValue int) {
	this.ops = append(this.ops, []int{row1, col1, row2, col2, newValue})
}

func (this *SubrectangleQueries) GetValue(row int, col int) int {
	for i := len(this.ops) - 1; i >= 0; i-- {
		op := this.ops[i]
		if op[0] <= row && row <= op[2] && op[1] <= col && col <= op[3] {
			return op[4]
		}
	}
	return this.g[row][col]
}

/**
 * Your SubrectangleQueries object will be instantiated and called as such:
 * obj := Constructor(rectangle);
 * obj.UpdateSubrectangle(row1,col1,row2,col2,newValue);
 * param_2 := obj.GetValue(row,col);
 */
```

### **TypeScript**

```ts
class SubrectangleQueries {
    g: number[][];
    ops: number[][];
    constructor(rectangle: number[][]) {
        this.g = rectangle;
        this.ops = [];
    }

    updateSubrectangle(
        row1: number,
        col1: number,
        row2: number,
        col2: number,
        newValue: number,
    ): void {
        this.ops.push([row1, col1, row2, col2, newValue]);
    }

    getValue(row: number, col: number): number {
        for (let i = this.ops.length - 1; ~i; --i) {
            const [r1, c1, r2, c2, v] = this.ops[i];
            if (r1 <= row && row <= r2 && c1 <= col && col <= c2) {
                return v;
            }
        }
        return this.g[row][col];
    }
}

/**
 * Your SubrectangleQueries object will be instantiated and called as such:
 * var obj = new SubrectangleQueries(rectangle)
 * obj.updateSubrectangle(row1,col1,row2,col2,newValue)
 * var param_2 = obj.getValue(row,col)
 */
```

### **...**

```

```

<!-- tabs:end -->

# [119. Pascal's Triangle II](https://leetcode.com/problems/pascals-triangle-ii)

[中文文档](/solution/0100-0199/0119.Pascal%27s%20Triangle%20II/README.md)

## Description

<p>Given an integer <code>rowIndex</code>, return the <code>rowIndex<sup>th</sup></code> (<strong>0-indexed</strong>) row of the <strong>Pascal&#39;s triangle</strong>.</p>

<p>In <strong>Pascal&#39;s triangle</strong>, each number is the sum of the two numbers directly above it as shown:</p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0119.Pascal%27s%20Triangle%20II/images/PascalTriangleAnimated2.gif" style="height:240px; width:260px" />
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> rowIndex = 3
<strong>Output:</strong> [1,3,3,1]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> rowIndex = 0
<strong>Output:</strong> [1]
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> rowIndex = 1
<strong>Output:</strong> [1,1]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= rowIndex &lt;= 33</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you optimize your algorithm to use only <code>O(rowIndex)</code> extra space?</p>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getRow(self, rowIndex: int) -> List[int]:
        def makePascal(prevArr):
            if len(prevArr) == 0:
                return [1]
            elif len(prevArr) == 1:
                return [1, 1]
            else:
                NewArr = [0] * (len(prevArr) + 1)
                NewArr[0], NewArr[-1] = 1, 1
                for i in range(len(prevArr) - 1):
                    NewArr[i + 1] = prevArr[i] + prevArr[i + 1]
                return NewArr

        temp = []
        Pascal = []
        for i in range(rowIndex + 1):
            temp = makePascal(temp)
            Pascal.append(temp)
        return Pascal[rowIndex]
```

### **Java**

```java
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> ret = new LinkedList<>();
        long nk = 1;
        for (int i = 0; i <= rowIndex; i++) {
            ret.add((int) nk);
            nk = nk * (rowIndex - i) / (i + 1);
        }
        return ret;
    }
}
```

### **Go**

```go
func getRow(rowIndex int) []int {
	row := make([]int, rowIndex+1)
	row[0] = 1
	for i := 1; i <= rowIndex; i++ {
		for j := i; j > 0; j-- {
			row[j] += row[j-1]
		}
	}
	return row
}
```

### **...**

```

```

<!-- tabs:end -->

# [118. Pascal's Triangle](https://leetcode.com/problems/pascals-triangle)

[中文文档](/solution/0100-0199/0118.Pascal's%20Triangle/README.md)

## Description

<p>Given a non-negative integer&nbsp;<em>numRows</em>, generate the first <em>numRows</em> of Pascal&#39;s triangle.</p>

<p><img alt="" src="https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif" style="height:240px; width:260px" /><br />

<small>In Pascal&#39;s triangle, each number is the sum of the two numbers directly above it.</small></p>

<p><strong>Example:</strong></p>

<pre>

<strong>Input:</strong> 5

<strong>Output:</strong>

[

     [1],

    [1,1],

   [1,2,1],

  [1,3,3,1],

 [1,4,6,4,1]

]

</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def generate(self, numRows: int) -> List[List[int]]:
        if numRows == 0:
            return []
        res = []
        for i in range(numRows):
            t = [1 if j == 0 or j == i else 0 for j in range(i + 1)]
            for j in range(1, i):
                t[j] = res[i - 1][j - 1] + res[i - 1][j]
            res.append(t)
        return res
```

### **Java**

```java
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) return res;
        for (int i = 0; i < numRows; ++i) {
            // 每一行
            List<Integer> t = new ArrayList<>();
            for (int j = 0; j < i + 1; ++j) {
                boolean firstOrLast = j == 0 || j == i;
                // 设置每一行首尾元素为1，其它元素为0
                t.add(firstOrLast ? 1 : 0);
            }
            for (int j = 1; j < i; ++j) {
                int val = res.get(i - 1).get(j - 1) + res.get(i - 1).get(j);
                t.set(j, val);
            }
            res.add(t);
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->

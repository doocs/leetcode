# [118. 杨辉三角](https://leetcode-cn.com/problems/pascals-triangle)

[English Version](/solution/0100-0199/0118.Pascal%27s%20Triangle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非负整数&nbsp;<em>numRows，</em>生成杨辉三角的前&nbsp;<em>numRows&nbsp;</em>行。</p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0118.Pascal%27s%20Triangle/images/PascalTriangleAnimated2.gif"></p>

<p><small>在杨辉三角中，每个数是它左上方和右上方的数的和。</small></p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> 5
<strong>输出:</strong>
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先设置每一行首尾元素为 1，其它元素为 0。然后根据杨辉三角，设置每一行其它元素即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def generate(self, numRows: int) -> List[List[int]]:
        res = []
        for i in range(numRows):
            t = [1 if j == 0 or j == i else 0 for j in range(i + 1)]
            for j in range(1, i):
                t[j] = res[i - 1][j - 1] + res[i - 1][j]
            res.append(t)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; ++i) {
            List<Integer> t = new ArrayList<>();
            for (int j = 0; j < i + 1; ++j) {
                boolean firstOrLast = j == 0 || j == i;
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

### **TypeScript**

```ts
function generate(numRows: number): number[][] {
    if (numRows == 0) return [];
    let ans = [[1]];
    for (let i = 1; i < numRows; ++i) {
        ans.push(new Array(i + 1).fill(1));
        let half = i >> 1;
        for (let j = 1; j <= half; ++j) {
            let cur = ans[i - 1][j - 1] + ans[i - 1][j];
            ans[i][j] = cur;
            ans[i][i - j] = cur;
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> generate(int numRows) {
        vector<vector<int>> res;
        for (int i = 0; i < numRows; ++i) {
            vector<int> t(i + 1);
            t[0] = 1;
            t[i] = 1;
            for (int j = 1; j < i; ++j) {
                t[j] = res[i - 1][j - 1] + res[i - 1][j];
            }
            res.push_back(t);
        }
        return res;
    }
};
```

### **Go**

```go
func generate(numRows int) [][]int {
	res := make([][]int, numRows)
	for i := 0; i < numRows; i++ {
		t := make([]int, i+1)
		t[0] = 1
		t[i] = 1
		for j := 1; j < i; j++ {
			t[j] = res[i-1][j-1] + res[i-1][j]
		}
		res[i] = t
	}
	return res
}
```

### **JavaScript**

```js
const generate = function (numRows) {
    let arr = [];
    for (let i = 0; i < numRows; i++) {
        let row = [];
        row[0] = 1;
        row[i] = 1;
        for (let j = 1; j < row.length - 1; j++) {
            row[j] = arr[i - 1][j - 1] + arr[i - 1][j];
        }
        arr.push(row);
    }
    return arr;
};
```

### **...**

```

```

<!-- tabs:end -->

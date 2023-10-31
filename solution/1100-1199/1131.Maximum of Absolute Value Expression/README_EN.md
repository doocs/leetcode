# [1131. Maximum of Absolute Value Expression](https://leetcode.com/problems/maximum-of-absolute-value-expression)

[中文文档](/solution/1100-1199/1131.Maximum%20of%20Absolute%20Value%20Expression/README.md)

## Description

<p>Given two arrays of integers with equal lengths, return the maximum value of:</p>

<p><code>|arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|</code></p>

<p>where the maximum is taken over all <code>0 &lt;= i, j &lt; arr1.length</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr1 = [1,2,3,4], arr2 = [-1,4,5,6]
<strong>Output:</strong> 13
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr1 = [1,-2,-5,0,10], arr2 = [0,-2,-1,-7,-4]
<strong>Output:</strong> 20
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= arr1.length == arr2.length &lt;= 40000</code></li>
	<li><code>-10^6 &lt;= arr1[i], arr2[i] &lt;= 10^6</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxAbsValExpr(self, arr1: List[int], arr2: List[int]) -> int:
        dirs = (1, -1, -1, 1, 1)
        ans = -inf
        for a, b in pairwise(dirs):
            mx, mi = -inf, inf
            for i, (x, y) in enumerate(zip(arr1, arr2)):
                mx = max(mx, a * x + b * y + i)
                mi = min(mi, a * x + b * y + i)
                ans = max(ans, mx - mi)
        return ans
```

### **Java**

```java
class Solution {
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int[] dirs = {1, -1, -1, 1, 1};
        final int inf = 1 << 30;
        int ans = -inf;
        int n = arr1.length;
        for (int k = 0; k < 4; ++k) {
            int a = dirs[k], b = dirs[k + 1];
            int mx = -inf, mi = inf;
            for (int i = 0; i < n; ++i) {
                mx = Math.max(mx, a * arr1[i] + b * arr2[i] + i);
                mi = Math.min(mi, a * arr1[i] + b * arr2[i] + i);
                ans = Math.max(ans, mx - mi);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxAbsValExpr(vector<int>& arr1, vector<int>& arr2) {
        int dirs[5] = {1, -1, -1, 1, 1};
        const int inf = 1 << 30;
        int ans = -inf;
        int n = arr1.size();
        for (int k = 0; k < 4; ++k) {
            int a = dirs[k], b = dirs[k + 1];
            int mx = -inf, mi = inf;
            for (int i = 0; i < n; ++i) {
                mx = max(mx, a * arr1[i] + b * arr2[i] + i);
                mi = min(mi, a * arr1[i] + b * arr2[i] + i);
                ans = max(ans, mx - mi);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxAbsValExpr(arr1 []int, arr2 []int) int {
	dirs := [5]int{1, -1, -1, 1, 1}
	const inf = 1 << 30
	ans := -inf
	for k := 0; k < 4; k++ {
		a, b := dirs[k], dirs[k+1]
		mx, mi := -inf, inf
		for i, x := range arr1 {
			y := arr2[i]
			mx = max(mx, a*x+b*y+i)
			mi = min(mi, a*x+b*y+i)
			ans = max(ans, mx-mi)
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function maxAbsValExpr(arr1: number[], arr2: number[]): number {
    const dirs = [1, -1, -1, 1, 1];
    const inf = 1 << 30;
    let ans = -inf;
    for (let k = 0; k < 4; ++k) {
        const [a, b] = [dirs[k], dirs[k + 1]];
        let mx = -inf;
        let mi = inf;
        for (let i = 0; i < arr1.length; ++i) {
            const [x, y] = [arr1[i], arr2[i]];
            mx = Math.max(mx, a * x + b * y + i);
            mi = Math.min(mi, a * x + b * y + i);
            ans = Math.max(ans, mx - mi);
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->

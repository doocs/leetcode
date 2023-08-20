# [2829. Determine the Minimum Sum of a k-avoiding Array](https://leetcode.com/problems/determine-the-minimum-sum-of-a-k-avoiding-array)

[中文文档](/solution/2800-2899/2829.Determine%20the%20Minimum%20Sum%20of%20a%20k-avoiding%20Array/README.md)

## Description

<p>You are given two integers,&nbsp;<code>n</code> and <code>k</code>.</p>

<p>An array of <strong>distinct</strong> positive integers is called a <b>k-avoiding</b> array if there does not exist any pair of distinct elements that sum to <code>k</code>.</p>

<p>Return <em>the <strong>minimum</strong> possible sum of a k-avoiding array of length </em><code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5, k = 4
<strong>Output:</strong> 18
<strong>Explanation:</strong> Consider the k-avoiding array [1,2,4,5,6], which has a sum of 18.
It can be proven that there is no k-avoiding array with a sum less than 18.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, k = 6
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can construct the array [1,2], which has a sum of 3.
It can be proven that there is no k-avoiding array with a sum less than 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, k &lt;= 50</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumSum(self, n: int, k: int) -> int:
        s, i = 0, 1
        vis = set()
        for _ in range(n):
            while i in vis:
                i += 1
            vis.add(i)
            vis.add(k - i)
            s += i
        return s
```

### **Java**

```java
class Solution {
    public int minimumSum(int n, int k) {
        int s = 0, i = 1;
        boolean[] vis = new boolean[k + n * n + 1];
        while (n-- > 0) {
            while (vis[i]) {
                ++i;
            }
            vis[i] = true;
            if (k >= i) {
                vis[k - i] = true;
            }
            s += i;
        }
        return s;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumSum(int n, int k) {
        int s = 0, i = 1;
        bool vis[k + n * n + 1];
        memset(vis, false, sizeof(vis));
        while (n--) {
            while (vis[i]) {
                ++i;
            }
            vis[i] = true;
            if (k >= i) {
                vis[k - i] = true;
            }
            s += i;
        }
        return s;
    }
};
```

### **Go**

```go
func minimumSum(n int, k int) int {
	s, i := 0, 1
	vis := make([]bool, k+n*n+1)
	for ; n > 0; n-- {
		for vis[i] {
			i++
		}
		vis[i] = true
		if k >= i {
			vis[k-i] = true
		}
		s += i
	}
	return s
}
```

### **TypeScript**

```ts
function minimumSum(n: number, k: number): number {
    let s = 0;
    let i = 1;
    const vis: boolean[] = Array(n * n + k + 1);
    while (n--) {
        while (vis[i]) {
            ++i;
        }
        vis[i] = true;
        if (k >= i) {
            vis[k - i] = true;
        }
        s += i;
    }
    return s;
}
```

### **...**

```

```

<!-- tabs:end -->

# [667. Beautiful Arrangement II](https://leetcode.com/problems/beautiful-arrangement-ii)

[中文文档](/solution/0600-0699/0667.Beautiful%20Arrangement%20II/README.md)

## Description

<p>Given two integers <code>n</code> and <code>k</code>, construct a list <code>answer</code> that contains <code>n</code> different positive integers ranging from <code>1</code> to <code>n</code> and obeys the following requirement:</p>

<ul>
	<li>Suppose this list is <code>answer =&nbsp;[a<sub>1</sub>, a<sub>2</sub>, a<sub>3</sub>, ... , a<sub>n</sub>]</code>, then the list <code>[|a<sub>1</sub> - a<sub>2</sub>|, |a<sub>2</sub> - a<sub>3</sub>|, |a<sub>3</sub> - a<sub>4</sub>|, ... , |a<sub>n-1</sub> - a<sub>n</sub>|]</code> has exactly <code>k</code> distinct integers.</li>
</ul>

<p>Return <em>the list</em> <code>answer</code>. If there multiple valid answers, return <strong>any of them</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, k = 1
<strong>Output:</strong> [1,2,3]
Explanation: The [1,2,3] has three different positive integers ranging from 1 to 3, and the [1,1] has exactly 1 distinct integer: 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, k = 2
<strong>Output:</strong> [1,3,2]
Explanation: The [1,3,2] has three different positive integers ranging from 1 to 3, and the [2,1] has exactly 2 distinct integers: 1 and 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt; n &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def constructArray(self, n: int, k: int) -> List[int]:
        l, r = 1, n
        ans = []
        for i in range(k):
            if i % 2 == 0:
                ans.append(l)
                l += 1
            else:
                ans.append(r)
                r -= 1
        for i in range(k, n):
            if k % 2 == 0:
                ans.append(r)
                r -= 1
            else:
                ans.append(l)
                l += 1
        return ans
```

### **Java**

```java
class Solution {
    public int[] constructArray(int n, int k) {
        int l = 1, r = n;
        int[] ans = new int[n];
        for (int i = 0; i < k; ++i) {
            ans[i] = i % 2 == 0 ? l++ : r--;
        }
        for (int i = k; i < n; ++i) {
            ans[i] = k % 2 == 0 ? r-- : l++;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> constructArray(int n, int k) {
        int l = 1, r = n;
        vector<int> ans(n);
        for (int i = 0; i < k; ++i) {
            ans[i] = i % 2 == 0 ? l++ : r--;
        }
        for (int i = k; i < n; ++i) {
            ans[i] = k % 2 == 0 ? r-- : l++;
        }
        return ans;
    }
};
```

### **Go**

```go
func constructArray(n int, k int) []int {
	l, r := 1, n
	ans := make([]int, n)
	for i := 0; i < k; i++ {
		if i%2 == 0 {
			ans[i] = l
			l++
		} else {
			ans[i] = r
			r--
		}
	}
	for i := k; i < n; i++ {
		if k%2 == 0 {
			ans[i] = r
			r--
		} else {
			ans[i] = l
			l++
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function constructArray(n: number, k: number): number[] {
    let l = 1;
    let r = n;
    const ans = new Array(n);
    for (let i = 0; i < k; ++i) {
        ans[i] = i % 2 == 0 ? l++ : r--;
    }
    for (let i = k; i < n; ++i) {
        ans[i] = k % 2 == 0 ? r-- : l++;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->

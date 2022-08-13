# [1399. Count Largest Group](https://leetcode.com/problems/count-largest-group)

[中文文档](/solution/1300-1399/1399.Count%20Largest%20Group/README.md)

## Description

<p>You are given an integer <code>n</code>.</p>

<p>Each number from <code>1</code> to <code>n</code> is grouped according to the sum of its digits.</p>

<p>Return <em>the number of groups that have the largest size</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 13
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 9 groups in total, they are grouped according sum of its digits of numbers from 1 to 13:
[1,10], [2,11], [3,12], [4,13], [5], [6], [7], [8], [9].
There are 4 groups with largest size.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 2 groups [1], [2] of size 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countLargestGroup(self, n: int) -> int:
        cnt = Counter()
        ans, mx = 0, 0
        for i in range(1, n + 1):
            t = sum(int(v) for v in str(i))
            cnt[t] += 1
            if mx < cnt[t]:
                mx = cnt[t]
                ans = 1
            elif mx == cnt[t]:
                ans += 1
        return ans
```

### **Java**

```java
class Solution {
    public int countLargestGroup(int n) {
        int[] cnt = new int[40];
        int mx = 0, ans = 0;
        for (int i = 1; i <= n; ++i) {
            int t = 0;
            int j = i;
            while (j != 0) {
                t += j % 10;
                j /= 10;
            }
            ++cnt[t];
            if (mx < cnt[t]) {
                mx = cnt[t];
                ans = 1;
            } else if (mx == cnt[t]) {
                ++ans;
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
    int countLargestGroup(int n) {
        vector<int> cnt(40);
        int mx = 0, ans = 0;
        for (int i = 1; i <= n; ++i) {
            int t = 0;
            int j = i;
            while (j) {
                t += j % 10;
                j /= 10;
            }
            ++cnt[t];
            if (mx < cnt[t]) {
                mx = cnt[t];
                ans = 1;
            } else if (mx == cnt[t])
                ++ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func countLargestGroup(n int) int {
	cnt := make([]int, 40)
	mx, ans := 0, 0
	for i := 1; i <= n; i++ {
		t := 0
		j := i
		for j != 0 {
			t += j % 10
			j /= 10
		}
		cnt[t]++
		if mx < cnt[t] {
			mx = cnt[t]
			ans = 1
		} else if mx == cnt[t] {
			ans++
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->

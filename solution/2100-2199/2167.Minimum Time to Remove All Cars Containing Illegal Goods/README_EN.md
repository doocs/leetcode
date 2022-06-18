# [2167. Minimum Time to Remove All Cars Containing Illegal Goods](https://leetcode.com/problems/minimum-time-to-remove-all-cars-containing-illegal-goods)

[中文文档](/solution/2100-2199/2167.Minimum%20Time%20to%20Remove%20All%20Cars%20Containing%20Illegal%20Goods/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> binary string <code>s</code> which represents a sequence of train cars. <code>s[i] = &#39;0&#39;</code> denotes that the <code>i<sup>th</sup></code> car does <strong>not</strong> contain illegal goods and <code>s[i] = &#39;1&#39;</code> denotes that the <code>i<sup>th</sup></code> car does contain illegal goods.</p>

<p>As the train conductor, you would like to get rid of all the cars containing illegal goods. You can do any of the following three operations <strong>any</strong> number of times:</p>

<ol>
	<li>Remove a train car from the <strong>left</strong> end (i.e., remove <code>s[0]</code>) which takes 1 unit of time.</li>
	<li>Remove a train car from the <strong>right</strong> end (i.e., remove <code>s[s.length - 1]</code>) which takes 1 unit of time.</li>
	<li>Remove a train car from <strong>anywhere</strong> in the sequence which takes 2 units of time.</li>
</ol>

<p>Return <em>the <strong>minimum</strong> time to remove all the cars containing illegal goods</em>.</p>

<p>Note that an empty sequence of cars is considered to have no cars containing illegal goods.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;<strong><u>11</u></strong>00<strong><u>1</u></strong>0<strong><u>1</u></strong>&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> 
One way to remove all the cars containing illegal goods from the sequence is to
- remove a car from the left end 2 times. Time taken is 2 * 1 = 2.
- remove a car from the right end. Time taken is 1.
- remove the car containing illegal goods found in the middle. Time taken is 2.
This obtains a total time of 2 + 1 + 2 = 5. 

An alternative way is to
- remove a car from the left end 2 times. Time taken is 2 * 1 = 2.
- remove a car from the right end 3 times. Time taken is 3 * 1 = 3.
This also obtains a total time of 2 + 3 = 5.

5 is the minimum time taken to remove all the cars containing illegal goods. 
There are no other ways to remove them with less time.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;00<strong><u>1</u></strong>0&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong>
One way to remove all the cars containing illegal goods from the sequence is to
- remove a car from the left end 3 times. Time taken is 3 * 1 = 3.
This obtains a total time of 3.

Another way to remove all the cars containing illegal goods from the sequence is to
- remove the car containing illegal goods found in the middle. Time taken is 2.
This obtains a total time of 2.

Another way to remove all the cars containing illegal goods from the sequence is to 
- remove a car from the right end 2 times. Time taken is 2 * 1 = 2. 
This obtains a total time of 2.

2 is the minimum time taken to remove all the cars containing illegal goods. 
There are no other ways to remove them with less time.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumTime(self, s: str) -> int:
        n = len(s)
        pre = [0] * (n + 1)
        suf = [0] * (n + 1)
        for i, c in enumerate(s):
            pre[i + 1] = pre[i] if c == '0' else min(pre[i] + 2, i + 1)
        for i in range(n - 1, -1, -1):
            suf[i] = suf[i + 1] if s[i] == '0' else min(suf[i + 1] + 2, n - i)
        return min(a + b for a, b in zip(pre[1:], suf[1:]))
```

### **Java**

```java
class Solution {
    public int minimumTime(String s) {
        int n = s.length();
        int[] pre = new int[n + 1];
        int[] suf = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            pre[i + 1] = s.charAt(i) == '0' ? pre[i] : Math.min(pre[i] + 2, i + 1);
        }
        for (int i = n - 1; i >= 0; --i) {
            suf[i] = s.charAt(i) == '0' ? suf[i + 1] : Math.min(suf[i + 1] + 2, n - i);
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; ++i) {
            ans = Math.min(ans, pre[i] + suf[i]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumTime(string s) {
        int n = s.size();
        vector<int> pre(n + 1);
        vector<int> suf(n + 1);
        for (int i = 0; i < n; ++i) pre[i + 1] = s[i] == '0' ? pre[i] : min(pre[i] + 2, i + 1);
        for (int i = n - 1; ~i; --i) suf[i] = s[i] == '0' ? suf[i + 1] : min(suf[i + 1] + 2, n - i);
        int ans = INT_MAX;
        for (int i = 1; i <= n; ++i) ans = min(ans, pre[i] + suf[i]);
        return ans;
    }
};
```

### **Go**

```go
func minimumTime(s string) int {
	n := len(s)
	pre := make([]int, n+1)
	suf := make([]int, n+1)
	for i, c := range s {
		pre[i+1] = pre[i]
		if c == '1' {
			pre[i+1] = min(pre[i]+2, i+1)
		}
	}
	for i := n - 1; i >= 0; i-- {
		suf[i] = suf[i+1]
		if s[i] == '1' {
			suf[i] = min(suf[i+1]+2, n-i)
		}
	}
	ans := 0x3f3f3f3f
	for i := 1; i <= n; i++ {
		ans = min(ans, pre[i]+suf[i])
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->

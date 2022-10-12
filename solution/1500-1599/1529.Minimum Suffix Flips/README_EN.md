# [1529. Minimum Suffix Flips](https://leetcode.com/problems/minimum-suffix-flips)

[中文文档](/solution/1500-1599/1529.Minimum%20Suffix%20Flips/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> binary string <code>target</code> of length <code>n</code>. You have another binary string <code>s</code> of length <code>n</code> that is initially set to all zeros. You want to make <code>s</code> equal to <code>target</code>.</p>

<p>In one operation, you can pick an index <code>i</code> where <code>0 &lt;= i &lt; n</code> and flip all bits in the <strong>inclusive</strong> range <code>[i, n - 1]</code>. Flip means changing <code>&#39;0&#39;</code> to <code>&#39;1&#39;</code> and <code>&#39;1&#39;</code> to <code>&#39;0&#39;</code>.</p>

<p>Return <em>the minimum number of operations needed to make </em><code>s</code><em> equal to </em><code>target</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = &quot;10111&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> Initially, s = &quot;00000&quot;.
Choose index i = 2: &quot;00<u>000</u>&quot; -&gt; &quot;00<u>111</u>&quot;
Choose index i = 0: &quot;<u>00111</u>&quot; -&gt; &quot;<u>11000</u>&quot;
Choose index i = 1: &quot;1<u>1000</u>&quot; -&gt; &quot;1<u>0111</u>&quot;
We need at least 3 flip operations to form target.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> target = &quot;101&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> Initially, s = &quot;000&quot;.
Choose index i = 0: &quot;<u>000</u>&quot; -&gt; &quot;<u>111</u>&quot;
Choose index i = 1: &quot;1<u>11</u>&quot; -&gt; &quot;1<u>00</u>&quot;
Choose index i = 2: &quot;10<u>0</u>&quot; -&gt; &quot;10<u>1</u>&quot;
We need at least 3 flip operations to form target.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> target = &quot;00000&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> We do not need any operations since the initial s already equals target.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == target.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>target[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minFlips(self, target: str) -> int:
        ans = 0
        for v in target:
            if (ans & 1) ^ int(v):
                ans += 1
        return ans
```

### **Java**

```java
class Solution {
    public int minFlips(String target) {
        int ans = 0;
        for (int i = 0; i < target.length(); ++i) {
            int v = target.charAt(i) - '0';
            if (((ans & 1) ^ v) != 0) {
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
    int minFlips(string target) {
        int ans = 0;
        for (char c : target) {
            int v = c - '0';
            if ((ans & 1) ^ v) {
                ++ans;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minFlips(target string) int {
	ans := 0
	for _, c := range target {
		v := int(c - '0')
		if ((ans & 1) ^ v) != 0 {
			ans++
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->

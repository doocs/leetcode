# [991. Broken Calculator](https://leetcode.com/problems/broken-calculator)

[中文文档](/solution/0900-0999/0991.Broken%20Calculator/README.md)

## Description

<p>There is a broken calculator that has the integer <code>startValue</code> on its display initially. In one operation, you can:</p>

<ul>
	<li>multiply the number on display by <code>2</code>, or</li>
	<li>subtract <code>1</code> from the number on display.</li>
</ul>

<p>Given two integers <code>startValue</code> and <code>target</code>, return <em>the minimum number of operations needed to display </em><code>target</code><em> on the calculator</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> startValue = 2, target = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> Use double operation and then decrement operation {2 -&gt; 4 -&gt; 3}.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> startValue = 5, target = 8
<strong>Output:</strong> 2
<strong>Explanation:</strong> Use decrement and then double {5 -&gt; 4 -&gt; 8}.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> startValue = 3, target = 10
<strong>Output:</strong> 3
<strong>Explanation:</strong> Use double, decrement and double {3 -&gt; 6 -&gt; 5 -&gt; 10}.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= startValue, target &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def brokenCalc(self, startValue: int, target: int) -> int:
        ans = 0
        while startValue < target:
            if target & 1:
                target += 1
            else:
                target >>= 1
            ans += 1
        ans += startValue - target
        return ans
```

### **Java**

```java
class Solution {
    public int brokenCalc(int startValue, int target) {
        int ans = 0;
        while (startValue < target) {
            if ((target & 1) == 1) {
                target++;
            } else {
                target >>= 1;
            }
            ans += 1;
        }
        ans += startValue - target;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int brokenCalc(int startValue, int target) {
        int ans = 0;
        while (startValue < target) {
            if (target & 1) {
                target++;
            } else {
                target >>= 1;
            }
            ++ans;
        }
        ans += startValue - target;
        return ans;
    }
};
```

### **Go**

```go
func brokenCalc(startValue int, target int) (ans int) {
	for startValue < target {
		if target&1 == 1 {
			target++
		} else {
			target >>= 1
		}
		ans++
	}
	ans += startValue - target
	return
}
```

### **...**

```

```

<!-- tabs:end -->

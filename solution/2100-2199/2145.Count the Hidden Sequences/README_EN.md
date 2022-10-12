# [2145. Count the Hidden Sequences](https://leetcode.com/problems/count-the-hidden-sequences)

[中文文档](/solution/2100-2199/2145.Count%20the%20Hidden%20Sequences/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array of <code>n</code> integers <code>differences</code>, which describes the <strong>differences </strong>between each pair of <strong>consecutive </strong>integers of a <strong>hidden</strong> sequence of length <code>(n + 1)</code>. More formally, call the hidden sequence <code>hidden</code>, then we have that <code>differences[i] = hidden[i + 1] - hidden[i]</code>.</p>

<p>You are further given two integers <code>lower</code> and <code>upper</code> that describe the <strong>inclusive</strong> range of values <code>[lower, upper]</code> that the hidden sequence can contain.</p>

<ul>
	<li>For example, given <code>differences = [1, -3, 4]</code>, <code>lower = 1</code>, <code>upper = 6</code>, the hidden sequence is a sequence of length <code>4</code> whose elements are in between <code>1</code> and <code>6</code> (<strong>inclusive</strong>).

    <ul>
    	<li><code>[3, 4, 1, 5]</code> and <code>[4, 5, 2, 6]</code> are possible hidden sequences.</li>
    	<li><code>[5, 6, 3, 7]</code> is not possible since it contains an element greater than <code>6</code>.</li>
    	<li><code>[1, 2, 3, 4]</code> is not possible since the differences are not correct.</li>
    </ul>
    </li>

</ul>

<p>Return <em>the number of <strong>possible</strong> hidden sequences there are.</em> If there are no possible sequences, return <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> differences = [1,-3,4], lower = 1, upper = 6
<strong>Output:</strong> 2
<strong>Explanation:</strong> The possible hidden sequences are:
- [3, 4, 1, 5]
- [4, 5, 2, 6]
Thus, we return 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> differences = [3,-4,5,1,-2], lower = -4, upper = 5
<strong>Output:</strong> 4
<strong>Explanation:</strong> The possible hidden sequences are:
- [-3, 0, -4, 1, 2, 0]
- [-2, 1, -3, 2, 3, 1]
- [-1, 2, -2, 3, 4, 2]
- [0, 3, -1, 4, 5, 3]
Thus, we return 4.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> differences = [4,-7,2], lower = 3, upper = 6
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no possible hidden sequences. Thus, we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == differences.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= differences[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= lower &lt;= upper &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numberOfArrays(self, differences: List[int], lower: int, upper: int) -> int:
        num = mi = mx = 0
        for d in differences:
            num += d
            mi = min(mi, num)
            mx = max(mx, num)
        return max(0, upper - lower - (mx - mi) + 1)
```

### **Java**

```java
class Solution {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        long num = 0, mi = 0, mx = 0;
        for (int d : differences) {
            num += d;
            mi = Math.min(mi, num);
            mx = Math.max(mx, num);
        }
        return Math.max(0, (int) (upper - lower - (mx - mi) + 1));
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfArrays(vector<int>& differences, int lower, int upper) {
        long long num = 0, mi = 0, mx = 0;
        for (int& d : differences) {
            num += d;
            mi = min(mi, num);
            mx = max(mx, num);
        }
        return max(0, (int)(upper - lower - (mx - mi) + 1));
    }
};
```

### **Go**

```go
func numberOfArrays(differences []int, lower int, upper int) int {
	num, mi, mx := 0, 0, 0
	for _, d := range differences {
		num += d
		mi = min(mi, num)
		mx = max(mx, num)
	}
	return max(0, upper-lower-(mx-mi)+1)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
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

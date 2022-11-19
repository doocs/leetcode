# [1732. Find the Highest Altitude](https://leetcode.com/problems/find-the-highest-altitude)

[中文文档](/solution/1700-1799/1732.Find%20the%20Highest%20Altitude/README.md)

## Description

<p>There is a biker going on a road trip. The road trip consists of <code>n + 1</code> points at different altitudes. The biker starts his trip on point <code>0</code> with altitude equal <code>0</code>.</p>

<p>You are given an integer array <code>gain</code> of length <code>n</code> where <code>gain[i]</code> is the <strong>net gain in altitude</strong> between points <code>i</code>​​​​​​ and <code>i + 1</code> for all (<code>0 &lt;= i &lt; n)</code>. Return <em>the <strong>highest altitude</strong> of a point.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> gain = [-5,1,5,0,-7]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The altitudes are [0,-5,-4,1,1,-6]. The highest is 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> gain = [-4,-3,-2,-1,4,3,2]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The altitudes are [0,-4,-7,-9,-10,-6,-3,-1]. The highest is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == gain.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>-100 &lt;= gain[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def largestAltitude(self, gain: List[int]) -> int:
        return max(accumulate(gain, initial=0))
```

```python
class Solution:
    def largestAltitude(self, gain: List[int]) -> int:
        ans = h = 0
        for v in gain:
            h += v
            ans = max(ans, h)
        return ans
```

### **Java**

```java
class Solution {
    public int largestAltitude(int[] gain) {
        int ans = 0, h = 0;
        for (int v : gain) {
            h += v;
            ans = Math.max(ans, h);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int largestAltitude(vector<int>& gain) {
        int ans = 0, h = 0;
        for (int v : gain) h += v, ans = max(ans, h);
        return ans;
    }
};
```

### **Go**

```go
func largestAltitude(gain []int) (ans int) {
	h := 0
	for _, v := range gain {
		h += v
		if ans < h {
			ans = h
		}
	}
	return
}
```

### **JavaScript**

```js
/**
 * @param {number[]} gain
 * @return {number}
 */
var largestAltitude = function (gain) {
    let ans = 0;
    let h = 0;
    for (const v of gain) {
        h += v;
        ans = Math.max(ans, h);
    }
    return ans;
};
```

### **Rust**

```rust
impl Solution {
    pub fn largest_altitude(gain: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut h = 0;
        for v in gain.iter() {
            h += v;
            ans = ans.max(h);
        }
        ans
    }
}
```

### **C**

```c
#define max(a, b) (((a) > (b)) ? (a) : (b))

int largestAltitude(int *gain, int gainSize) {
    int ans = 0;
    int h = 0;
    for (int i = 0; i < gainSize; i++) {
        h += gain[i];
        ans = max(ans, h);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->

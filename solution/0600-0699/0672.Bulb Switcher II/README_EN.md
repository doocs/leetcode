# [672. Bulb Switcher II](https://leetcode.com/problems/bulb-switcher-ii)

[中文文档](/solution/0600-0699/0672.Bulb%20Switcher%20II/README.md)

## Description

<p>There is a room with <code>n</code> bulbs labeled from <code>1</code> to <code>n</code> that all are turned on initially, and <strong>four buttons</strong> on the wall. Each of the four buttons has a different functionality where:</p>

<ul>
	<li><strong>Button 1:</strong> Flips the status of all the bulbs.</li>
	<li><strong>Button 2:</strong> Flips the status of all the bulbs with even labels (i.e., <code>2, 4, ...</code>).</li>
	<li><strong>Button 3:</strong> Flips the status of all the bulbs with odd labels (i.e., <code>1, 3, ...</code>).</li>
	<li><strong>Button 4:</strong> Flips the status of all the bulbs with a label <code>j = 3k + 1</code> where <code>k = 0, 1, 2, ...</code> (i.e., <code>1, 4, 7, 10, ...</code>).</li>
</ul>

<p>You must make <strong>exactly</strong> <code>presses</code> button presses in total. For each press, you may pick <strong>any</strong> of the four buttons to press.</p>

<p>Given the two integers <code>n</code> and <code>presses</code>, return <em>the number of <strong>different possible statuses</strong> after performing all </em><code>presses</code><em> button presses</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1, presses = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> Status can be:
- [off] by pressing button 1
- [on] by pressing button 2
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, presses = 1
<strong>Output:</strong> 3
<strong>Explanation:</strong> Status can be:
- [off, off] by pressing button 1
- [on, off] by pressing button 2
- [off, on] by pressing button 3
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 3, presses = 1
<strong>Output:</strong> 4
<strong>Explanation:</strong> Status can be:
- [off, off, off] by pressing button 1
- [off, on, off] by pressing button 2
- [on, off, on] by pressing button 3
- [off, on, on] by pressing button 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= presses &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def flipLights(self, n: int, presses: int) -> int:
        ops = (0b111111, 0b010101, 0b101010, 0b100100)
        n = min(n, 6)
        vis = set()
        for mask in range(1 << 4):
            cnt = mask.bit_count()
            if cnt <= presses and cnt % 2 == presses % 2:
                t = 0
                for i, op in enumerate(ops):
                    if (mask >> i) & 1:
                        t ^= op
                t &= (1 << 6) - 1
                t >>= 6 - n
                vis.add(t)
        return len(vis)
```

### **Java**

```java
class Solution {
    public int flipLights(int n, int presses) {
        int[] ops = new int[] {0b111111, 0b010101, 0b101010, 0b100100};
        Set<Integer> vis = new HashSet<>();
        n = Math.min(n, 6);
        for (int mask = 0; mask < 1 << 4; ++mask) {
            int cnt = Integer.bitCount(mask);
            if (cnt <= presses && cnt % 2 == presses % 2) {
                int t = 0;
                for (int i = 0; i < 4; ++i) {
                    if (((mask >> i) & 1) == 1) {
                        t ^= ops[i];
                    }
                }
                t &= ((1 << 6) - 1);
                t >>= (6 - n);
                vis.add(t);
            }
        }
        return vis.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int flipLights(int n, int presses) {
        n = min(n, 6);
        vector<int> ops = {0b111111, 0b010101, 0b101010, 0b100100};
        unordered_set<int> vis;
        for (int mask = 0; mask < 1 << 4; ++mask) {
            int cnt = __builtin_popcount(mask);
            if (cnt > presses || cnt % 2 != presses % 2) continue;
            int t = 0;
            for (int i = 0; i < 4; ++i) {
                if (mask >> i & 1) {
                    t ^= ops[i];
                }
            }
            t &= (1 << 6) - 1;
            t >>= (6 - n);
            vis.insert(t);
        }
        return vis.size();
    }
};
```

### **Go**

```go
func flipLights(n int, presses int) int {
	if n > 6 {
		n = 6
	}
	ops := []int{0b111111, 0b010101, 0b101010, 0b100100}
	vis := map[int]bool{}
	for mask := 0; mask < 1<<4; mask++ {
		cnt := bits.OnesCount(uint(mask))
		if cnt <= presses && cnt%2 == presses%2 {
			t := 0
			for i, op := range ops {
				if mask>>i&1 == 1 {
					t ^= op
				}
			}
			t &= 1<<6 - 1
			t >>= (6 - n)
			vis[t] = true
		}
	}
	return len(vis)
}
```

### **...**

```

```

<!-- tabs:end -->

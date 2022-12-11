# [2498. Frog Jump II](https://leetcode.com/problems/frog-jump-ii)

[中文文档](/solution/2400-2499/2498.Frog%20Jump%20II/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>stones</code> sorted in <strong>strictly increasing order</strong> representing the positions of stones in a river.</p>

<p>A frog, initially on the first stone, wants to travel to the last stone and then return to the first stone. However, it can jump to any stone <strong>at most once</strong>.</p>

<p>The <strong>length</strong> of a jump is the absolute difference between the position of the stone the frog is currently on and the position of the stone to which the frog jumps.</p>

<ul>
	<li>More formally, if the frog is at <code>stones[i]</code> and is jumping to <code>stones[j]</code>, the length of the jump is <code>|stones[i] - stones[j]|</code>.</li>
</ul>

<p>The <strong>cost</strong> of a path is the <strong>maximum length of a jump</strong> among all jumps in the path.</p>

<p>Return <em>the <strong>minimum</strong> cost of a path for the frog</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2498.Frog%20Jump%20II/images/example-1.png" style="width: 600px; height: 219px;" />
<pre>
<strong>Input:</strong> stones = [0,2,5,6,7]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The above figure represents one of the optimal paths the frog can take.
The cost of this path is 5, which is the maximum length of a jump.
Since it is not possible to achieve a cost of less than 5, we return it.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2498.Frog%20Jump%20II/images/example-2.png" style="width: 500px; height: 171px;" />
<pre>
<strong>Input:</strong> stones = [0,3,9]
<strong>Output:</strong> 9
<strong>Explanation:</strong> 
The frog can jump directly to the last stone and come back to the first stone. 
In this case, the length of each jump will be 9. The cost for the path will be max(9, 9) = 9.
It can be shown that this is the minimum achievable cost.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= stones.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= stones[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>stones[0] == 0</code></li>
	<li><code>stones</code> is sorted in a strictly increasing order.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxJump(self, stones: List[int]) -> int:
        ans = stones[1] - stones[0]
        for i in range(2, len(stones)):
            ans = max(ans, stones[i] - stones[i - 2])
        return ans
```

### **Java**

```java
class Solution {
    public int maxJump(int[] stones) {
        int ans = stones[1] - stones[0];
        for (int i = 2; i < stones.length; ++i) {
            ans = Math.max(ans, stones[i] - stones[i - 2]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxJump(vector<int>& stones) {
        int ans = stones[1] - stones[0];
        for (int i = 2; i < stones.size(); ++i) ans = max(ans, stones[i] - stones[i - 2]);
        return ans;
    }
};
```

### **Go**

```go
func maxJump(stones []int) int {
	ans := stones[1] - stones[0]
	for i := 2; i < len(stones); i++ {
		ans = max(ans, stones[i]-stones[i-2])
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->

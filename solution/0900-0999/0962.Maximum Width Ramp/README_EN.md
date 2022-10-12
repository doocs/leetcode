# [962. Maximum Width Ramp](https://leetcode.com/problems/maximum-width-ramp)

[中文文档](/solution/0900-0999/0962.Maximum%20Width%20Ramp/README.md)

## Description

<p>A <strong>ramp</strong> in an integer array <code>nums</code> is a pair <code>(i, j)</code> for which <code>i &lt; j</code> and <code>nums[i] &lt;= nums[j]</code>. The <strong>width</strong> of such a ramp is <code>j - i</code>.</p>

<p>Given an integer array <code>nums</code>, return <em>the maximum width of a <strong>ramp</strong> in </em><code>nums</code>. If there is no <strong>ramp</strong> in <code>nums</code>, return <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,0,8,2,1,5]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The maximum width ramp is achieved at (i, j) = (1, 5): nums[1] = 0 and nums[5] = 5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [9,8,1,0,1,9,4,0,4,1]
<strong>Output:</strong> 7
<strong>Explanation:</strong> The maximum width ramp is achieved at (i, j) = (2, 9): nums[2] = 1 and nums[9] = 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 5 * 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxWidthRamp(self, nums: List[int]) -> int:
        stk = []
        for i, v in enumerate(nums):
            if not stk or nums[stk[-1]] > v:
                stk.append(i)
        ans = 0
        for i in range(len(nums) - 1, -1, -1):
            while stk and nums[stk[-1]] <= nums[i]:
                ans = max(ans, i - stk.pop())
            if not stk:
                break
        return ans
```

### **Java**

```java
class Solution {
    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (stk.isEmpty() || nums[stk.peek()] > nums[i]) {
                stk.push(i);
            }
        }
        int ans = 0;
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && nums[stk.peek()] <= nums[i]) {
                ans = Math.max(ans, i - stk.pop());
            }
            if (stk.isEmpty()) {
                break;
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
    int maxWidthRamp(vector<int>& nums) {
        int n = nums.size();
        stack<int> stk;
        for (int i = 0; i < n; ++i) {
            if (stk.empty() || nums[stk.top()] > nums[i]) stk.push(i);
        }
        int ans = 0;
        for (int i = n - 1; i; --i) {
            while (!stk.empty() && nums[stk.top()] <= nums[i]) {
                ans = max(ans, i - stk.top());
                stk.pop();
            }
            if (stk.empty()) break;
        }
        return ans;
    }
};
```

### **Go**

```go
func maxWidthRamp(nums []int) int {
	n := len(nums)
	stk := []int{}
	for i, v := range nums {
		if len(stk) == 0 || nums[stk[len(stk)-1]] > v {
			stk = append(stk, i)
		}
	}
	ans := 0
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 && nums[stk[len(stk)-1]] <= nums[i] {
			ans = max(ans, i-stk[len(stk)-1])
			stk = stk[:len(stk)-1]
		}
		if len(stk) == 0 {
			break
		}
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

# [1674. Minimum Moves to Make Array Complementary](https://leetcode.com/problems/minimum-moves-to-make-array-complementary)

[中文文档](/solution/1600-1699/1674.Minimum%20Moves%20to%20Make%20Array%20Complementary/README.md)

## Description

<p>You are given an integer array <code>nums</code> of <strong>even</strong> length <code>n</code> and an integer <code>limit</code>. In one move, you can replace any integer from <code>nums</code> with another integer between <code>1</code> and <code>limit</code>, inclusive.</p>

<p>The array <code>nums</code> is <strong>complementary</strong> if for all indices <code>i</code> (<strong>0-indexed</strong>), <code>nums[i] + nums[n - 1 - i]</code> equals the same number. For example, the array <code>[1,2,3,4]</code> is complementary because for all indices <code>i</code>, <code>nums[i] + nums[n - 1 - i] = 5</code>.</p>

<p>Return the <em><strong>minimum</strong> number of moves required to make </em><code>nums</code><em> <strong>complementary</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,4,3], limit = 4
<strong>Output:</strong> 1
<strong>Explanation:</strong> In 1 move, you can change nums to [1,2,<u>2</u>,3] (underlined elements are changed).
nums[0] + nums[3] = 1 + 3 = 4.
nums[1] + nums[2] = 2 + 2 = 4.
nums[2] + nums[1] = 2 + 2 = 4.
nums[3] + nums[0] = 3 + 1 = 4.
Therefore, nums[i] + nums[n-1-i] = 4 for every i, so nums is complementary.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,2,1], limit = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> In 2 moves, you can change nums to [<u>2</u>,2,2,<u>2</u>]. You cannot change any number to 3 since 3 &gt; limit.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,1,2], limit = 2
<strong>Output:</strong> 0
<strong>Explanation:</strong> nums is already complementary.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>2 &lt;= n&nbsp;&lt;=&nbsp;10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i]&nbsp;&lt;= limit &lt;=&nbsp;10<sup>5</sup></code></li>
	<li><code>n</code> is even.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minMoves(self, nums: List[int], limit: int) -> int:
        d = [0] * (limit * 2 + 2)
        n = len(nums)

        for i in range(n >> 1):
            a, b = min(nums[i], nums[n - i - 1]), max(nums[i], nums[n - i - 1])

            d[2] += 2
            d[limit * 2 + 1] -= 2

            d[a + 1] -= 1
            d[b + limit + 1] += 1

            d[a + b] -= 1
            d[a + b + 1] += 1

        ans, s = n, 0
        for v in d[2: limit * 2 + 1]:
            s += v
            if ans > s:
                ans = s
        return ans
```

### **Java**

```java
class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] d = new int[limit * 2 + 2];
        for (int i = 0; i<n> > 1; ++i) {
            int a = Math.min(nums[i], nums[n - i - 1]);
            int b = Math.max(nums[i], nums[n - i - 1]);

            d[2] += 2;
            d[limit * 2 + 1] -= 2;

            d[a + 1] -= 1;
            d[b + limit + 1] += 1;

            d[a + b] -= 1;
            d[a + b + 1] += 1;
        }
        int ans = n, s = 0;
        for (int i = 2; i <= limit * 2; ++i) {
            s += d[i];
            if (ans > s) {
                ans = s;
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
    int minMoves(vector<int>& nums, int limit) {
        int n = nums.size();
        vector<int> d(limit * 2 + 2);
        for (int i = 0; i < n >> 1; ++i) {
            int a = min(nums[i], nums[n - i - 1]);
            int b = max(nums[i], nums[n - i - 1]);

            d[2] += 2;
            d[limit * 2 + 1] -= 2;

            d[a + 1] -= 1;
            d[b + limit + 1] += 1;

            d[a + b] -= 1;
            d[a + b + 1] += 1;
        }
        int ans = n, s = 0;
        for (int i = 2; i <= limit * 2; ++i) {
            s += d[i];
            if (ans > s) {
                ans = s;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minMoves(nums []int, limit int) int {
	d := make([]int, limit*2+2)
	n := len(nums)
	for i := 0; i < n>>1; i++ {
		a, b := min(nums[i], nums[n-i-1]), max(nums[i], nums[n-i-1])
		d[2] += 2
		d[limit*2+1] -= 2

		d[a+1] -= 1
		d[b+limit+1] += 1

		d[a+b] -= 1
		d[a+b+1] += 1
	}
	ans, s := n, 0
	for _, v := range d[2 : limit*2+1] {
		s += v
		if ans > s {
			ans = s
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

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->

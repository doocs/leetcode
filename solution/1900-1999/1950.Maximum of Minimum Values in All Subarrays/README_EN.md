# [1950. Maximum of Minimum Values in All Subarrays](https://leetcode.com/problems/maximum-of-minimum-values-in-all-subarrays)

[中文文档](/solution/1900-1999/1950.Maximum%20of%20Minimum%20Values%20in%20All%20Subarrays/README.md)

## Description

<p>You are given an integer array <code>nums</code> of size <code>n</code>. You are asked to solve <code>n</code> queries for each integer <code>i</code> in the range <code>0 &lt;= i &lt; n</code>.</p>

<p>To solve the <code>i<sup>th</sup></code> query:</p>

<ol>
	<li>Find the <strong>minimum value</strong> in each possible subarray of size <code>i + 1</code> of the array <code>nums</code>.</li>
	<li>Find the <strong>maximum</strong> of those minimum values. This maximum is the <strong>answer</strong> to the query.</li>
</ol>

<p>Return <em>a <strong>0-indexed</strong> integer array</em> <code>ans</code> <em>of size </em><code>n</code> <em>such that </em><code>ans[i]</code> <em>is the answer to the </em><code>i<sup>th</sup></code> <em>query</em>.</p>

<p>A <strong>subarray</strong> is a contiguous sequence of elements in an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,2,4]
<strong>Output:</strong> [4,2,1,0]
<strong>Explanation:</strong>
i=0:
- The subarrays of size 1 are [0], [1], [2], [4]. The minimum values are 0, 1, 2, 4.
- The maximum of the minimum values is 4.
i=1:
- The subarrays of size 2 are [0,1], [1,2], [2,4]. The minimum values are 0, 1, 2.
- The maximum of the minimum values is 2.
i=2:
- The subarrays of size 3 are [0,1,2], [1,2,4]. The minimum values are 0, 1.
- The maximum of the minimum values is 1.
i=3:
- There is one subarray of size 4, which is [0,1,2,4]. The minimum value is 0.
- There is only one value, so the maximum is 0.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,20,50,10]
<strong>Output:</strong> [50,20,10,10]
<strong>Explanation:</strong>
i=0:
- The subarrays of size 1 are [10], [20], [50], [10]. The minimum values are 10, 20, 50, 10.
- The maximum of the minimum values is 50.
i=1:
- The subarrays of size 2 are [10,20], [20,50], [50,10]. The minimum values are 10, 20, 10.
- The maximum of the minimum values is 20.
i=2:
- The subarrays of size 3 are [10,20,50], [20,50,10]. The minimum values are 10, 10.
- The maximum of the minimum values is 10.
i=3:
- There is one subarray of size 4, which is [10,20,50,10]. The minimum value is 10.
- There is only one value, so the maximum is 10.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findMaximums(self, nums: List[int]) -> List[int]:
        n = len(nums)
        left = [-1] * n
        right = [n] * n
        stk = []
        for i, x in enumerate(nums):
            while stk and nums[stk[-1]] >= x:
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)
        stk = []
        for i in range(n - 1, -1, -1):
            while stk and nums[stk[-1]] >= nums[i]:
                stk.pop()
            if stk:
                right[i] = stk[-1]
            stk.append(i)
        ans = [0] * n
        for i in range(n):
            m = right[i] - left[i] - 1
            ans[m - 1] = max(ans[m - 1], nums[i])
        for i in range(n - 2, -1, -1):
            ans[i] = max(ans[i], ans[i + 1])
        return ans
```

### **Java**

```java
class Solution {
    public int[] findMaximums(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            while (!stk.isEmpty() && nums[stk.peek()] >= nums[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                left[i] = stk.peek();
            }
            stk.push(i);
        }
        stk.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && nums[stk.peek()] >= nums[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                right[i] = stk.peek();
            }
            stk.push(i);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int m = right[i] - left[i] - 1;
            ans[m - 1] = Math.max(ans[m - 1], nums[i]);
        }
        for (int i = n - 2; i >= 0; --i) {
            ans[i] = Math.max(ans[i], ans[i + 1]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> findMaximums(vector<int>& nums) {
        int n = nums.size();
        vector<int> left(n, -1);
        vector<int> right(n, n);
        stack<int> stk;
        for (int i = 0; i < n; ++i) {
            while (!stk.empty() && nums[stk.top()] >= nums[i]) {
                stk.pop();
            }
            if (!stk.empty()) {
                left[i] = stk.top();
            }
            stk.push(i);
        }
        stk = stack<int>();
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.empty() && nums[stk.top()] >= nums[i]) {
                stk.pop();
            }
            if (!stk.empty()) {
                right[i] = stk.top();
            }
            stk.push(i);
        }
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            int m = right[i] - left[i] - 1;
            ans[m - 1] = max(ans[m - 1], nums[i]);
        }
        for (int i = n - 2; i >= 0; --i) {
            ans[i] = max(ans[i], ans[i + 1]);
        }
        return ans;
    }
};
```

### **Go**

```go
func findMaximums(nums []int) []int {
	n := len(nums)
	left := make([]int, n)
	right := make([]int, n)
	for i := range left {
		left[i], right[i] = -1, n
	}
	stk := []int{}
	for i, x := range nums {
		for len(stk) > 0 && nums[stk[len(stk)-1]] >= x {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			left[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	stk = []int{}
	for i := n - 1; i >= 0; i-- {
		x := nums[i]
		for len(stk) > 0 && nums[stk[len(stk)-1]] >= x {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			right[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	ans := make([]int, n)
	for i := range ans {
		m := right[i] - left[i] - 1
		ans[m-1] = max(ans[m-1], nums[i])
	}
	for i := n - 2; i >= 0; i-- {
		ans[i] = max(ans[i], ans[i+1])
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

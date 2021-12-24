# [239. Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum)

[中文文档](/solution/0200-0299/0239.Sliding%20Window%20Maximum/README.md)

## Description

<p>You are given an array of integers&nbsp;<code>nums</code>, there is a sliding window of size <code>k</code> which is moving from the very left of the array to the very right. You can only see the <code>k</code> numbers in the window. Each time the sliding window moves right by one position.</p>

<p>Return <em>the max sliding window</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,-1,-3,5,3,6,7], k = 3
<strong>Output:</strong> [3,3,5,5,6,7]
<strong>Explanation:</strong> 
Window position                Max
---------------             -----
[1  3  -1] -3  5  3  6  7       <strong>3</strong>
 1 [3  -1  -3] 5  3  6  7       <strong>3</strong>
 1  3 [-1  -3  5] 3  6  7      <strong> 5</strong>
 1  3  -1 [-3  5  3] 6  7       <strong>5</strong>
 1  3  -1  -3 [5  3  6] 7       <strong>6</strong>
 1  3  -1  -3  5 [3  6  7]      <strong>7</strong>
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1], k = 1
<strong>Output:</strong> [1]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,-1], k = 1
<strong>Output:</strong> [1,-1]
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> nums = [9,11], k = 2
<strong>Output:</strong> [11]
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,-2], k = 2
<strong>Output:</strong> [4]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        q, res = deque(), []
        for i, num in enumerate(nums):
            if q and i - k + 1 > q[0]:
                q.popleft()
            while q and nums[q[-1]] <= num:
                q.pop()
            q.append(i)
            if i >= k - 1:
                res.append(nums[q[0]])
        return res
```

### **Java**

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) {
            return new int[0];
        }
        int[] res = new int[n - k + 1];
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0, j = 0; i < n; ++i) {
            if (!q.isEmpty() && i - k + 1 > q.peekFirst()) {
                q.pollFirst();
            }
            while (!q.isEmpty() && nums[q.peekLast()] <= nums[i]) {
                q.pollLast();
            }
            q.offerLast(i);
            if (i >= k - 1) {
                res[j++] = nums[q.peekFirst()];
            }
        }
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number[]}
 */
var maxSlidingWindow = function (nums, k) {
    let len = nums.length;
    if (len < k) return [];
    let res = [],
        win = [];
    for (let i = 0; i < k; i++) {
        while (win.length > 0 && nums[i] >= nums[win[win.length - 1]])
            win.pop();
        win.push(i);
    }
    res.push(nums[win[0]]);
    for (let i = k; i < len; i++) {
        while (win.length > 0 && nums[i] >= nums[win[win.length - 1]])
            win.pop();
        if (win.length > 0 && win[0] < i - k + 1) win.shift();
        win.push(i);
        res.push(nums[win[0]]);
    }
    return res;
};
```

### **C++**

```cpp
class Solution {
public:
	vector<int> maxSlidingWindow(vector<int> &nums, int k) {
		vector<int> res;
		deque<int> q;
		for (int i = 0; i < nums.size(); ++i)
		{
			if (!q.empty() && i - k + 1 > q.front())
				q.pop_front();
			while (!q.empty() && nums[q.back()] <= nums[i])
				q.pop_back();
			q.push_back(i);
			if (i >= k - 1)
				res.push_back(nums[q.front()]);
		}
		return res;
	}
};
```

### **Go**

```go
func maxSlidingWindow(nums []int, k int) []int {
	var res []int
	var q []int
	for i, num := range nums {
		if len(q) > 0 && i-k+1 > q[0] {
			q = q[1:]
		}
		for len(q) > 0 && nums[q[len(q)-1]] <= num {
			q = q[:len(q)-1]
		}
		q = append(q, i)
		if i >= k-1 {
			res = append(res, nums[q[0]])
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->

# [1950. 所有子数组最小值中的最大值](https://leetcode.cn/problems/maximum-of-minimum-values-in-all-subarrays)

[English Version](/solution/1900-1999/1950.Maximum%20of%20Minimum%20Values%20in%20All%20Subarrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code> ，你需要处理 <code>n</code> 个查询。</p>

<p>对于第 <code>i</code> （<code>0 &lt;= i &lt;&nbsp;n</code>）个查询：</p>

<ol>
	<li>你需要先找出 <code>nums</code> 的所有长度为 <code>i + 1</code> 的子数组中的<strong> 最小值</strong> 。</li>
	<li>在这些最小值中找出<strong> 最大值</strong> 作为答案。</li>
</ol>

<p>返回一个 <strong>下标从 0 开始</strong> 的长度为 <code>n</code> 的整数数组 <code>ans</code> ，<code>ans[i]</code> 代表第 <code>i</code> 个查询的答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> nums = [0,1,2,4]
<strong>输出:</strong> [4,2,1,0]
<strong>解释:</strong>
i = 0:
- 大小为 1 的子数组为 [0], [1], [2], [4]
- 有最大的最小值的子数组是 [4], 它的最小值是 4
i = 1:
- 大小为 2 的子数组为 [0,1], [1,2], [2,4]
- 有最大的最小值的子数组是 [2,4], 它的最小值是 2
i = 2:
- 大小为 3 的子数组为 [0,1,2], [1,2,4]
- 有最大的最小值的子数组是 [1,2,4], 它的最小值是 1
i = 3:
- 大小为 4 的子数组为 [0,1,2,4]
- 有最大的最小值的子数组是 [0,1,2,4], 它的最小值是 0</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入: </strong>nums = [10,20,50,10]
<strong>输出: </strong>[50,20,10,10]
<strong>解释:</strong>
i = 0: 
- 大小为 1 的子数组为 [10], [20], [50], [10]
- 有最大的最小值的子数组是 [50], 它的最小值是 50
i = 1: 
- 大小为 2 的子数组为 [10,20], [20,50], [50,10]
- 有最大的最小值的子数组是 [20,50], 它的最小值是 20
i = 2: 
- 大小为 3 的子数组为 [10,20,50], [20,50,10]
- 有最大的最小值的子数组是 [10,20,50], 它的最小值是 10
i = 3: 
- 大小为 4 的子数组为 [10,20,50,10]
- 有最大的最小值的子数组是 [10,20,50,10], 它的最小值是 10</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：单调栈**

我们可以先利用单调栈，求出每个位置的左边第一个比它小的位置 $left[i]$ 和右边第一个比它小的位置 $right[i]$，那么以 $nums[i]$ 为最小值的子数组的长度为 $m = right[i] - left[i] - 1$。

然后我们遍历数组，对于每个位置 $i$，更新 $ans[m - 1] = max(ans[m - 1], nums[i])$。

接着我们倒序遍历数组，更新 $ans[i] = max(ans[i], ans[i + 1])$。

最后返回 $ans$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

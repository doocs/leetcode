# [1673. 找出最具竞争力的子序列](https://leetcode.cn/problems/find-the-most-competitive-subsequence)

[English Version](/solution/1600-1699/1673.Find%20the%20Most%20Competitive%20Subsequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 和一个正整数 <code>k</code> ，返回长度为 <code>k</code> 且最具 <strong>竞争力</strong> 的<em> </em><code>nums</code> 子序列。</p>

<p>数组的子序列是从数组中删除一些元素（可能不删除元素）得到的序列。</p>

<p>在子序列 <code>a</code> 和子序列 <code>b</code> 第一个不相同的位置上，如果 <code>a</code> 中的数字小于 <code>b</code> 中对应的数字，那么我们称子序列 <code>a</code> 比子序列 <code>b</code>（相同长度下）更具 <strong>竞争力</strong> 。 例如，<code>[1,3,4]</code> 比 <code>[1,3,5]</code> 更具竞争力，在第一个不相同的位置，也就是最后一个位置上， <code>4</code> 小于 <code>5</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,5,2,6], k = 2
<strong>输出：</strong>[2,6]
<strong>解释：</strong>在所有可能的子序列集合 {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]} 中，[2,6] 最具竞争力。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,4,3,3,5,4,9,6], k = 4
<strong>输出：</strong>[2,3,3,4]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>0 <= nums[i] <= 10<sup>9</sup></code></li>
	<li><code>1 <= k <= nums.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：栈**

我们从左到右遍历数组 `nums`，维护一个栈 `stk`，遍历过程中，如果当前元素 `nums[i]` 小于栈顶元素，且栈中元素个数加上 $n-i$ 大于 $k$，则将栈顶元素出栈，直到不满足上述条件为止。此时如果栈中元素个数小于 $k$，则将当前元素入栈。

遍历结束后，栈中元素即为所求。

时间复杂度 $O(n)$，空间复杂度 $O(k)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def mostCompetitive(self, nums: List[int], k: int) -> List[int]:
        stk = []
        n = len(nums)
        for i, v in enumerate(nums):
            while stk and stk[-1] > v and len(stk) + n - i > k:
                stk.pop()
            if len(stk) < k:
                stk.append(v)
        return stk
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        Deque<Integer> stk = new ArrayDeque<>();
        int n = nums.length;
        for (int i = 0; i < nums.length; ++i) {
            while (!stk.isEmpty() && stk.peek() > nums[i] && stk.size() + n - i > k) {
                stk.pop();
            }
            if (stk.size() < k) {
                stk.push(nums[i]);
            }
        }
        int[] ans = new int[stk.size()];
        for (int i = ans.length - 1; i >= 0; --i) {
            ans[i] = stk.pop();
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> mostCompetitive(vector<int>& nums, int k) {
        vector<int> stk;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            while (stk.size() && stk.back() > nums[i] && stk.size() + n - i > k) {
                stk.pop_back();
            }
            if (stk.size() < k) {
                stk.push_back(nums[i]);
            }
        }
        return stk;
    }
};
```

### **Go**

```go
func mostCompetitive(nums []int, k int) []int {
	stk := []int{}
	n := len(nums)
	for i, v := range nums {
		for len(stk) > 0 && stk[len(stk)-1] > v && len(stk)+n-i > k {
			stk = stk[:len(stk)-1]
		}
		if len(stk) < k {
			stk = append(stk, v)
		}
	}
	return stk
}
```

### **...**

```

```

<!-- tabs:end -->

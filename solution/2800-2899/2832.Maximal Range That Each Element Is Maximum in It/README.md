# [2832. 每个元素为最大值的最大范围](https://leetcode.cn/problems/maximal-range-that-each-element-is-maximum-in-it)

[English Version](/solution/2800-2899/2832.Maximal%20Range%20That%20Each%20Element%20Is%20Maximum%20in%20It/README_EN.md)

<!-- tags:栈,数组,单调栈 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>现给定一个由 <strong>不同</strong> 整数构成的 <strong>0</strong> 索引数组 <code>nums</code> 。</p>

<p>我们用以下方式定义与 <code>nums</code> 长度相同的 <strong>0</strong> 索引数组 <code>ans</code> ：</p>

<ul>
	<li><code>ans[i]</code> 是子数组 <code>nums[l..r]</code> 的 <strong>最大</strong> 长度，该子数组中的最大元素等于 <code>nums[i]</code> 。</li>
</ul>

<p>返回数组 <code>ans</code> 。</p>

<p><strong>注意</strong>，<strong>子数组</strong> 是数组的连续部分。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,5,4,3,6]
<b>输出：</b>[1,4,2,1,5]
<b>解释：</b>对于 nums[0]，最长的子数组，其中最大值为 1，是 nums[0..0]，所以 ans[0] = 1。 
对于 nums[1]，最长的子数组，是 nums[0..3]，其中最大值为 5，所以 ans[1] = 4。 
对于 nums[2]，最长的子数组，是 nums[2..3]，其中最大值为 4，所以 ans[2] = 2。 
对于 nums[3]，最长的子数组，是 nums[3..3]，其中最大值为 3，所以 ans[3] = 1。 
对于 nums[4]，最长的子数组，是 nums[0..4]，其中最大值为 6，所以 ans[4] = 5。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,4,5]
<b>输出：</b>[1,2,3,4,5]
<b>解释：</b>对于 nums[i]，最长的子数组，是 nums[0..i]，其中最大值与 nums[i] 相等，所以 ans[i] = i + 1。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li>所有&nbsp;<code>nums</code> 中的元素都是不重复的。</li>
</ul>

## 解法

### 方法一：单调栈

本题属于单调栈的模板题，我们只需要利用单调栈，求出每个元素 $nums[i]$ 左边和右边第一个比它大的元素的位置，分别记为 $left[i]$ 和 $right[i]$，那么 $nums[i]$ 作为最大值的区间长度就是 $right[i] - left[i] - 1$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

```python
class Solution:
    def maximumLengthOfRanges(self, nums: List[int]) -> List[int]:
        n = len(nums)
        left = [-1] * n
        right = [n] * n
        stk = []
        for i, x in enumerate(nums):
            while stk and nums[stk[-1]] <= x:
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)
        stk = []
        for i in range(n - 1, -1, -1):
            while stk and nums[stk[-1]] <= nums[i]:
                stk.pop()
            if stk:
                right[i] = stk[-1]
            stk.append(i)
        return [r - l - 1 for l, r in zip(left, right)]
```

```java
class Solution {
    public int[] maximumLengthOfRanges(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            while (!stk.isEmpty() && nums[stk.peek()] <= nums[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                left[i] = stk.peek();
            }
            stk.push(i);
        }
        stk.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && nums[stk.peek()] <= nums[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                right[i] = stk.peek();
            }
            stk.push(i);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = right[i] - left[i] - 1;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> maximumLengthOfRanges(vector<int>& nums) {
        int n = nums.size();
        vector<int> left(n, -1);
        vector<int> right(n, n);
        stack<int> stk;
        for (int i = 0; i < n; ++i) {
            while (!stk.empty() && nums[stk.top()] <= nums[i]) {
                stk.pop();
            }
            if (!stk.empty()) {
                left[i] = stk.top();
            }
            stk.push(i);
        }
        stk = stack<int>();
        for (int i = n - 1; ~i; --i) {
            while (!stk.empty() && nums[stk.top()] <= nums[i]) {
                stk.pop();
            }
            if (!stk.empty()) {
                right[i] = stk.top();
            }
            stk.push(i);
        }
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            ans[i] = right[i] - left[i] - 1;
        }
        return ans;
    }
};
```

```go
func maximumLengthOfRanges(nums []int) []int {
	n := len(nums)
	left := make([]int, n)
	right := make([]int, n)
	for i := range left {
		left[i] = -1
		right[i] = n
	}
	stk := []int{}
	for i, x := range nums {
		for len(stk) > 0 && nums[stk[len(stk)-1]] <= x {
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
		for len(stk) > 0 && nums[stk[len(stk)-1]] <= x {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			right[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	ans := make([]int, n)
	for i := range ans {
		ans[i] = right[i] - left[i] - 1
	}
	return ans
}
```

```ts
function maximumLengthOfRanges(nums: number[]): number[] {
    const n = nums.length;
    const left: number[] = Array(n).fill(-1);
    const right: number[] = Array(n).fill(n);
    const stk: number[] = [];
    for (let i = 0; i < n; ++i) {
        while (stk.length && nums[stk.at(-1)] <= nums[i]) {
            stk.pop();
        }
        if (stk.length) {
            left[i] = stk.at(-1);
        }
        stk.push(i);
    }
    stk.length = 0;
    for (let i = n - 1; i >= 0; --i) {
        while (stk.length && nums[stk.at(-1)] <= nums[i]) {
            stk.pop();
        }
        if (stk.length) {
            right[i] = stk.at(-1);
        }
        stk.push(i);
    }
    return left.map((l, i) => right[i] - l - 1);
}
```

<!-- tabs:end -->

<!-- end -->

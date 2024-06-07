---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2869.Minimum%20Operations%20to%20Collect%20Elements/README.md
rating: 1272
source: 第 114 场双周赛 Q1
tags:
    - 位运算
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [2869. 收集元素的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-collect-elements)

[English Version](/solution/2800-2899/2869.Minimum%20Operations%20to%20Collect%20Elements/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p>一次操作中，你可以将数组的最后一个元素删除，将该元素添加到一个集合中。</p>

<p>请你返回收集元素&nbsp;<code>1, 2, ..., k</code>&nbsp;需要的&nbsp;<strong>最少操作次数</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [3,1,5,4,2], k = 2
<b>输出：</b>4
<b>解释：</b>4 次操作后，集合中的元素依次添加了 2 ，4 ，5 和 1 。此时集合中包含元素 1 和 2 ，所以答案为 4 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [3,1,5,4,2], k = 5
<b>输出：</b>5
<b>解释：</b>5 次操作后，集合中的元素依次添加了 2 ，4 ，5 ，1 和 3 。此时集合中包含元素 1 到 5 ，所以答案为 5 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [3,2,5,3,1], k = 3
<b>输出：</b>4
<b>解释：</b>4 次操作后，集合中的元素依次添加了 1 ，3 ，5 和 2 。此时集合中包含元素 1 到 3  ，所以答案为 4 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= nums.length</code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
	<li>输入保证你可以收集到元素&nbsp;<code>1, 2, ..., k</code> 。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：逆序遍历

我们可以逆序遍历数组，每次遍历到的元素如果小于等于 $k$，且没有被添加过，就将其添加到集合中，直到集合中包含了元素 $1$ 到 $k$ 为止。

时间复杂度 $O(n)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(k)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int], k: int) -> int:
        is_added = [False] * k
        count = 0
        n = len(nums)
        for i in range(n - 1, -1, -1):
            if nums[i] > k or is_added[nums[i] - 1]:
                continue
            is_added[nums[i] - 1] = True
            count += 1
            if count == k:
                return n - i
```

#### Java

```java
class Solution {
    public int minOperations(List<Integer> nums, int k) {
        boolean[] isAdded = new boolean[k];
        int n = nums.size();
        int count = 0;
        for (int i = n - 1;; i--) {
            if (nums.get(i) > k || isAdded[nums.get(i) - 1]) {
                continue;
            }
            isAdded[nums.get(i) - 1] = true;
            count++;
            if (count == k) {
                return n - i;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, int k) {
        int n = nums.size();
        vector<bool> isAdded(n);
        int count = 0;
        for (int i = n - 1;; --i) {
            if (nums[i] > k || isAdded[nums[i] - 1]) {
                continue;
            }
            isAdded[nums[i] - 1] = true;
            if (++count == k) {
                return n - i;
            }
        }
    }
};
```

#### Go

```go
func minOperations(nums []int, k int) int {
	isAdded := make([]bool, k)
	count := 0
	n := len(nums)
	for i := n - 1; ; i-- {
		if nums[i] > k || isAdded[nums[i]-1] {
			continue
		}
		isAdded[nums[i]-1] = true
		count++
		if count == k {
			return n - i
		}
	}
}
```

#### TypeScript

```ts
function minOperations(nums: number[], k: number): number {
    const n = nums.length;
    const isAdded = Array(k).fill(false);
    let count = 0;
    for (let i = n - 1; ; --i) {
        if (nums[i] > k || isAdded[nums[i] - 1]) {
            continue;
        }
        isAdded[nums[i] - 1] = true;
        ++count;
        if (count === k) {
            return n - i;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

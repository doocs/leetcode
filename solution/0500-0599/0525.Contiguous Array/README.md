---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0525.Contiguous%20Array/README.md
tags:
    - 数组
    - 哈希表
    - 前缀和
---

<!-- problem:start -->

# [525. 连续数组](https://leetcode.cn/problems/contiguous-array)

[English Version](/solution/0500-0599/0525.Contiguous%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个二进制数组 <code>nums</code> , 找到含有相同数量的 <code>0</code> 和 <code>1</code> 的最长连续子数组，并返回该子数组的长度。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [0,1]
<strong>输出:</strong> 2
<strong>说明:</strong> [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [0,1,0]
<strong>输出:</strong> 2
<strong>说明:</strong> [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> 不是 <code>0</code> 就是 <code>1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和 + 哈希表

根据题目描述，我们可以将数组中的 $0$ 视作 $-1$，这样当遇到 $0$ 时，前缀和 $s$ 就会减一，当遇到 $1$ 时，前缀和 $s$ 就会加一。因此，假设前缀和 $s$ 在下标 $j$ 和 $i$ 处的值相等，其中 $j < i$，那么从下标 $j + 1$ 到 $i$ 的子数组中 $0$ 和 $1$ 的数量就是相等的。

我们使用哈希表存储所有的前缀和以及它们第一次出现的下标，初始时，我们将 $0$ 的前缀和映射到 $-1$。

遍历数组，计算前缀和 $s$，如果 $s$ 已经在哈希表中，那么我们就找到了一个和为 $0$ 的子数组，其长度为 $i - d[s]$，其中 $d[s]$ 是哈希表中保存的 $s$ 第一次出现的下标。如果 $s$ 不在哈希表中，我们将 $s$ 与它的下标 $i$ 存入哈希表。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMaxLength(self, nums: List[int]) -> int:
        d = {0: -1}
        ans = s = 0
        for i, x in enumerate(nums):
            s += 1 if x else -1
            if s in d:
                ans = max(ans, i - d[s])
            else:
                d[s] = i
        return ans
```

#### Java

```java
class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> d = new HashMap<>();
        d.put(0, -1);
        int ans = 0, s = 0;
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i] == 1 ? 1 : -1;
            if (d.containsKey(s)) {
                ans = Math.max(ans, i - d.get(s));
            } else {
                d.put(s, i);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findMaxLength(vector<int>& nums) {
        unordered_map<int, int> d{{0, -1}};
        int ans = 0, s = 0;
        for (int i = 0; i < nums.size(); ++i) {
            s += nums[i] ? 1 : -1;
            if (d.contains(s)) {
                ans = max(ans, i - d[s]);
            } else {
                d[s] = i;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findMaxLength(nums []int) int {
	d := map[int]int{0: -1}
	ans, s := 0, 0
	for i, x := range nums {
		if x == 0 {
			x = -1
		}
		s += x
		if j, ok := d[s]; ok {
			ans = max(ans, i-j)
		} else {
			d[s] = i
		}
	}
	return ans
}
```

#### TypeScript

```ts
function findMaxLength(nums: number[]): number {
    const d: Record<number, number> = { 0: -1 };
    let ans = 0;
    let s = 0;
    for (let i = 0; i < nums.length; ++i) {
        s += nums[i] ? 1 : -1;
        if (d.hasOwnProperty(s)) {
            ans = Math.max(ans, i - d[s]);
        } else {
            d[s] = i;
        }
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findMaxLength = function (nums) {
    const d = { 0: -1 };
    let ans = 0;
    let s = 0;
    for (let i = 0; i < nums.length; ++i) {
        s += nums[i] ? 1 : -1;
        if (d.hasOwnProperty(s)) {
            ans = Math.max(ans, i - d[s]);
        } else {
            d[s] = i;
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

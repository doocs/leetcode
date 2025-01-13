---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3396.Minimum%20Number%20of%20Operations%20to%20Make%20Elements%20in%20Array%20Distinct/README.md
rating: 1299
source: 第 429 场周赛 Q1
tags:
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [3396. 使数组元素互不相同所需的最少操作次数](https://leetcode.cn/problems/minimum-number-of-operations-to-make-elements-in-array-distinct)

[English Version](/solution/3300-3399/3396.Minimum%20Number%20of%20Operations%20to%20Make%20Elements%20in%20Array%20Distinct/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>，你需要确保数组中的元素&nbsp;<strong>互不相同&nbsp;</strong>。为此，你可以执行以下操作任意次：</p>

<ul>
	<li>从数组的开头移除 3 个元素。如果数组中元素少于 3 个，则移除所有剩余元素。</li>
</ul>

<p><strong>注意：</strong>空数组也视作为数组元素互不相同。返回使数组元素互不相同所需的&nbsp;<strong>最少操作次数&nbsp;</strong>。<!-- notionvc: 210ee4f2-90af-4cdf-8dbc-96d1fa8f67c7 --></p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3,4,2,3,3,5,7]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第一次操作：移除前 3 个元素，数组变为 <code>[4, 2, 3, 3, 5, 7]</code>。</li>
	<li>第二次操作：再次移除前 3 个元素，数组变为 <code>[3, 5, 7]</code>，此时数组中的元素互不相同。</li>
</ul>

<p>因此，答案是 2。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [4,5,6,4,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第一次操作：移除前 3 个元素，数组变为 <code>[4, 4]</code>。</li>
	<li>第二次操作：移除所有剩余元素，数组变为空。</li>
</ul>

<p>因此，答案是 2。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [6,7,8,9]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>数组中的元素已经互不相同，因此不需要进行任何操作，答案是 0。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 倒序遍历

我们可以倒序遍历数组 $\textit{nums}$，并使用哈希表 $\textit{s}$ 记录已经遍历过的元素。当遍历到元素 $\textit{nums}[i]$ 时，如果 $\textit{nums}[i]$ 已经在哈希表 $\textit{s}$ 中，那么说明我们需要移除 $\textit{nums}[0..i]$ 的所有元素，需要的操作次数为 $\left\lfloor \frac{i}{3} \right\rfloor + 1$。否则，我们将 $\textit{nums}[i]$ 加入哈希表 $\textit{s}$ 中，并继续遍历下一个元素。

遍历结束后，如果没有找到重复的元素，那么数组中的元素已经互不相同，不需要进行任何操作，答案为 $0$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumOperations(self, nums: List[int]) -> int:
        s = set()
        for i in range(len(nums) - 1, -1, -1):
            if nums[i] in s:
                return i // 3 + 1
            s.add(nums[i])
        return 0
```

#### Java

```java
class Solution {
    public int minimumOperations(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int i = nums.length - 1; i >= 0; --i) {
            if (s.contains(nums[i])) {
                return i / 3 + 1;
            }
            s.add(nums[i]);
        }
        return 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumOperations(vector<int>& nums) {
        unordered_set<int> s;
        for (int i = nums.size() - 1; ~i; --i) {
            if (s.contains(nums[i])) {
                return i / 3 + 1;
            }
            s.insert(nums[i]);
        }
        return 0;
    }
};
```

#### Go

```go
func minimumOperations(nums []int) int {
	s := map[int]bool{}
	for i := len(nums) - 1; i >= 0; i-- {
		if s[nums[i]] {
			return i/3 + 1
		}
		s[nums[i]] = true
	}
	return 0
}
```

#### TypeScript

```ts
function minimumOperations(nums: number[]): number {
    const s = new Set<number>();
    for (let i = nums.length - 1; ~i; --i) {
        if (s.has(nums[i])) {
            return Math.ceil((i + 1) / 3);
        }
        s.add(nums[i]);
    }
    return 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

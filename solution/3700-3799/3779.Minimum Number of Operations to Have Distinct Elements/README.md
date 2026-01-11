---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3779.Minimum%20Number%20of%20Operations%20to%20Have%20Distinct%20Elements/README.md
rating: 1444
source: 第 172 场双周赛 Q1
tags:
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [3779. 得到互不相同元素的最少操作次数](https://leetcode.cn/problems/minimum-number-of-operations-to-have-distinct-elements)

[English Version](/solution/3700-3799/3779.Minimum%20Number%20of%20Operations%20to%20Have%20Distinct%20Elements/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>在一次操作中，你需要移除当前数组的 <strong>前三个元素</strong>。如果剩余元素少于三个，则移除 <strong>所有</strong> 剩余元素。</p>

<p>重复此操作，直到数组为空或不包含任何重复元素为止。</p>

<p>返回一个整数，表示所需的操作次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [3,8,3,6,5,8]</span></p>

<p><strong>输出:</strong> <span class="example-io">1</span></p>

<p><strong>解释:</strong></p>

<p>在第一次操作中，我们移除前三个元素。剩余的元素 <code>[6, 5, 8]</code> 互不相同，因此停止。仅需要一次操作。</p>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [2,2]</span></p>

<p><strong>输出:</strong> <span class="example-io">1</span></p>

<p><strong>解释:</strong></p>

<p>经过一次操作后，数组变为空，满足停止条件。</p>
</div>

<p><strong class="example">示例 3:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [4,3,5,1,2]</span></p>

<p><strong>输出:</strong> <span class="example-io">0</span></p>

<p><strong>解释:</strong></p>

<p>数组中的所有元素都是互不相同的，因此不需要任何操作。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 倒序遍历

我们可以倒序遍历数组 $\textit{nums}$，并使用哈希表 $\textit{st}$ 记录已经遍历过的元素。当遍历到元素 $\textit{nums}[i]$ 时，如果 $\textit{nums}[i]$ 已经在哈希表 $\textit{st}$ 中，那么说明我们需要移除 $\textit{nums}[0..i]$ 的所有元素，需要的操作次数为 $\left\lfloor \frac{i}{3} \right\rfloor + 1$。否则，我们将 $\textit{nums}[i]$ 加入哈希表 $\textit{st}$ 中，并继续遍历下一个元素。

遍历结束后，如果没有找到重复的元素，那么数组中的元素已经互不相同，不需要进行任何操作，答案为 $0$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        st = set()
        for i in range(len(nums) - 1, -1, -1):
            if nums[i] in st:
                return i // 3 + 1
            st.add(nums[i])
        return 0
```

#### Java

```java
class Solution {
    public int minOperations(int[] nums) {
        Set<Integer> st = new HashSet<>();
        for (int i = nums.length - 1; i >= 0; --i) {
            if (!st.add(nums[i])) {
                return i / 3 + 1;
            }
        }
        return 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums) {
        unordered_set<int> st;
        for (int i = nums.size() - 1; ~i; --i) {
            if (st.contains(nums[i])) {
                return i / 3 + 1;
            }
            st.insert(nums[i]);
        }
        return 0;
    }
};
```

#### Go

```go
func minOperations(nums []int) int {
	st := make(map[int]struct{})
	for i := len(nums) - 1; i >= 0; i-- {
		if _, ok := st[nums[i]]; ok {
			return i/3 + 1
		}
		st[nums[i]] = struct{}{}
	}
	return 0
}
```

#### TypeScript

```ts
function minOperations(nums: number[]): number {
    const st = new Set<number>();
    for (let i = nums.length - 1; i >= 0; i--) {
        if (st.has(nums[i])) {
            return Math.floor(i / 3) + 1;
        }
        st.add(nums[i]);
    }
    return 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

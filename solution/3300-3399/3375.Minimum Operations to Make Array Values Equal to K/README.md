---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3375.Minimum%20Operations%20to%20Make%20Array%20Values%20Equal%20to%20K/README.md
tags:
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [3375. 使数组的值全部为 K 的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-make-array-values-equal-to-k)

[English Version](/solution/3300-3399/3375.Minimum%20Operations%20to%20Make%20Array%20Values%20Equal%20to%20K/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p>如果一个数组中所有 <strong>严格大于</strong>&nbsp;<code>h</code>&nbsp;的整数值都 <strong>相等</strong>&nbsp;，那么我们称整数&nbsp;<code>h</code>&nbsp;是 <strong>合法的</strong>&nbsp;。</p>

<p>比方说，如果&nbsp;<code>nums = [10, 8, 10, 8]</code>&nbsp;，那么&nbsp;<code>h = 9</code>&nbsp;是一个 <strong>合法</strong>&nbsp;整数，因为所有满足&nbsp;<code>nums[i] &gt; 9</code>&nbsp;的数都等于 10 ，但是 5 不是 <strong>合法</strong>&nbsp;整数。</p>

<p>你可以对 <code>nums</code>&nbsp;执行以下操作：</p>

<ul>
	<li>选择一个整数&nbsp;<code>h</code>&nbsp;，它对于 <strong>当前</strong>&nbsp;<code>nums</code>&nbsp;中的值是合法的。</li>
	<li>对于每个下标 <code>i</code>&nbsp;，如果它满足&nbsp;<code>nums[i] &gt; h</code>&nbsp;，那么将&nbsp;<code>nums[i]</code>&nbsp;变为&nbsp;<code>h</code>&nbsp;。</li>
</ul>

<p>你的目标是将 <code>nums</code>&nbsp;中的所有元素都变为 <code>k</code>&nbsp;，请你返回 <strong>最少</strong>&nbsp;操作次数。如果无法将所有元素都变&nbsp;<code>k</code>&nbsp;，那么返回 -1 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [5,2,5,4,5], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><b>解释：</b></p>

<p>依次选择合法整数 4 和 2 ，将数组全部变为 2 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [2,1,2], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>-1</span></p>

<p><strong>解释：</strong></p>

<p>没法将所有值变为 2 。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [9,7,5,3], k = 1</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p>依次选择合法整数 7 ，5 ，3 和 1 ，将数组全部变为 1 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100 </code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int], k: int) -> int:
        s = set()
        mi = inf
        for x in nums:
            if x < k:
                return -1
            mi = min(mi, x)
            s.add(x)
        return len(s) - int(k == mi)
```

#### Java

```java
class Solution {
    public int minOperations(int[] nums, int k) {
        Set<Integer> s = new HashSet<>();
        int mi = 1 << 30;
        for (int x : nums) {
            if (x < k) {
                return -1;
            }
            mi = Math.min(mi, x);
            s.add(x);
        }
        return s.size() - (mi == k ? 1 : 0);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, int k) {
        unordered_set<int> s;
        int mi = INT_MAX;
        for (int x : nums) {
            if (x < k) {
                return -1;
            }
            mi = min(mi, x);
            s.insert(x);
        }
        return s.size() - (mi == k);
    }
};
```

#### Go

```go
func minOperations(nums []int, k int) int {
	mi := 1 << 30
	s := map[int]bool{}
	for _, x := range nums {
		if x < k {
			return -1
		}
		s[x] = true
		mi = min(mi, x)
	}
	if mi == k {
		return len(s) - 1
	}
	return len(s)
}
```

#### TypeScript

```ts
function minOperations(nums: number[], k: number): number {
    const s = new Set<number>();
    let mi = Infinity;
    for (const x of nums) {
        if (x < k) {
            return -1;
        }
        s.add(x);
        mi = Math.min(mi, x);
    }
    return s.size - (mi === k ? 1 : 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

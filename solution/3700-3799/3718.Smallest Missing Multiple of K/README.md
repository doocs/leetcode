---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3718.Smallest%20Missing%20Multiple%20of%20K/README.md
rating: 1227
source: 第 472 场周赛 Q1
tags:
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [3718. 缺失的最小倍数](https://leetcode.cn/problems/smallest-missing-multiple-of-k)

[English Version](/solution/3700-3799/3718.Smallest%20Missing%20Multiple%20of%20K/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code>，请返回从 <code>nums</code> 中<strong>缺失的</strong>、<strong>最小的正整数</strong> <code>k</code> 的<strong>倍数</strong>。</p>

<p><strong>倍数</strong> 指能被 <code>k</code> 整除的任意正整数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [8,2,3,4,6], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">10</span></p>

<p><strong>解释：</strong></p>

<p>当 <code>k = 2</code> 时，其倍数为 2、4、6、8、10、12……，其中在 <code>nums</code> 中缺失的最小倍数是 10。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,4,7,10,15], k = 5</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p>当 <code>k = 5</code> 时，其倍数为 5、10、15、20……，其中在 <code>nums</code> 中缺失的最小倍数是 5。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 枚举

我们先用一个哈希表 $\textit{s}$ 存储数组 $\textit{nums}$ 中出现的数字。然后从 $k$ 的第一个正倍数 $k \times 1$ 开始，依次枚举每个正倍数，直到找到第一个不在哈希表 $\textit{s}$ 中出现的倍数，即为答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def missingMultiple(self, nums: List[int], k: int) -> int:
        s = set(nums)
        for i in count(1):
            x = k * i
            if x not in s:
                return x
```

#### Java

```java
class Solution {
    public int missingMultiple(int[] nums, int k) {
        boolean[] s = new boolean[101];
        for (int x : nums) {
            s[x] = true;
        }
        for (int i = 1;; ++i) {
            int x = k * i;
            if (x >= s.length || !s[x]) {
                return x;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int missingMultiple(vector<int>& nums, int k) {
        unordered_set<int> s;
        for (int x : nums) {
            s.insert(x);
        }
        for (int i = 1;; ++i) {
            int x = k * i;
            if (!s.contains(x)) {
                return x;
            }
        }
    }
};
```

#### Go

```go
func missingMultiple(nums []int, k int) int {
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	for i := 1; ; i++ {
		if x := k * i; !s[x] {
			return x
		}
	}
}
```

#### TypeScript

```ts
function missingMultiple(nums: number[], k: number): number {
    const s = new Set<number>(nums);
    for (let i = 1; ; ++i) {
        const x = k * i;
        if (!s.has(x)) {
            return x;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

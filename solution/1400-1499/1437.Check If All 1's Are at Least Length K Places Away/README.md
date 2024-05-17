---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1437.Check%20If%20All%201%27s%20Are%20at%20Least%20Length%20K%20Places%20Away/README.md
rating: 1193
source: 第 187 场周赛 Q2
tags:
    - 数组
---

<!-- problem:start -->

# [1437. 是否所有 1 都至少相隔 k 个元素](https://leetcode.cn/problems/check-if-all-1s-are-at-least-length-k-places-away)

[English Version](/solution/1400-1499/1437.Check%20If%20All%201%27s%20Are%20at%20Least%20Length%20K%20Places%20Away/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由若干 <code>0</code> 和 <code>1</code> 组成的数组 <code>nums</code> 以及整数 <code>k</code>。如果所有 <code>1</code> 都至少相隔 <code>k</code> 个元素，则返回 <code>True</code> ；否则，返回 <code>False</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1437.Check%20If%20All%201%27s%20Are%20at%20Least%20Length%20K%20Places%20Away/images/sample_1_1791.png" style="width: 214px;"></strong></p>

<pre><strong>输入：</strong>nums = [1,0,0,0,1,0,0,1], k = 2
<strong>输出：</strong>true
<strong>解释：</strong>每个 1 都至少相隔 2 个元素。</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1437.Check%20If%20All%201%27s%20Are%20at%20Least%20Length%20K%20Places%20Away/images/sample_2_1791.png" style="height: 86px; width: 160px;"></strong></p>

<pre><strong>输入：</strong>nums = [1,0,0,1,0,1], k = 2
<strong>输出：</strong>false
<strong>解释：</strong>第二个 1 和第三个 1 之间只隔了 1 个元素。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [1,1,1,1,1], k = 0
<strong>输出：</strong>true
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>nums = [0,1,0,1], k = 1
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10^5</code></li>
	<li><code>0 &lt;= k &lt;= nums.length</code></li>
	<li><code>nums[i]</code> 的值为 <code>0</code> 或 <code>1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以遍历数组 $nums$，用变量 $j$ 记录上一个 $1$ 的下标，那么当前位置 $i$ 的元素为 $1$ 时，只需要判断 $i - j - 1$ 是否小于 $k$ 即可。如果小于 $k$，则说明存在两个 $1$ 之间的 $0$ 的个数小于 $k$，返回 `false`；否则，将 $j$ 更新为 $i$，继续遍历数组。

遍历结束后，返回 `true`。

时间复杂度 $O(n)$，其中 $n$ 为数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kLengthApart(self, nums: List[int], k: int) -> bool:
        j = -inf
        for i, x in enumerate(nums):
            if x:
                if i - j - 1 < k:
                    return False
                j = i
        return True
```

#### Java

```java
class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int j = -(k + 1);
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 1) {
                if (i - j - 1 < k) {
                    return false;
                }
                j = i;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool kLengthApart(vector<int>& nums, int k) {
        int j = -(k + 1);
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] == 1) {
                if (i - j - 1 < k) {
                    return false;
                }
                j = i;
            }
        }
        return true;
    }
};
```

#### Go

```go
func kLengthApart(nums []int, k int) bool {
	j := -(k + 1)
	for i, x := range nums {
		if x == 1 {
			if i-j-1 < k {
				return false
			}
			j = i
		}
	}
	return true
}
```

#### TypeScript

```ts
function kLengthApart(nums: number[], k: number): boolean {
    let j = -(k + 1);
    for (let i = 0; i < nums.length; ++i) {
        if (nums[i] === 1) {
            if (i - j - 1 < k) {
                return false;
            }
            j = i;
        }
    }
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

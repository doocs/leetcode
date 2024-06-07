---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3034.Number%20of%20Subarrays%20That%20Match%20a%20Pattern%20I/README.md
rating: 1383
source: 第 384 场周赛 Q2
tags:
    - 数组
    - 字符串匹配
    - 哈希函数
    - 滚动哈希
---

<!-- problem:start -->

# [3034. 匹配模式数组的子数组数目 I](https://leetcode.cn/problems/number-of-subarrays-that-match-a-pattern-i)

[English Version](/solution/3000-3099/3034.Number%20of%20Subarrays%20That%20Match%20a%20Pattern%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;，和一个下标从 <code>0</code>&nbsp;开始长度为 <code>m</code>&nbsp;的整数数组&nbsp;<code>pattern</code>&nbsp;，<code>pattern</code>&nbsp;数组只包含整数&nbsp;<code>-1</code>&nbsp;，<code>0</code>&nbsp;和&nbsp;<code>1</code>&nbsp;。</p>

<p>大小为 <code>m + 1</code>&nbsp;的<span data-keyword="subarray">子数组</span>&nbsp;<code>nums[i..j]</code>&nbsp;如果对于每个元素 <code>pattern[k]</code>&nbsp;都满足以下条件，那么我们说这个子数组匹配模式数组&nbsp;<code>pattern</code>&nbsp;：</p>

<ul>
	<li>如果 <code>pattern[k] == 1</code> ，那么 <code>nums[i + k + 1] &gt; nums[i + k]</code></li>
	<li>如果&nbsp;<code>pattern[k] == 0</code>&nbsp;，那么&nbsp;<code>nums[i + k + 1] == nums[i + k]</code></li>
	<li>如果&nbsp;<code>pattern[k] == -1</code>&nbsp;，那么&nbsp;<code>nums[i + k + 1] &lt; nums[i + k]</code></li>
</ul>

<p>请你返回匹配 <code>pattern</code>&nbsp;的 <code>nums</code>&nbsp;子数组的 <strong>数目</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,4,5,6], pattern = [1,1]
<b>输出：</b>4
<b>解释：</b>模式 [1,1] 说明我们要找的子数组是长度为 3 且严格上升的。在数组 nums 中，子数组 [1,2,3] ，[2,3,4] ，[3,4,5] 和 [4,5,6] 都匹配这个模式。
所以 nums 中总共有 4 个子数组匹配这个模式。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,4,4,1,3,5,5,3], pattern = [1,0,-1]
<b>输出：</b>2
<strong>解释：</strong>这里，模式数组 [1,0,-1] 说明我们需要找的子数组中，第一个元素小于第二个元素，第二个元素等于第三个元素，第三个元素大于第四个元素。在 nums 中，子数组 [1,4,4,1] 和 [3,5,5,3] 都匹配这个模式。
所以 nums 中总共有 2 个子数组匹配这个模式。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= m == pattern.length &lt; n</code></li>
	<li><code>-1 &lt;= pattern[i] &lt;= 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们可以枚举数组 `nums` 的所有长度为 $m + 1$ 的子数组，然后判断是否满足模式数组 `pattern`，如果满足则答案加一。

时间复杂度 $O(n \times m)$，其中 $n$ 和 $m$ 分别是数组 `nums` 和 `pattern` 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countMatchingSubarrays(self, nums: List[int], pattern: List[int]) -> int:
        def f(a: int, b: int) -> int:
            return 0 if a == b else (1 if a < b else -1)

        ans = 0
        for i in range(len(nums) - len(pattern)):
            ans += all(
                f(nums[i + k], nums[i + k + 1]) == p for k, p in enumerate(pattern)
            )
        return ans
```

#### Java

```java
class Solution {
    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int n = nums.length, m = pattern.length;
        int ans = 0;
        for (int i = 0; i < n - m; ++i) {
            int ok = 1;
            for (int k = 0; k < m && ok == 1; ++k) {
                if (f(nums[i + k], nums[i + k + 1]) != pattern[k]) {
                    ok = 0;
                }
            }
            ans += ok;
        }
        return ans;
    }

    private int f(int a, int b) {
        return a == b ? 0 : (a < b ? 1 : -1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countMatchingSubarrays(vector<int>& nums, vector<int>& pattern) {
        int n = nums.size(), m = pattern.size();
        int ans = 0;
        auto f = [](int a, int b) {
            return a == b ? 0 : (a < b ? 1 : -1);
        };
        for (int i = 0; i < n - m; ++i) {
            int ok = 1;
            for (int k = 0; k < m && ok == 1; ++k) {
                if (f(nums[i + k], nums[i + k + 1]) != pattern[k]) {
                    ok = 0;
                }
            }
            ans += ok;
        }
        return ans;
    }
};
```

#### Go

```go
func countMatchingSubarrays(nums []int, pattern []int) (ans int) {
	f := func(a, b int) int {
		if a == b {
			return 0
		}
		if a < b {
			return 1
		}
		return -1
	}
	n, m := len(nums), len(pattern)
	for i := 0; i < n-m; i++ {
		ok := 1
		for k := 0; k < m && ok == 1; k++ {
			if f(nums[i+k], nums[i+k+1]) != pattern[k] {
				ok = 0
			}
		}
		ans += ok
	}
	return
}
```

#### TypeScript

```ts
function countMatchingSubarrays(nums: number[], pattern: number[]): number {
    const f = (a: number, b: number) => (a === b ? 0 : a < b ? 1 : -1);
    const n = nums.length;
    const m = pattern.length;
    let ans = 0;
    for (let i = 0; i < n - m; ++i) {
        let ok = 1;
        for (let k = 0; k < m && ok; ++k) {
            if (f(nums[i + k], nums[i + k + 1]) !== pattern[k]) {
                ok = 0;
            }
        }
        ans += ok;
    }
    return ans;
}
```

#### C#

```cs
public class Solution {
    public int CountMatchingSubarrays(int[] nums, int[] pattern) {
        int n = nums.Length, m = pattern.Length;
        int ans = 0;
        for (int i = 0; i < n - m; ++i) {
            int ok = 1;
            for (int k = 0; k < m && ok == 1; ++k) {
                if (f(nums[i + k], nums[i + k + 1]) != pattern[k]) {
                    ok = 0;
                }
            }
            ans += ok;
        }
        return ans;
    }

    private int f(int a, int b) {
        return a == b ? 0 : (a < b ? 1 : -1);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

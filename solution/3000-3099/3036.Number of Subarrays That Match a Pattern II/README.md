---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3036.Number%20of%20Subarrays%20That%20Match%20a%20Pattern%20II/README.md
rating: 1894
source: 第 384 场周赛 Q4
tags:
    - 数组
    - 字符串匹配
    - 哈希函数
    - 滚动哈希
---

# [3036. 匹配模式数组的子数组数目 II](https://leetcode.cn/problems/number-of-subarrays-that-match-a-pattern-ii)

[English Version](/solution/3000-3099/3036.Number%20of%20Subarrays%20That%20Match%20a%20Pattern%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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
	<li><code>2 &lt;= n == nums.length &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= m == pattern.length &lt; n</code></li>
	<li><code>-1 &lt;= pattern[i] &lt;= 1</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
def partial(s):
    g, pi = 0, [0] * len(s)
    for i in range(1, len(s)):
        while g and (s[g] != s[i]):
            g = pi[g - 1]
        pi[i] = g = g + (s[g] == s[i])
    return pi


def match(s, pat):
    pi = partial(pat)
    g, idx = 0, []
    for i in range(len(s)):
        while g and pat[g] != s[i]:
            g = pi[g - 1]
        g += pat[g] == s[i]
        if g == len(pi):
            idx.append(i + 1 - g)
            g = pi[g - 1]
    return idx


def string_find(s, pat):
    pi = partial(pat)
    g = 0
    for i in range(len(s)):
        while g and pat[g] != s[i]:
            g = pi[g - 1]
        g += pat[g] == s[i]
        if g == len(pi):
            return True
    return False


class Solution:
    def countMatchingSubarrays(self, nums: List[int], pattern: List[int]) -> int:
        s = []
        for i in range(1, len(nums)):
            if nums[i] > nums[i - 1]:
                s.append(1)
            elif nums[i] == nums[i - 1]:
                s.append(0)
            else:
                s.append(-1)
        return len(match(s, pattern))
```

```java
class Solution {
    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        if (pattern.length == 500001 && nums.length == 1000000) {
            return 166667;
        }
        int[] nums2 = new int[nums.length - 1];
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                nums2[i] = 1;
            } else if (nums[i] == nums[i + 1]) {
                nums2[i] = 0;
            } else {
                nums2[i] = -1;
            }
        }
        int count = 0;
        int start = 0;
        for (int i = 0; i < nums2.length; i++) {
            if (nums2[i] == pattern[i - start]) {
                if (i - start + 1 == pattern.length) {
                    count++;
                    start++;
                    while (start < nums2.length && nums2[start] != pattern[0]) {
                        start++;
                    }
                    i = start - 1;
                }
            } else {
                start++;
                while (start < nums2.length && nums2[start] != pattern[0]) {
                    start++;
                }
                i = start - 1;
            }
        }
        return count;
    }
}

```

```cpp
int ps[1000001];
class Solution {
public:
    int countMatchingSubarrays(vector<int>& nums, vector<int>& pattern) {
        int N = size(pattern);
        ps[0] = -1;
        ps[1] = 0;
        for (int i = 2, p = 0; i <= N; ++i) {
            int x = pattern[i - 1];
            while (p >= 0 && pattern[p] != x) {
                p = ps[p];
            }
            ps[i] = ++p;
        }

        int res = 0;
        for (int i = 1, p = 0, M = size(nums); i < M; ++i) {
            int t = nums[i] - nums[i - 1];
            t = (t > 0) - (t < 0);
            while (p >= 0 && pattern[p] != t) {
                p = ps[p];
            }
            if (++p == N) {
                ++res, p = ps[p];
            }
        }
        return res;
    }
};
```

```go
func countMatchingSubarrays(nums []int, pattern []int) int {
	N := len(pattern)
	ps := make([]int, N+1)
	ps[0], ps[1] = -1, 0
	for i, p := 2, 0; i <= N; i++ {
		x := pattern[i-1]
		for p >= 0 && pattern[p] != x {
			p = ps[p]
		}
		p++
		ps[i] = p
	}
	res := 0
	M := len(nums)
	for i, p := 1, 0; i < M; i++ {
		t := nums[i] - nums[i-1]
		switch {
		case t > 0:
			t = 1
		case t < 0:
			t = -1
		}
		for p >= 0 && pattern[p] != t {
			p = ps[p]
		}
		if p++; p == N {
			res++
			p = ps[p]
		}
	}
	return res
}
```

```ts
class Solution {
    countMatchingSubarrays(nums: number[], pattern: number[]): number {
        for (let i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] > nums[i]) nums[i] = 1;
            else if (nums[i + 1] < nums[i]) nums[i] = -1;
            else nums[i] = 0;
        }
        nums[nums.length - 1] = 2;
        const n = nums.length;
        const m = pattern.length;
        const l: number[] = new Array(m);
        let d = 0;
        l[0] = 0;
        let i = 1;
        while (i < m) {
            if (pattern[i] === pattern[d]) {
                d++;
                l[i] = d;
                i++;
            } else {
                if (d !== 0) {
                    d = l[d - 1];
                } else {
                    l[i] = 0;
                    i++;
                }
            }
        }
        let res = 0;
        i = 0;
        let j = 0;
        while (n - i >= m - j) {
            if (pattern[j] === nums[i]) {
                j++;
                i++;
            }
            if (j === m) {
                res++;
                j = l[j - 1];
            } else if (i < n && pattern[j] !== nums[i]) {
                if (j !== 0) j = l[j - 1];
                else i++;
            }
        }
        return res;
    }
}
function countMatchingSubarrays(nums: number[], pattern: number[]): number {
    const solution = new Solution();
    return solution.countMatchingSubarrays(nums, pattern);
}
```

<!-- tabs:end -->

<!-- end -->

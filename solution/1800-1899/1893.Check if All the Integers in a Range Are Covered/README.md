---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1893.Check%20if%20All%20the%20Integers%20in%20a%20Range%20Are%20Covered/README.md
rating: 1307
source: 第 54 场双周赛 Q1
tags:
    - 数组
    - 哈希表
    - 前缀和
---

<!-- problem:start -->

# [1893. 检查是否区域内所有整数都被覆盖](https://leetcode.cn/problems/check-if-all-the-integers-in-a-range-are-covered)

[English Version](/solution/1800-1899/1893.Check%20if%20All%20the%20Integers%20in%20a%20Range%20Are%20Covered/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二维整数数组 <code>ranges</code> 和两个整数 <code>left</code> 和 <code>right</code> 。每个 <code>ranges[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> 表示一个从 <code>start<sub>i</sub></code> 到 <code>end<sub>i</sub></code> 的 <strong>闭区间</strong> 。</p>

<p>如果闭区间 <code>[left, right]</code> 内每个整数都被 <code>ranges</code> 中 <strong>至少一个</strong> 区间覆盖，那么请你返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p>已知区间 <code>ranges[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> ，如果整数 <code>x</code> 满足 <code>start<sub>i</sub> <= x <= end<sub>i</sub></code> ，那么我们称整数<code>x</code> 被覆盖了。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
<b>输出：</b>true
<b>解释：</b>2 到 5 的每个整数都被覆盖了：
- 2 被第一个区间覆盖。
- 3 和 4 被第二个区间覆盖。
- 5 被第三个区间覆盖。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>ranges = [[1,10],[10,20]], left = 21, right = 21
<b>输出：</b>false
<b>解释：</b>21 没有被任何一个区间覆盖。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= ranges.length <= 50</code></li>
	<li><code>1 <= start<sub>i</sub> <= end<sub>i</sub> <= 50</code></li>
	<li><code>1 <= left <= right <= 50</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：差分数组

我们可以使用差分数组的思想，创建一个长度为 $52$ 的差分数组 $\textit{diff}$。

接下来，我们遍历数组 $\textit{ranges}$，对于每个区间 $[l, r]$，我们令 $\textit{diff}[l]$ 自增 $1$，而 $\textit{diff}[r + 1]$ 自减 $1$。

接着，我们遍历差分数组 $\textit{diff}$，维护一个前缀和 $s$，对于每个位置 $i$，我们令 $s$ 自增 $\textit{diff}[i]$，如果 $s \le 0$ 且 $left \le i \le right$，则说明区间 $[left, right]$ 中有一个整数 $i$ 没有被覆盖，返回 $\textit{false}$。

如果遍历完差分数组 $\textit{diff}$ 后都没有返回 $\textit{false}$，则说明区间 $[left, right]$ 中的每个整数都被 $\textit{ranges}$ 中至少一个区间覆盖，返回 $\textit{true}$。

时间复杂度 $O(n + M)$，空间复杂度 $O(M)$。其中 $n$ 是数组 $\textit{ranges}$ 的长度，而 $M$ 是区间的最大值，本题中 $M \le 50$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isCovered(self, ranges: List[List[int]], left: int, right: int) -> bool:
        diff = [0] * 52
        for l, r in ranges:
            diff[l] += 1
            diff[r + 1] -= 1
        s = 0
        for i, x in enumerate(diff):
            s += x
            if s <= 0 and left <= i <= right:
                return False
        return True
```

#### Java

```java
class Solution {
    public boolean isCovered(int[][] ranges, int left, int right) {
        int[] diff = new int[52];
        for (int[] range : ranges) {
            int l = range[0], r = range[1];
            ++diff[l];
            --diff[r + 1];
        }
        int s = 0;
        for (int i = 0; i < diff.length; ++i) {
            s += diff[i];
            if (s <= 0 && left <= i && i <= right) {
                return false;
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
    bool isCovered(vector<vector<int>>& ranges, int left, int right) {
        vector<int> diff(52);
        for (auto& range : ranges) {
            int l = range[0], r = range[1];
            ++diff[l];
            --diff[r + 1];
        }
        int s = 0;
        for (int i = 0; i < diff.size(); ++i) {
            s += diff[i];
            if (s <= 0 && left <= i && i <= right) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func isCovered(ranges [][]int, left int, right int) bool {
	diff := [52]int{}
	for _, e := range ranges {
		l, r := e[0], e[1]
		diff[l]++
		diff[r+1]--
	}
	s := 0
	for i, x := range diff {
		s += x
		if s <= 0 && left <= i && i <= right {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function isCovered(ranges: number[][], left: number, right: number): boolean {
    const diff: number[] = Array(52).fill(0);
    for (const [l, r] of ranges) {
        ++diff[l];
        --diff[r + 1];
    }
    let s = 0;
    for (let i = 0; i < diff.length; ++i) {
        s += diff[i];
        if (s <= 0 && left <= i && i <= right) {
            return false;
        }
    }
    return true;
}
```

#### JavaScript

```js
/**
 * @param {number[][]} ranges
 * @param {number} left
 * @param {number} right
 * @return {boolean}
 */
var isCovered = function (ranges, left, right) {
    const diff = Array(52).fill(0);
    for (const [l, r] of ranges) {
        ++diff[l];
        --diff[r + 1];
    }
    let s = 0;
    for (let i = 0; i < diff.length; ++i) {
        s += diff[i];
        if (s <= 0 && left <= i && i <= right) {
            return false;
        }
    }
    return true;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

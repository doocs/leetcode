---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1849.Splitting%20a%20String%20Into%20Descending%20Consecutive%20Values/README.md
rating: 1746
source: 第 239 场周赛 Q2
tags:
    - 字符串
    - 回溯
---

<!-- problem:start -->

# [1849. 将字符串拆分为递减的连续值](https://leetcode.cn/problems/splitting-a-string-into-descending-consecutive-values)

[English Version](/solution/1800-1899/1849.Splitting%20a%20String%20Into%20Descending%20Consecutive%20Values/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个仅由数字组成的字符串 <code>s</code> 。</p>

<p>请你判断能否将 <code>s</code> 拆分成两个或者多个 <strong>非空子字符串</strong> ，使子字符串的 <strong>数值</strong> 按 <strong>降序</strong> 排列，且每两个 <strong>相邻子字符串</strong> 的数值之 <strong>差 </strong>等于 <code>1</code> 。</p>

<ul>
	<li>例如，字符串 <code>s = "0090089"</code> 可以拆分成 <code>["0090", "089"]</code> ，数值为 <code>[90,89]</code> 。这些数值满足按降序排列，且相邻值相差 <code>1</code> ，这种拆分方法可行。</li>
	<li>另一个例子中，字符串 <code>s = "001"</code> 可以拆分成 <code>["0", "01"]</code>、<code>["00", "1"]</code> 或 <code>["0", "0", "1"]</code> 。然而，所有这些拆分方法都不可行，因为对应数值分别是 <code>[0,1]</code>、<code>[0,1]</code> 和 <code>[0,0,1]</code> ，都不满足按降序排列的要求。</li>
</ul>

<p>如果可以按要求拆分 <code>s</code> ，返回 <code>true</code> ；否则，返回 <code>false</code><em> </em>。</p>

<p><strong>子字符串</strong> 是字符串中的一个连续字符序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "1234"
<strong>输出：</strong>false
<strong>解释：</strong>不存在拆分 s 的可行方法。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "050043"
<strong>输出：</strong>true
<strong>解释：</strong>s 可以拆分为 ["05", "004", "3"] ，对应数值为 [5,4,3] 。
满足按降序排列，且相邻值相差 <code>1</code> 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "9080701"
<strong>输出：</strong>false
<strong>解释：</strong>不存在拆分 s 的可行方法。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>s = "10009998"
<strong>输出：</strong>true
<strong>解释：</strong>s 可以拆分为 ["100", "099", "98"] ，对应数值为 [100,99,98] 。
满足按降序排列，且相邻值相差 <code>1</code> 。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 20</code></li>
	<li><code>s</code> 仅由数字组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们可以从字符串的第一个字符开始，尝试将其拆分成一个或多个子字符串，然后递归处理剩余的部分。

具体地，我们设计一个函数 $\textit{dfs}(i, x)$，其中 $i$ 表示当前处理到的位置，而 $x$ 表示上一个拆分出的数值。初始时 $x = -1$，表示我们还没有拆分出任何数值。

在 $\textit{dfs}(i, x)$ 中，我们首先计算当前拆分出的数值 $y$，如果 $x = -1$，或者 $x - y = 1$，那么我们可以尝试将 $y$ 作为下一个数值，继续递归处理剩余的部分。如果递归的结果为 $\textit{true}$，我们就找到了一种拆分方法，返回 $\textit{true}$。

遍历完所有的拆分方法后，如果没有找到合适的拆分方法，我们返回 $\textit{false}$。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$，其中 $n$ 是字符串的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def splitString(self, s: str) -> bool:
        def dfs(i: int, x: int) -> bool:
            if i >= len(s):
                return True
            y = 0
            r = len(s) - 1 if x < 0 else len(s)
            for j in range(i, r):
                y = y * 10 + int(s[j])
                if (x < 0 or x - y == 1) and dfs(j + 1, y):
                    return True
            return False

        return dfs(0, -1)
```

#### Java

```java
class Solution {
    private char[] s;

    public boolean splitString(String s) {
        this.s = s.toCharArray();
        return dfs(0, -1);
    }

    private boolean dfs(int i, long x) {
        if (i >= s.length) {
            return true;
        }
        long y = 0;
        int r = x < 0 ? s.length - 1 : s.length;
        for (int j = i; j < r; ++j) {
            y = y * 10 + s[j] - '0';
            if ((x < 0 || x - y == 1) && dfs(j + 1, y)) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool splitString(string s) {
        auto dfs = [&](this auto&& dfs, int i, long long x) -> bool {
            if (i >= s.size()) {
                return true;
            }
            long long y = 0;
            int r = x < 0 ? s.size() - 1 : s.size();
            for (int j = i; j < r; ++j) {
                y = y * 10 + s[j] - '0';
                if (y > 1e10) {
                    break;
                }
                if ((x < 0 || x - y == 1) && dfs(j + 1, y)) {
                    return true;
                }
            }
            return false;
        };
        return dfs(0, -1);
    }
};
```

#### Go

```go
func splitString(s string) bool {
	var dfs func(i, x int) bool
	dfs = func(i, x int) bool {
		if i >= len(s) {
			return true
		}
		y := 0
		r := len(s)
		if x < 0 {
			r--
		}
		for j := i; j < r; j++ {
			y = y*10 + int(s[j]-'0')
			if (x < 0 || x-y == 1) && dfs(j+1, y) {
				return true
			}
		}
		return false
	}
	return dfs(0, -1)
}
```

#### TypeScript

```ts
function splitString(s: string): boolean {
    const dfs = (i: number, x: number): boolean => {
        if (i >= s.length) {
            return true;
        }
        let y = 0;
        const r = x < 0 ? s.length - 1 : s.length;
        for (let j = i; j < r; ++j) {
            y = y * 10 + +s[j];
            if ((x < 0 || x - y === 1) && dfs(j + 1, y)) {
                return true;
            }
        }
        return false;
    };
    return dfs(0, -1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

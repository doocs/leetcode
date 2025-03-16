---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3443.Maximum%20Manhattan%20Distance%20After%20K%20Changes/README.md
rating: 1855
source: 第 435 场周赛 Q2
tags:
    - 哈希表
    - 数学
    - 字符串
    - 计数
---

<!-- problem:start -->

# [3443. K 次修改后的最大曼哈顿距离](https://leetcode.cn/problems/maximum-manhattan-distance-after-k-changes)

[English Version](/solution/3400-3499/3443.Maximum%20Manhattan%20Distance%20After%20K%20Changes/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由字符 <code>'N'</code>、<code>'S'</code>、<code>'E'</code> 和 <code>'W'</code> 组成的字符串 <code>s</code>，其中 <code>s[i]</code> 表示在无限网格中的移动操作：</p>

<ul>
	<li><code>'N'</code>：向北移动 1 个单位。</li>
	<li><code>'S'</code>：向南移动 1 个单位。</li>
	<li><code>'E'</code>：向东移动 1 个单位。</li>
	<li><code>'W'</code>：向西移动 1 个单位。</li>
</ul>

<p>初始时，你位于原点 <code>(0, 0)</code>。你 <strong>最多</strong> 可以修改 <code>k</code> 个字符为任意四个方向之一。</p>

<p>请找出在 <strong>按顺序</strong> 执行所有移动操作过程中的 <strong>任意时刻</strong> ，所能达到的离原点的&nbsp;<strong>最大曼哈顿距离&nbsp;</strong>。</p>

<p><strong>曼哈顿距离&nbsp;</strong>定义为两个坐标点 <code>(x<sub>i</sub>, y<sub>i</sub>)</code> 和 <code>(x<sub>j</sub>, y<sub>j</sub>)</code> 的横向距离绝对值与纵向距离绝对值之和，即 <code>|x<sub>i</sub> - x<sub>j</sub>| + |y<sub>i</sub> - y<sub>j</sub>|</code>。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "NWSE", k = 1</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><b>解释：</b></p>

<p>将&nbsp;<code>s[2]</code>&nbsp;从&nbsp;<code>'S'</code>&nbsp;改为&nbsp;<code>'N'</code> ，字符串&nbsp;<code>s</code>&nbsp;变为&nbsp;<code>"NWNE"</code> 。</p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">移动操作</th>
			<th style="border: 1px solid black;">位置 (x, y)</th>
			<th style="border: 1px solid black;">曼哈顿距离</th>
			<th style="border: 1px solid black;">最大值</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">s[0] == 'N'</td>
			<td style="border: 1px solid black;">(0, 1)</td>
			<td style="border: 1px solid black;">0 + 1 = 1</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">s[1] == 'W'</td>
			<td style="border: 1px solid black;">(-1, 1)</td>
			<td style="border: 1px solid black;">1 + 1 = 2</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">s[2] == 'N'</td>
			<td style="border: 1px solid black;">(-1, 2)</td>
			<td style="border: 1px solid black;">1 + 2 = 3</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">s[3] == 'E'</td>
			<td style="border: 1px solid black;">(0, 2)</td>
			<td style="border: 1px solid black;">0 + 2 = 2</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
	</tbody>
</table>

<p>执行移动操作过程中，距离原点的最大曼哈顿距离是 3 。</p>
</div>

<p><b>示例 2：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "NSWWEW", k = 3</span></p>

<p><span class="example-io"><b>输出：</b>6</span></p>

<p><b>解释：</b></p>

<p>将&nbsp;<code>s[1]</code>&nbsp;从&nbsp;<code>'S'</code>&nbsp;改为&nbsp;<code>'N'</code> ，将&nbsp;<code>s[4]</code>&nbsp;从&nbsp;<code>'E'</code>&nbsp;改为&nbsp;<code>'W'</code> 。字符串&nbsp;<code>s</code>&nbsp;变为&nbsp;<code>"NNWWWW"</code>&nbsp;。</p>

<p>执行移动操作过程中，距离原点的最大曼哈顿距离是 6&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= s.length</code></li>
	<li><code>s</code>&nbsp;仅由&nbsp;<code>'N'</code>、<code>'S'</code>、<code>'E'</code>&nbsp;和&nbsp;<code>'W'</code> 。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举 + 贪心

我们可以枚举四种情况，分别为 $\textit{SE}$, $\textit{SW}$, $\textit{NE}$, $\textit{NW}$，然后计算每种情况下的最大曼哈顿距离。

我们定义一个函数 $\text{calc}(a, b)$，用于计算最终生效方向为 $\textit{a}$ 和 $\textit{b}$ 时的最大曼哈顿距离。

我们定义变量 $\textit{mx}$ 用于记录当前的曼哈顿距离，定义 $\textit{cnt}$ 用于记录已经修改的次数，答案 $\textit{ans}$ 初始化为 $0$。

遍历字符串 $\textit{s}$，如果当前字符为 $\textit{a}$ 或 $\textit{b}$，则 $\textit{mx}$ 加 $1$，否则如果 $\textit{cnt} < k$，则 $\textit{mx}$ 加 $1$，而 $\textit{cnt}$ 加 $1$，否则 $\textit{mx}$ 减 $1$。然后更新 $\textit{ans} = \max(\textit{ans}, \textit{mx})$。

最后返回四种情况下的最大值。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $\textit{s}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxDistance(self, s: str, k: int) -> int:
        def calc(a: str, b: str) -> int:
            ans = mx = cnt = 0
            for c in s:
                if c == a or c == b:
                    mx += 1
                elif cnt < k:
                    cnt += 1
                    mx += 1
                else:
                    mx -= 1
                ans = max(ans, mx)
            return ans

        a = calc("S", "E")
        b = calc("S", "W")
        c = calc("N", "E")
        d = calc("N", "W")
        return max(a, b, c, d)
```

#### Java

```java
class Solution {
    private char[] s;
    private int k;

    public int maxDistance(String s, int k) {
        this.s = s.toCharArray();
        this.k = k;
        int a = calc('S', 'E');
        int b = calc('S', 'W');
        int c = calc('N', 'E');
        int d = calc('N', 'W');
        return Math.max(Math.max(a, b), Math.max(c, d));
    }

    private int calc(char a, char b) {
        int ans = 0, mx = 0, cnt = 0;
        for (char c : s) {
            if (c == a || c == b) {
                ++mx;
            } else if (cnt < k) {
                ++mx;
                ++cnt;
            } else {
                --mx;
            }
            ans = Math.max(ans, mx);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxDistance(string s, int k) {
        auto calc = [&](char a, char b) {
            int ans = 0, mx = 0, cnt = 0;
            for (char c : s) {
                if (c == a || c == b) {
                    ++mx;
                } else if (cnt < k) {
                    ++mx;
                    ++cnt;
                } else {
                    --mx;
                }
                ans = max(ans, mx);
            }
            return ans;
        };
        int a = calc('S', 'E');
        int b = calc('S', 'W');
        int c = calc('N', 'E');
        int d = calc('N', 'W');
        return max({a, b, c, d});
    }
};
```

#### Go

```go
func maxDistance(s string, k int) int {
	calc := func(a rune, b rune) int {
		var ans, mx, cnt int
		for _, c := range s {
			if c == a || c == b {
				mx++
			} else if cnt < k {
				mx++
				cnt++
			} else {
				mx--
			}
			ans = max(ans, mx)
		}
		return ans
	}
	a := calc('S', 'E')
	b := calc('S', 'W')
	c := calc('N', 'E')
	d := calc('N', 'W')
	return max(a, b, c, d)
}
```

#### TypeScript

```ts
function maxDistance(s: string, k: number): number {
    const calc = (a: string, b: string): number => {
        let [ans, mx, cnt] = [0, 0, 0];
        for (const c of s) {
            if (c === a || c === b) {
                ++mx;
            } else if (cnt < k) {
                ++mx;
                ++cnt;
            } else {
                --mx;
            }
            ans = Math.max(ans, mx);
        }
        return ans;
    };
    const a = calc('S', 'E');
    const b = calc('S', 'W');
    const c = calc('N', 'E');
    const d = calc('N', 'W');
    return Math.max(a, b, c, d);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

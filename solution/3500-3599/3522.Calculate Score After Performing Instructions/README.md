---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3522.Calculate%20Score%20After%20Performing%20Instructions/README.md
rating: 1238
source: 第 446 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 字符串
    - 模拟
---

<!-- problem:start -->

# [3522. 执行指令后的得分](https://leetcode.cn/problems/calculate-score-after-performing-instructions)

[English Version](/solution/3500-3599/3522.Calculate%20Score%20After%20Performing%20Instructions/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个数组：<code>instructions</code> 和 <code>values</code>，数组的长度均为 <code>n</code>。</p>

<p>你需要根据以下规则模拟一个过程：</p>

<ul>
	<li>从下标&nbsp;<code>i = 0</code> 的第一个指令开始，初始得分为 0。</li>
	<li>如果 <code>instructions[i]</code> 是 <code>"add"</code>：
	<ul>
		<li>将 <code>values[i]</code> 加到你的得分中。</li>
		<li>移动到下一个指令 <code>(i + 1)</code>。</li>
	</ul>
	</li>
	<li>如果 <code>instructions[i]</code> 是 <code>"jump"</code>：
	<ul>
		<li>移动到下标为&nbsp;<code>(i + values[i])</code> 的指令，但不修改你的得分。</li>
	</ul>
	</li>
</ul>

<p>当以下任一情况发生时，过程会终止：</p>

<ul>
	<li>越界（即 <code>i &lt; 0</code> 或 <code>i &gt;= n</code>），或</li>
	<li>尝试再次执行已经执行过的指令。被重复访问的指令不会再次执行。</li>
</ul>

<p>返回过程结束时的得分。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">instructions = ["jump","add","add","jump","add","jump"], values = [2,1,3,1,-2,-3]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>从下标&nbsp;0 开始模拟过程：</p>

<ul>
	<li>下标 0：指令是 <code>"jump"</code>，移动到下标&nbsp;<code>0 + 2 = 2</code>。</li>
	<li>下标 2：指令是 <code>"add"</code>，将 <code>values[2] = 3</code> 加到得分中，移动到下标&nbsp;3。得分变为 3。</li>
	<li>下标 3：指令是 <code>"jump"</code>，移动到下标&nbsp;<code>3 + 1 = 4</code>。</li>
	<li>下标 4：指令是 <code>"add"</code>，将 <code>values[4] = -2</code> 加到得分中，移动到下标&nbsp;5。得分变为 1。</li>
	<li>下标 5：指令是 <code>"jump"</code>，移动到下标&nbsp;<code>5 + (-3) = 2</code>。</li>
	<li>下标 2：已经访问过。过程结束。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">instructions = ["jump","add","add"], values = [3,1,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>从下标&nbsp;0 开始模拟过程：</p>

<ul>
	<li>下标 0：指令是 <code>"jump"</code>，移动到下标&nbsp;<code>0 + 3 = 3</code>。</li>
	<li>下标 3：越界。过程结束。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">instructions = ["jump"], values = [0]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>从下标&nbsp;0 开始模拟过程：</p>

<ul>
	<li>下标 0：指令是 <code>"jump"</code>，移动到下标&nbsp;<code>0 + 0 = 0</code>。</li>
	<li>下标 0：已经访问过。过程结束。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == instructions.length == values.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>instructions[i]</code> 只能是 <code>"add"</code> 或 <code>"jump"</code>。</li>
	<li><code>-10<sup>5</sup> &lt;= values[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们根据题意模拟即可。

我们定义一个长度为 $n$ 的布尔数组 $\textit{vis}$，用于记录每一条指令是否被执行过，初始时均为 $\text{false}$。

然后我们从下标 $i = 0$ 开始，循环执行以下操作：

1. 将 $\textit{vis}[i]$ 置为 $\text{true}$；
2. 如果 $\textit{instructions}[i]$ 的第一个字符为 'a'，那么我们将答案增加 $\textit{value}[i]$，然后 $i$ 加 $1$；否则，我们将 $i$ 增加 $\textit{value}[i]$。

循环，直至 $i \lt 0$ 或者 $i \ge n$，或者 $\textit{vis}[i]$ 为 $\text{true}$。

最后返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{value}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def calculateScore(self, instructions: List[str], values: List[int]) -> int:
        n = len(values)
        vis = [False] * n
        ans = i = 0
        while 0 <= i < n and not vis[i]:
            vis[i] = True
            if instructions[i][0] == "a":
                ans += values[i]
                i += 1
            else:
                i = i + values[i]
        return ans
```

#### Java

```java
class Solution {
    public long calculateScore(String[] instructions, int[] values) {
        int n = values.length;
        boolean[] vis = new boolean[n];
        long ans = 0;
        int i = 0;

        while (i >= 0 && i < n && !vis[i]) {
            vis[i] = true;
            if (instructions[i].charAt(0) == 'a') {
                ans += values[i];
                i += 1;
            } else {
                i = i + values[i];
            }
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long calculateScore(vector<string>& instructions, vector<int>& values) {
        int n = values.size();
        vector<bool> vis(n, false);
        long long ans = 0;
        int i = 0;

        while (i >= 0 && i < n && !vis[i]) {
            vis[i] = true;
            if (instructions[i][0] == 'a') {
                ans += values[i];
                i += 1;
            } else {
                i += values[i];
            }
        }

        return ans;
    }
};
```

#### Go

```go
func calculateScore(instructions []string, values []int) (ans int64) {
	n := len(values)
	vis := make([]bool, n)
	i := 0
	for i >= 0 && i < n && !vis[i] {
		vis[i] = true
		if instructions[i][0] == 'a' {
			ans += int64(values[i])
			i += 1
		} else {
			i += values[i]
		}
	}
	return
}
```

#### TypeScript

```ts
function calculateScore(instructions: string[], values: number[]): number {
    const n = values.length;
    const vis: boolean[] = Array(n).fill(false);
    let ans = 0;
    let i = 0;

    while (i >= 0 && i < n && !vis[i]) {
        vis[i] = true;
        if (instructions[i][0] === 'a') {
            ans += values[i];
            i += 1;
        } else {
            i += values[i];
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

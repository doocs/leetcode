---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3842.Toggle%20Light%20Bulbs/README.md
rating: 1160
source: 第 489 场周赛 Q1
---

<!-- problem:start -->

# [3842. 切换打开灯泡](https://leetcode.cn/problems/toggle-light-bulbs)

[English Version](/solution/3800-3899/3842.Toggle%20Light%20Bulbs/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>bulbs</code>，其中每个元素的取值范围为 1 到 100。</p>

<p>有 100 个电灯泡，按从 1 到 100 编号，初始时所有灯泡均为关闭状态。</p>

<p>对于数组 <code>bulbs</code> 中的每一个元素 <code>bulbs[i]</code>，执行以下操作：</p>

<ul>
	<li>如果第 <code>bulbs[i]</code>&nbsp;个灯泡当前是关闭状态，将其打开。</li>
	<li>如果第 <code>bulbs[i]</code>&nbsp;个灯泡当前是打开状态，将其关闭。</li>
</ul>

<p>返回一个整数列表，表示最终处于打开状态的灯泡编号，<strong>按升序排列</strong>。如果没有灯泡是打开的，返回一个空列表。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> bulbs<span class="example-io"> = [10,30,20,10]</span></p>

<p><strong>输出：</strong> <span class="example-io">[20,30]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第 <code>bulbs[0] = 10</code>&nbsp;个灯泡当前是关闭状态，将其打开。</li>
	<li>第 <code>bulbs[1] = 30</code>&nbsp;个灯泡当前是关闭状态，将其打开。</li>
	<li>第 <code>bulbs[2] = 20</code>&nbsp;个灯泡当前是关闭状态，将其打开。</li>
	<li>第 <code>bulbs[3] = 10</code> 个灯泡当前是打开状态，将其关闭。</li>
	<li>最终，第 20 个和第 30 个灯泡处于打开状态。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> bulbs<span class="example-io"> = [100,100]</span></p>

<p><strong>输出：</strong> <span class="example-io">[]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第 <code>bulbs[0] = 100</code>&nbsp;个灯泡当前是关闭状态，将其打开。</li>
	<li>第 <code>bulbs[1] = 100</code>&nbsp;个灯泡当前是打开状态，将其关闭。</li>
	<li>最终，没有灯泡处于打开状态。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= bulbs.length &lt;= 100</code></li>
	<li><code>1 &lt;= bulbs[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们用一个长度为 $101$ 的数组 $\textit{st}$ 来记录每个灯泡的状态，初始时所有元素为 $0$，表示所有灯泡均为关闭状态。对于数组 $\textit{bulbs}$ 中的每一个元素 $\textit{bulbs}[i]$，我们将 $\textit{st}[\textit{bulbs}[i]]$ 的值取反（即 $0$ 变为 $1$，$1$ 变为 $0$）。最后，我们遍历 $\textit{st}$ 数组，将值为 $1$ 的索引加入结果列表中，并返回结果。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{bulbs}$ 的长度。空间复杂度 $O(M)$，其中 $M$ 是灯泡的最大编号。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def toggleLightBulbs(self, bulbs: list[int]) -> list[int]:
        st = [0] * 101
        for x in bulbs:
            st[x] ^= 1
        return [i for i, x in enumerate(st) if x]
```

#### Java

```java
class Solution {
    public List<Integer> toggleLightBulbs(List<Integer> bulbs) {
        int[] st = new int[101];
        for (int x : bulbs) {
            st[x] ^= 1;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < st.length; ++i) {
            if (st[i] == 1) {
                ans.add(i);
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
    vector<int> toggleLightBulbs(vector<int>& bulbs) {
        vector<int> st(101, 0);
        for (int x : bulbs) {
            st[x] ^= 1;
        }
        vector<int> ans;
        for (int i = 0; i < 101; ++i) {
            if (st[i]) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func toggleLightBulbs(bulbs []int) []int {
	st := make([]int, 101)
	for _, x := range bulbs {
		st[x] ^= 1
	}
	ans := make([]int, 0)
	for i := 0; i < 101; i++ {
		if st[i] == 1 {
			ans = append(ans, i)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function toggleLightBulbs(bulbs: number[]): number[] {
    const st: number[] = new Array(101).fill(0);
    for (const x of bulbs) {
        st[x] ^= 1;
    }
    const ans: number[] = [];
    for (let i = 0; i < 101; i++) {
        if (st[i] === 1) {
            ans.push(i);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

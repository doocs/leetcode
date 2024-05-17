---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1953.Maximum%20Number%20of%20Weeks%20for%20Which%20You%20Can%20Work/README.md
rating: 1803
source: 第 252 场周赛 Q2
tags:
    - 贪心
    - 数组
---

<!-- problem:start -->

# [1953. 你可以工作的最大周数](https://leetcode.cn/problems/maximum-number-of-weeks-for-which-you-can-work)

[English Version](/solution/1900-1999/1953.Maximum%20Number%20of%20Weeks%20for%20Which%20You%20Can%20Work/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你&nbsp;<code>n</code> 个项目，编号从 <code>0</code> 到 <code>n - 1</code> 。同时给你一个整数数组 <code>milestones</code> ，其中每个 <code>milestones[i]</code> 表示第 <code>i</code> 个项目中的阶段任务数量。</p>

<p>你可以按下面两个规则参与项目中的工作：</p>

<ul>
	<li>每周，你将会完成 <strong>某一个</strong> 项目中的 <strong>恰好一个</strong>&nbsp;阶段任务。你每周都 <strong>必须</strong> 工作。</li>
	<li>在 <strong>连续的</strong> 两周中，你 <strong>不能</strong> 参与并完成同一个项目中的两个阶段任务。</li>
</ul>

<p>一旦所有项目中的全部阶段任务都完成，或者仅剩余一个阶段任务都会导致你违反上面的规则，那么你将&nbsp;<strong>停止工作</strong> 。注意，由于这些条件的限制，你可能无法完成所有阶段任务。</p>

<p>返回在不违反上面规则的情况下你&nbsp;<strong>最多</strong>&nbsp;能工作多少周。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>milestones = [1,2,3]
<strong>输出：</strong>6
<strong>解释：</strong>一种可能的情形是：
​​​​- 第 1 周，你参与并完成项目 0 中的一个阶段任务。
- 第 2 周，你参与并完成项目 2 中的一个阶段任务。
- 第 3 周，你参与并完成项目 1 中的一个阶段任务。
- 第 4 周，你参与并完成项目 2 中的一个阶段任务。
- 第 5 周，你参与并完成项目 1 中的一个阶段任务。
- 第 6 周，你参与并完成项目 2 中的一个阶段任务。
总周数是 6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>milestones = [5,2,1]
<strong>输出：</strong>7
<strong>解释：</strong>一种可能的情形是：
- 第 1 周，你参与并完成项目 0 中的一个阶段任务。
- 第 2 周，你参与并完成项目 1 中的一个阶段任务。
- 第 3 周，你参与并完成项目 0 中的一个阶段任务。
- 第 4 周，你参与并完成项目 1 中的一个阶段任务。
- 第 5 周，你参与并完成项目 0 中的一个阶段任务。
- 第 6 周，你参与并完成项目 2 中的一个阶段任务。
- 第 7 周，你参与并完成项目 0 中的一个阶段任务。
总周数是 7 。
注意，你不能在第 8 周参与完成项目 0 中的最后一个阶段任务，因为这会违反规则。
因此，项目 0 中会有一个阶段任务维持未完成状态。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == milestones.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= milestones[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

我们考虑什么情况下不能完成所有阶段任务。如果存在一个项目 $i$，它的阶段任务数大于其余所有项目的阶段任务数之和再加 $1$，那么就不能完成所有阶段任务。否则，我们一定可以通过不同项目之间来回穿插的方式完成所有阶段任务。

我们记所有项目的阶段任务数之和为 $s$，最大的阶段任务数为 $mx$，那么其余所有项目的阶段任务数之和为 $rest = s - mx$。

如果 $mx \gt rest + 1$，那么就不能完成所有阶段任务，最多只能完成 $rest \times 2 + 1$ 个阶段任务。否则，我们可以完成所有阶段任务，数量为 $s$。

时间复杂度 $O(n)$，其中 $n$ 为项目数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfWeeks(self, milestones: List[int]) -> int:
        mx, s = max(milestones), sum(milestones)
        rest = s - mx
        return rest * 2 + 1 if mx > rest + 1 else s
```

#### Java

```java
class Solution {
    public long numberOfWeeks(int[] milestones) {
        int mx = 0;
        long s = 0;
        for (int e : milestones) {
            s += e;
            mx = Math.max(mx, e);
        }
        long rest = s - mx;
        return mx > rest + 1 ? rest * 2 + 1 : s;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long numberOfWeeks(vector<int>& milestones) {
        int mx = *max_element(milestones.begin(), milestones.end());
        long long s = accumulate(milestones.begin(), milestones.end(), 0LL);
        long long rest = s - mx;
        return mx > rest + 1 ? rest * 2 + 1 : s;
    }
};
```

#### Go

```go
func numberOfWeeks(milestones []int) int64 {
	mx := slices.Max(milestones)
	s := 0
	for _, x := range milestones {
		s += x
	}
	rest := s - mx
	if mx > rest+1 {
		return int64(rest*2 + 1)
	}
	return int64(s)
}
```

#### TypeScript

```ts
function numberOfWeeks(milestones: number[]): number {
    const mx = Math.max(...milestones);
    const s = milestones.reduce((a, b) => a + b, 0);
    const rest = s - mx;
    return mx > rest + 1 ? rest * 2 + 1 : s;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

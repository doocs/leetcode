---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3616.Number%20of%20Student%20Replacements/README.md
tags:
    - 数组
    - 模拟
---

<!-- problem:start -->

# [3616. 学生替换人数 🔒](https://leetcode.cn/problems/number-of-student-replacements)

[English Version](/solution/3600-3699/3616.Number%20of%20Student%20Replacements/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组&nbsp;<code>ranks</code>，其中&nbsp;<code>ranks[i]</code>&nbsp;表示第 <code>i</code> 个 <strong>按顺序</strong> 到达的学生排名。数字越低表示排名 <strong>越好</strong>。</p>

<p>最初，默认 <strong>选中</strong> 第一个学生。</p>

<p>当一名排名 <strong>严格</strong> 更好的学生到来时，会发生 <strong>替换</strong>，并 <strong>取代</strong> 当前的选择。</p>

<p>返回替换的总数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>ranks = [4,1,2]</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第一个&nbsp;<code>ranks[0] = 4</code>&nbsp;的学生最初被选中。</li>
	<li>第二个学生&nbsp;<code>ranks[1] = 1</code> 比当前选择更好，因此发生替换。</li>
	<li>第三名学生排名更差，因此没有发生替换。</li>
	<li>因此，替换的人数为 1。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>ranks = [2,2,3]</span></p>

<p><strong>输出：</strong><span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第一个&nbsp;<code>ranks[0] = 2</code> 的学生最初被选中。</li>
	<li>两个&nbsp;<code>ranks[1] = 2</code> 或&nbsp;<code>ranks[2] = 3</code> 都不如当前选择好。</li>
	<li>因此，替换的人数为 0。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= ranks.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= ranks[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

<!-- thinking:start -->

> **思考**
>
> 替换发生当且仅当出现严格更优（更小）的排名。只需维护当前选中的排名 $\textit{cur}$，初值为第一位学生。
>
> 从左到右扫描，若 $x<\textit{cur}$ 则更新 $\textit{cur}$ 并计数加一。$n\le 10^5$，一次遍历即可，不必记录历史列表。

<!-- thinking:end -->


我们用一个变量 $\text{cur}$ 来记录当前选中的学生的排名。遍历数组 $\text{ranks}$，如果遇到一个排名更好的学生（即 $\text{ranks}[i] < \text{cur}$），则更新 $\text{cur}$ 并将答案加一。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 是学生的数量。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def totalReplacements(self, ranks: List[int]) -> int:
        ans, cur = 0, ranks[0]
        for x in ranks:
            if x < cur:
                cur = x
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int totalReplacements(int[] ranks) {
        int ans = 0;
        int cur = ranks[0];
        for (int x : ranks) {
            if (x < cur) {
                cur = x;
                ++ans;
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
    int totalReplacements(vector<int>& ranks) {
        int ans = 0;
        int cur = ranks[0];
        for (int x : ranks) {
            if (x < cur) {
                cur = x;
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func totalReplacements(ranks []int) (ans int) {
	cur := ranks[0]
	for _, x := range ranks {
		if x < cur {
			cur = x
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function totalReplacements(ranks: number[]): number {
    let [ans, cur] = [0, ranks[0]];
    for (const x of ranks) {
        if (x < cur) {
            cur = x;
            ans++;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

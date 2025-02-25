---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0781.Rabbits%20in%20Forest/README.md
tags:
    - 贪心
    - 数组
    - 哈希表
    - 数学
---

<!-- problem:start -->

# [781. 森林中的兔子](https://leetcode.cn/problems/rabbits-in-forest)

[English Version](/solution/0700-0799/0781.Rabbits%20in%20Forest/README_EN.md)

## 题目描述

<!-- description:start -->

<p>森林中有未知数量的兔子。提问其中若干只兔子<strong> "还有多少只兔子与你（指被提问的兔子）颜色相同?"</strong> ，将答案收集到一个整数数组 <code>answers</code> 中，其中 <code>answers[i]</code> 是第 <code>i</code> 只兔子的回答。</p>

<p>给你数组 <code>answers</code> ，返回森林中兔子的最少数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>answers = [1,1,2]
<strong>输出：</strong>5
<strong>解释：</strong>
两只回答了 "1" 的兔子可能有相同的颜色，设为红色。 
之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
设回答了 "2" 的兔子为蓝色。 
此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。 
因此森林中兔子的最少数量是 5 只：3 只回答的和 2 只没有回答的。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>answers = [10,10,10]
<strong>输出：</strong>11
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= answers.length &lt;= 1000</code></li>
	<li><code>0 &lt;= answers[i] &lt; 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 哈希表

根据题目描述，回答相同的兔子，可能属于同一种颜色，而回答不同的兔子，不可能属于同一种颜色。

因此，我们用一个哈希表 $\textit{cnt}$ 记录每种回答出现的次数。对于每种回答 $x$ 及其出现次数 $v$，我们按照每种颜色有 $x + 1$ 只兔子的原则，计算出兔子的最少数量，并将其加入答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{answers}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numRabbits(self, answers: List[int]) -> int:
        cnt = Counter(answers)
        ans = 0
        for x, v in cnt.items():
            group = x + 1
            ans += (v + group - 1) // group * group
        return ans
```

#### Java

```java
class Solution {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : answers) {
            cnt.merge(x, 1, Integer::sum);
        }
        int ans = 0;
        for (var e : cnt.entrySet()) {
            int group = e.getKey() + 1;
            ans += (e.getValue() + group - 1) / group * group;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numRabbits(vector<int>& answers) {
        unordered_map<int, int> cnt;
        for (int x : answers) {
            ++cnt[x];
        }
        int ans = 0;
        for (auto& [x, v] : cnt) {
            int group = x + 1;
            ans += (v + group - 1) / group * group;
        }
        return ans;
    }
};
```

#### Go

```go
func numRabbits(answers []int) (ans int) {
	cnt := map[int]int{}
	for _, x := range answers {
		cnt[x]++
	}
	for x, v := range cnt {
		group := x + 1
		ans += (v + group - 1) / group * group
	}
	return
}
```

#### TypeScript

```ts
function numRabbits(answers: number[]): number {
    const cnt = new Map<number, number>();
    for (const x of answers) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    let ans = 0;
    for (const [x, v] of cnt.entries()) {
        const group = x + 1;
        ans += Math.floor((v + group - 1) / group) * group;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

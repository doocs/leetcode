---
comments: true
difficulty: 简单
---

<!-- problem:start -->

# [4048. 统计等间距出现整数数目 I](https://leetcode.cn/problems/count-values-with-equally-spaced-occurrences-i)

[English Version](/solution/4000-4099/4048.Count%20Values%20With%20Equally%20Spaced%20Occurrences%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>如果一个整数 <code>x</code> 满足以下条件，则被称为 <strong>特别</strong> 的：</p>

<ul>
	<li><code>x</code> 在 <code>nums</code> 中 <strong>恰好出现三次</strong>。</li>
	<li><code>x</code> 的 <strong>所有</strong> 三次出现，在 <code>nums</code> 中都是 <strong>等间隔</strong> 的。换句话说，如果 <code>x</code> 的所有出现位置的下标为 <code>i<sub>1</sub> &lt; i<sub>2</sub> &lt; i<sub>3</sub></code>，那么 <code>i<sub>2</sub> - i<sub>1</sub> = i<sub>3</sub> - i<sub>2</sub></code>。</li>
</ul>

<p>返回 <code>nums</code> 中 <strong>不同</strong> 特别整数的数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [1,8,1,5,1,5,8,5]</span></p>

<p><strong>输出:</strong> <span class="example-io">2</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>1 是特别的，因为它恰好出现三次，且出现的等间隔下标为 0、2 和 4。</li>
	<li>5 是特别的，因为它恰好出现三次，且出现的等间隔下标为 3、5 和 7。</li>
	<li>8 不是特别的，因为它只出现了两次。</li>
</ul>

<p>因此，答案是 2。</p>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [8,8,8,8]</span></p>

<p><strong>输出:</strong>&nbsp;0</p>

<p><strong>解释:</strong></p>

<p>8 不是特别的，因为它出现的次数不是恰好三次。因此，答案是 0。</p>
</div>

<p><strong class="example">示例 3:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [8,6,6,8,8]</span></p>

<p><strong>输出:</strong> <span class="example-io">0</span></p>

<p><strong>解释:</strong></p>

<p>8 出现的下标为 0、3 和 4，这些下标不是等间隔的。6 只出现了两次。因此，没有整数是特别的。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

<!-- thinking:start -->

> **思考**
>
> $n \le 100$，即便对每个值再扫一遍数组也能通过。特别整数要求恰好出现三次，且三次下标成等差。
>
> 把同一值的下标收集到一起后，判断退化成两件事：列表长度是否为 $3$，以及首末下标之和是否等于中间下标的两倍。
>
> 用哈希表按值分组，一次遍历即可统计。

<!-- thinking:end -->

我们用哈希表记录每个整数出现的所有下标。遍历数组 $\textit{nums}$，将下标 $i$ 加入 $\textit{nums}[i]$ 对应的列表中。

然后遍历哈希表中的每个下标列表 $\textit{pos}$。若 $\textit{pos}$ 的长度为 $3$，且 $\textit{pos}[0] + \textit{pos}[2] = 2 \times \textit{pos}[1]$（即三次出现等间隔），则该整数是特别的，将答案加 $1$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSpecialIntegers(self, nums: list[int]) -> int:
        g = defaultdict(list)
        for i, x in enumerate(nums):
            g[x].append(i)
        return sum(
            len(pos) == 3 and pos[0] + pos[2] == pos[1] * 2 for pos in g.values()
        )
```

#### Java

```java
class Solution {
    public int countSpecialIntegers(int[] nums) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            g.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int ans = 0;
        for (List<Integer> pos : g.values()) {
            if (pos.size() == 3 && pos.get(0) + pos.get(2) == pos.get(1) * 2) {
                ans++;
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
    int countSpecialIntegers(vector<int>& nums) {
        unordered_map<int, vector<int>> g;
        for (int i = 0; i < nums.size(); i++) {
            g[nums[i]].push_back(i);
        }

        int ans = 0;
        for (auto& [x, pos] : g) {
            if (pos.size() == 3 && pos[0] + pos[2] == pos[1] * 2) {
                ans++;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countSpecialIntegers(nums []int) int {
	g := make(map[int][]int)
	for i, x := range nums {
		g[x] = append(g[x], i)
	}

	ans := 0
	for _, pos := range g {
		if len(pos) == 3 && pos[0]+pos[2] == pos[1]*2 {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countSpecialIntegers(nums: number[]): number {
    const g = new Map<number, number[]>();

    for (let i = 0; i < nums.length; i++) {
        if (!g.has(nums[i])) {
            g.set(nums[i], []);
        }
        g.get(nums[i])!.push(i);
    }

    let ans = 0;
    for (const pos of g.values()) {
        if (pos.length === 3 && pos[0] + pos[2] === pos[1] * 2) {
            ans++;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

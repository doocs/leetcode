---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0947.Most%20Stones%20Removed%20with%20Same%20Row%20or%20Column/README.md
tags:
    - 深度优先搜索
    - 并查集
    - 图
    - 哈希表
---

<!-- problem:start -->

# [947. 移除最多的同行或同列石头](https://leetcode.cn/problems/most-stones-removed-with-same-row-or-column)

[English Version](/solution/0900-0999/0947.Most%20Stones%20Removed%20with%20Same%20Row%20or%20Column/README_EN.md)

## 题目描述

<!-- description:start -->

<p><code>n</code> 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。</p>

<p>如果一块石头的 <strong>同行或者同列</strong> 上有其他石头存在，那么就可以移除这块石头。</p>

<p>给你一个长度为 <code>n</code> 的数组 <code>stones</code> ，其中 <code>stones[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 表示第 <code>i</code> 块石头的位置，返回 <strong>可以移除的石子</strong> 的最大数量。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
<strong>输出：</strong>5
<strong>解释：</strong>一种移除 5 块石头的方法如下所示：
1. 移除石头 [2,2] ，因为它和 [2,1] 同行。
2. 移除石头 [2,1] ，因为它和 [0,1] 同列。
3. 移除石头 [1,2] ，因为它和 [1,0] 同行。
4. 移除石头 [1,0] ，因为它和 [0,0] 同列。
5. 移除石头 [0,1] ，因为它和 [0,0] 同行。
石头 [0,0] 不能移除，因为它没有与另一块石头同行/列。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
<strong>输出：</strong>3
<strong>解释：</strong>一种移除 3 块石头的方法如下所示：
1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>stones = [[0,0]]
<strong>输出：</strong>0
<strong>解释：</strong>[0,0] 是平面上唯一一块石头，所以不可以移除它。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= stones.length <= 1000</code></li>
	<li><code>0 <= x<sub>i</sub>, y<sub>i</sub> <= 10<sup>4</sup></code></li>
	<li>不会有两块石头放在同一个坐标点上</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def removeStones(self, stones: List[List[int]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        n = 10010
        p = list(range(n << 1))
        for x, y in stones:
            p[find(x)] = find(y + n)

        s = {find(x) for x, _ in stones}
        return len(stones) - len(s)
```

#### Java

```java
class Solution {
    private int[] p;

    public int removeStones(int[][] stones) {
        int n = 10010;
        p = new int[n << 1];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        for (int[] stone : stones) {
            p[find(stone[0])] = find(stone[1] + n);
        }
        Set<Integer> s = new HashSet<>();
        for (int[] stone : stones) {
            s.add(find(stone[0]));
        }
        return stones.length - s.size();
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> p;

    int removeStones(vector<vector<int>>& stones) {
        int n = 10010;
        p.resize(n << 1);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        for (auto& stone : stones) p[find(stone[0])] = find(stone[1] + n);
        unordered_set<int> s;
        for (auto& stone : stones) s.insert(find(stone[0]));
        return stones.size() - s.size();
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

#### Go

```go
func removeStones(stones [][]int) int {
	n := 10010
	p := make([]int, n<<1)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, stone := range stones {
		p[find(stone[0])] = find(stone[1] + n)
	}
	s := make(map[int]bool)
	for _, stone := range stones {
		s[find(stone[0])] = true
	}
	return len(stones) - len(s)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

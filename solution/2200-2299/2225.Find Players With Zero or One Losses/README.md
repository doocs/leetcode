---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2225.Find%20Players%20With%20Zero%20or%20One%20Losses/README.md
rating: 1316
source: 第 287 场周赛 Q2
tags:
    - 数组
    - 哈希表
    - 计数
    - 排序
---

<!-- problem:start -->

# [2225. 找出输掉零场或一场比赛的玩家](https://leetcode.cn/problems/find-players-with-zero-or-one-losses)

[English Version](/solution/2200-2299/2225.Find%20Players%20With%20Zero%20or%20One%20Losses/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>matches</code> 其中 <code>matches[i] = [winner<sub>i</sub>, loser<sub>i</sub>]</code> 表示在一场比赛中 <code>winner<sub>i</sub></code> 击败了 <code>loser<sub>i</sub></code> 。</p>

<p>返回一个长度为 2 的列表<em> </em><code>answer</code> ：</p>

<ul>
	<li><code>answer[0]</code> 是所有 <strong>没有</strong> 输掉任何比赛的玩家列表。</li>
	<li><code>answer[1]</code> 是所有恰好输掉 <strong>一场</strong> 比赛的玩家列表。</li>
</ul>

<p>两个列表中的值都应该按 <strong>递增</strong> 顺序返回。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>只考虑那些参与 <strong>至少一场</strong> 比赛的玩家。</li>
	<li>生成的测试用例保证 <strong>不存在</strong> 两场比赛结果 <strong>相同</strong> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
<strong>输出：</strong>[[1,2,10],[4,5,7,8]]
<strong>解释：</strong>
玩家 1、2 和 10 都没有输掉任何比赛。
玩家 4、5、7 和 8 每个都输掉一场比赛。
玩家 3、6 和 9 每个都输掉两场比赛。
因此，answer[0] = [1,2,10] 和 answer[1] = [4,5,7,8] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>matches = [[2,3],[1,3],[5,4],[6,4]]
<strong>输出：</strong>[[1,2,5,6],[]]
<strong>解释：</strong>
玩家 1、2、5 和 6 都没有输掉任何比赛。
玩家 3 和 4 每个都输掉两场比赛。
因此，answer[0] = [1,2,5,6] 和 answer[1] = [] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= matches.length &lt;= 10<sup>5</sup></code></li>
	<li><code>matches[i].length == 2</code></li>
	<li><code>1 &lt;= winner<sub>i</sub>, loser<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>winner<sub>i</sub> != loser<sub>i</sub></code></li>
	<li>所有 <code>matches[i]</code> <strong>互不相同</strong></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 排序

我们用一个哈希表 $\text{cnt}$ 记录每个玩家输掉的比赛场次。

然后遍历哈希表，将输掉 $0$ 场比赛的玩家放入 $\text{ans}[0]$，将输掉 $1$ 场比赛的玩家放入 $\text{ans}[1]$。

最后将 $\text{ans}[0]$ 和 $\text{ans}[1]$ 按照升序排序，返回结果。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为比赛场次数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findWinners(self, matches: List[List[int]]) -> List[List[int]]:
        cnt = Counter()
        for winner, loser in matches:
            if winner not in cnt:
                cnt[winner] = 0
            cnt[loser] += 1
        ans = [[], []]
        for x, v in sorted(cnt.items()):
            if v < 2:
                ans[v].append(x)
        return ans
```

#### Java

```java
class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (var e : matches) {
            cnt.putIfAbsent(e[0], 0);
            cnt.merge(e[1], 1, Integer::sum);
        }
        List<List<Integer>> ans = List.of(new ArrayList<>(), new ArrayList<>());
        for (var e : cnt.entrySet()) {
            if (e.getValue() < 2) {
                ans.get(e.getValue()).add(e.getKey());
            }
        }
        Collections.sort(ans.get(0));
        Collections.sort(ans.get(1));
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> findWinners(vector<vector<int>>& matches) {
        map<int, int> cnt;
        for (auto& e : matches) {
            if (!cnt.contains(e[0])) {
                cnt[e[0]] = 0;
            }
            ++cnt[e[1]];
        }
        vector<vector<int>> ans(2);
        for (auto& [x, v] : cnt) {
            if (v < 2) {
                ans[v].push_back(x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findWinners(matches [][]int) [][]int {
	cnt := map[int]int{}
	for _, e := range matches {
		if _, ok := cnt[e[0]]; !ok {
			cnt[e[0]] = 0
		}
		cnt[e[1]]++
	}
	ans := make([][]int, 2)
	for x, v := range cnt {
		if v < 2 {
			ans[v] = append(ans[v], x)
		}
	}
	sort.Ints(ans[0])
	sort.Ints(ans[1])
	return ans
}
```

#### TypeScript

```ts
function findWinners(matches: number[][]): number[][] {
    const cnt: Map<number, number> = new Map();
    for (const [winner, loser] of matches) {
        if (!cnt.has(winner)) {
            cnt.set(winner, 0);
        }
        cnt.set(loser, (cnt.get(loser) || 0) + 1);
    }
    const ans: number[][] = [[], []];
    for (const [x, v] of cnt) {
        if (v < 2) {
            ans[v].push(x);
        }
    }
    ans[0].sort((a, b) => a - b);
    ans[1].sort((a, b) => a - b);
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[][]} matches
 * @return {number[][]}
 */
var findWinners = function (matches) {
    const cnt = new Map();
    for (const [winner, loser] of matches) {
        if (!cnt.has(winner)) {
            cnt.set(winner, 0);
        }
        cnt.set(loser, (cnt.get(loser) || 0) + 1);
    }
    const ans = [[], []];
    for (const [x, v] of cnt) {
        if (v < 2) {
            ans[v].push(x);
        }
    }
    ans[0].sort((a, b) => a - b);
    ans[1].sort((a, b) => a - b);
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

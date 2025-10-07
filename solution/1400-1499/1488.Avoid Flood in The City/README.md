---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1488.Avoid%20Flood%20in%20The%20City/README.md
rating: 1973
source: 第 194 场周赛 Q3
tags:
    - 贪心
    - 数组
    - 哈希表
    - 二分查找
    - 堆（优先队列）
---

<!-- problem:start -->

# [1488. 避免洪水泛滥](https://leetcode.cn/problems/avoid-flood-in-the-city)

[English Version](/solution/1400-1499/1488.Avoid%20Flood%20in%20The%20City/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你的国家有无数个湖泊，所有湖泊一开始都是空的。当第 <code>n</code>&nbsp;个湖泊下雨前是空的，那么它就会装满水。如果第 <code>n</code>&nbsp;个湖泊下雨前是 <strong>满的&nbsp;</strong>，这个湖泊会发生 <strong>洪水</strong> 。你的目标是避免任意一个湖泊发生洪水。</p>

<p>给你一个整数数组&nbsp;<code>rains</code>&nbsp;，其中：</p>

<ul>
	<li><code>rains[i] &gt; 0</code>&nbsp;表示第 <code>i</code>&nbsp;天时，第 <code>rains[i]</code>&nbsp;个湖泊会下雨。</li>
	<li><code>rains[i] == 0</code>&nbsp;表示第 <code>i</code>&nbsp;天没有湖泊会下雨，你可以选择 <strong>一个</strong>&nbsp;湖泊并 <strong>抽干</strong>&nbsp;这个湖泊的水。</li>
</ul>

<p>请返回一个数组<em>&nbsp;</em><code>ans</code>&nbsp;，满足：</p>

<ul>
	<li><code>ans.length == rains.length</code></li>
	<li>如果&nbsp;<code>rains[i] &gt; 0</code> ，那么<code>ans[i] == -1</code>&nbsp;。</li>
	<li>如果&nbsp;<code>rains[i] == 0</code>&nbsp;，<code>ans[i]</code>&nbsp;是你第&nbsp;<code>i</code>&nbsp;天选择抽干的湖泊。</li>
</ul>

<p>如果有多种可行解，请返回它们中的 <strong>任意一个</strong>&nbsp;。如果没办法阻止洪水，请返回一个 <strong>空的数组</strong>&nbsp;。</p>

<p>请注意，如果你选择抽干一个装满水的湖泊，它会变成一个空的湖泊。但如果你选择抽干一个空的湖泊，那么将无事发生。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>rains = [1,2,3,4]
<strong>输出：</strong>[-1,-1,-1,-1]
<strong>解释：</strong>第一天后，装满水的湖泊包括 [1]
第二天后，装满水的湖泊包括 [1,2]
第三天后，装满水的湖泊包括 [1,2,3]
第四天后，装满水的湖泊包括 [1,2,3,4]
没有哪一天你可以抽干任何湖泊的水，也没有湖泊会发生洪水。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>rains = [1,2,0,0,2,1]
<strong>输出：</strong>[-1,-1,2,1,-1,-1]
<strong>解释：</strong>第一天后，装满水的湖泊包括 [1]
第二天后，装满水的湖泊包括 [1,2]
第三天后，我们抽干湖泊 2 。所以剩下装满水的湖泊包括 [1]
第四天后，我们抽干湖泊 1 。所以暂时没有装满水的湖泊了。
第五天后，装满水的湖泊包括 [2]。
第六天后，装满水的湖泊包括 [1,2]。
可以看出，这个方案下不会有洪水发生。同时， [-1,-1,1,2,-1,-1] 也是另一个可行的没有洪水的方案。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>rains = [1,2,0,1,2]
<strong>输出：</strong>[]
<strong>解释：</strong>第二天后，装满水的湖泊包括 [1,2]。我们可以在第三天抽干一个湖泊的水。
但第三天后，湖泊 1 和 2 都会再次下雨，所以不管我们第三天抽干哪个湖泊的水，另一个湖泊都会发生洪水。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= rains.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= rains[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 二分查找

我们将所有晴天都存入 $sunny$ 数组或者有序集合中，使用哈希表 $rainy$ 记录每个湖泊最近一次下雨的日期。初始化答案数组 $ans$ 每个元素为 $-1$。

接下来，我们遍历 $rains$ 数组。对于每个下雨的日期 $i$，如果 $rainy[rains[i]]$ 存在，说明该湖泊在之前下过雨，那么我们需要找到 $sunny$ 数组中第一个大于 $rainy[rains[i]]$ 的日期，将其替换为下雨的日期，否则说明无法阻止洪水，返回空数组。对于没下雨的日期 $i$，我们将 $i$ 存入 $sunny$ 数组中，并且将 $ans[i]$ 置为 $1$。

遍历结束，返回答案数组。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为 $rains$ 数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def avoidFlood(self, rains: List[int]) -> List[int]:
        n = len(rains)
        ans = [-1] * n
        sunny = SortedList()
        rainy = {}
        for i, v in enumerate(rains):
            if v:
                if v in rainy:
                    idx = sunny.bisect_right(rainy[v])
                    if idx == len(sunny):
                        return []
                    ans[sunny[idx]] = v
                    sunny.discard(sunny[idx])
                rainy[v] = i
            else:
                sunny.add(i)
                ans[i] = 1
        return ans
```

#### Java

```java
class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        TreeSet<Integer> sunny = new TreeSet<>();
        Map<Integer, Integer> rainy = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int v = rains[i];
            if (v > 0) {
                if (rainy.containsKey(v)) {
                    Integer t = sunny.higher(rainy.get(v));
                    if (t == null) {
                        return new int[0];
                    }
                    ans[t] = v;
                    sunny.remove(t);
                }
                rainy.put(v, i);
            } else {
                sunny.add(i);
                ans[i] = 1;
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
    vector<int> avoidFlood(vector<int>& rains) {
        int n = rains.size();
        vector<int> ans(n, -1);
        set<int> sunny;
        unordered_map<int, int> rainy;
        for (int i = 0; i < n; ++i) {
            int v = rains[i];
            if (v) {
                if (rainy.count(v)) {
                    auto it = sunny.upper_bound(rainy[v]);
                    if (it == sunny.end()) {
                        return {};
                    }
                    ans[*it] = v;
                    sunny.erase(it);
                }
                rainy[v] = i;
            } else {
                sunny.insert(i);
                ans[i] = 1;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func avoidFlood(rains []int) []int {
	n := len(rains)
	ans := make([]int, n)
	for i := range ans {
		ans[i] = -1
	}

	sunny := redblacktree.New[int, struct{}]()
	rainy := map[int]int{}

	for i, v := range rains {
		if v > 0 {
			if last, ok := rainy[v]; ok {
				node, found := sunny.Ceiling(last + 1)
				if !found {
					return []int{}
				}
				t := node.Key
				ans[t] = v
				sunny.Remove(t)
			}
			rainy[v] = i
		} else {
			sunny.Put(i, struct{}{})
			ans[i] = 1
		}
	}
	return ans
}
```

#### TypeScript

```ts
import { AvlTree } from 'datastructures-js';

function avoidFlood(rains: number[]): number[] {
    const n = rains.length;
    const ans = Array(n).fill(-1);
    const sunny = new AvlTree<number>((a, b) => a - b);
    const rainy = new Map<number, number>();

    for (let i = 0; i < n; ++i) {
        const v = rains[i];
        if (v > 0) {
            if (rainy.has(v)) {
                const last = rainy.get(v)!;
                const node = sunny.ceil(last + 1);
                if (!node) return [];
                const t = node.getValue();
                ans[t] = v;
                sunny.remove(t);
            }
            rainy.set(v, i);
        } else {
            sunny.insert(i);
            ans[i] = 1;
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::{BTreeSet, HashMap};

impl Solution {
    pub fn avoid_flood(rains: Vec<i32>) -> Vec<i32> {
        let n = rains.len();
        let mut ans = vec![-1; n];
        let mut sunny = BTreeSet::new();
        let mut rainy = HashMap::new();

        for (i, &v) in rains.iter().enumerate() {
            if v > 0 {
                if let Some(&last) = rainy.get(&v) {
                    if let Some(&t) = sunny.range((last + 1) as usize..).next() {
                        ans[t] = v;
                        sunny.remove(&t);
                    } else {
                        return vec![];
                    }
                }
                rainy.insert(v, i as i32);
            } else {
                sunny.insert(i);
                ans[i] = 1;
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

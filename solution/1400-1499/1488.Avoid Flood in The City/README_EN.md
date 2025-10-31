---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1488.Avoid%20Flood%20in%20The%20City/README_EN.md
rating: 1973
source: Weekly Contest 194 Q3
tags:
    - Greedy
    - Array
    - Hash Table
    - Binary Search
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [1488. Avoid Flood in The City](https://leetcode.com/problems/avoid-flood-in-the-city)

[中文文档](/solution/1400-1499/1488.Avoid%20Flood%20in%20The%20City/README.md)

## Description

<!-- description:start -->

<p>Your country has 10<sup>9</sup> lakes. Initially, all the lakes are empty, but when it rains over the <code>n<sup>th</sup></code> lake, the <code>n<sup>th</sup></code> lake becomes full of water. If it rains over a lake that is <strong>full of water</strong>, there will be a <strong>flood</strong>. Your goal is to avoid floods in any lake.</p>

<p>Given an integer array <code>rains</code> where:</p>

<ul>
	<li><code>rains[i] &gt; 0</code> means there will be rains over the <code>rains[i]</code> lake.</li>
	<li><code>rains[i] == 0</code> means there are no rains this day and you&nbsp;<strong>must</strong> choose&nbsp;<strong>one lake</strong> this day and <strong>dry it</strong>.</li>
</ul>

<p>Return <em>an array <code>ans</code></em> where:</p>

<ul>
	<li><code>ans.length == rains.length</code></li>
	<li><code>ans[i] == -1</code> if <code>rains[i] &gt; 0</code>.</li>
	<li><code>ans[i]</code> is the lake you choose to dry in the <code>ith</code> day if <code>rains[i] == 0</code>.</li>
</ul>

<p>If there are multiple valid answers return <strong>any</strong> of them. If it is impossible to avoid flood return <strong>an empty array</strong>.</p>

<p>Notice that if you chose to dry a full lake, it becomes empty, but if you chose to dry an empty lake, nothing changes.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> rains = [1,2,3,4]
<strong>Output:</strong> [-1,-1,-1,-1]
<strong>Explanation:</strong> After the first day full lakes are [1]
After the second day full lakes are [1,2]
After the third day full lakes are [1,2,3]
After the fourth day full lakes are [1,2,3,4]
There&#39;s no day to dry any lake and there is no flood in any lake.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> rains = [1,2,0,0,2,1]
<strong>Output:</strong> [-1,-1,2,1,-1,-1]
<strong>Explanation:</strong> After the first day full lakes are [1]
After the second day full lakes are [1,2]
After the third day, we dry lake 2. Full lakes are [1]
After the fourth day, we dry lake 1. There is no full lakes.
After the fifth day, full lakes are [2].
After the sixth day, full lakes are [1,2].
It is easy that this scenario is flood-free. [-1,-1,1,2,-1,-1] is another acceptable scenario.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> rains = [1,2,0,1,2]
<strong>Output:</strong> []
<strong>Explanation:</strong> After the second day, full lakes are  [1,2]. We have to dry one lake in the third day.
After that, it will rain over lakes [1,2]. It&#39;s easy to prove that no matter which lake you choose to dry in the 3rd day, the other one will flood.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= rains.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= rains[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Binary Search

We store all sunny days in the $sunny$ array or a sorted set, and use the hash table $rainy$ to record the last rainy day for each lake. We initialize the answer array $ans$ with each element set to $-1$.

Next, we traverse the $rains$ array. For each rainy day $i$, if $rainy[rains[i]]$ exists, it means that the lake has rained before, so we need to find the first date in the $sunny$ array that is greater than $rainy[rains[i]]$, and replace it with the rainy day. Otherwise, it means that the flood cannot be prevented, and we return an empty array. For each non-rainy day $i$, we store $i$ in the $sunny$ array and set $ans[i]$ to $1$.

After the traversal, we return the answer array.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the $rains$ array.

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

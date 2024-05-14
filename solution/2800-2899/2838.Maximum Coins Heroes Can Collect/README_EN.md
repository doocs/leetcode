---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2838.Maximum%20Coins%20Heroes%20Can%20Collect/README_EN.md
tags:
    - Array
    - Two Pointers
    - Binary Search
    - Prefix Sum
    - Sorting
---

# [2838. Maximum Coins Heroes Can Collect 🔒](https://leetcode.com/problems/maximum-coins-heroes-can-collect)

[中文文档](/solution/2800-2899/2838.Maximum%20Coins%20Heroes%20Can%20Collect/README.md)

## Description

<p>There is a battle and <code>n</code> heroes are trying to defeat <code>m</code> monsters. You are given two <strong>1-indexed</strong> arrays of <strong>positive</strong> integers <code><font face="monospace">heroes</font></code> and <code><font face="monospace">monsters</font></code> of length <code>n</code> and <code>m</code>, respectively. <code><font face="monospace">heroes</font>[i]</code> is the power of <code>i<sup>th</sup></code> hero, and <code><font face="monospace">monsters</font>[i]</code> is the power of <code>i<sup>th</sup></code> monster.</p>

<p>The <code>i<sup>th</sup></code> hero can defeat the <code>j<sup>th</sup></code> monster if <code>monsters[j] &lt;= heroes[i]</code>.</p>

<p>You are also given a <strong>1-indexed</strong> array <code>coins</code> of length <code>m</code> consisting of <strong>positive</strong> integers. <code>coins[i]</code> is the number of coins that each hero earns after defeating the <code>i<sup>th</sup></code> monster.</p>

<p>Return<em> an array </em><code>ans</code><em> of length </em><code>n</code><em> where </em><code>ans[i]</code><em> is the <strong>maximum</strong> number of coins that the </em><code>i<sup>th</sup></code><em> hero can collect from this battle</em>.</p>

<p><strong>Notes</strong></p>

<ul>
	<li>The health of a hero doesn&#39;t get reduced after defeating a monster.</li>
	<li>Multiple heroes can defeat a monster, but each monster can be defeated by a given hero only once.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> heroes = [1,4,2], monsters = [1,1,5,2,3], coins = [2,3,4,5,6]
<strong>Output:</strong> [5,16,10]
<strong>Explanation: </strong>For each hero, we list the index of all the monsters he can defeat:
1<sup>st</sup> hero: [1,2] since the power of this hero is 1 and monsters[1], monsters[2] &lt;= 1. So this hero collects coins[1] + coins[2] = 5 coins.
2<sup>nd</sup> hero: [1,2,4,5] since the power of this hero is 4 and monsters[1], monsters[2], monsters[4], monsters[5] &lt;= 4. So this hero collects coins[1] + coins[2] + coins[4] + coins[5] = 16 coins.
3<sup>rd</sup> hero: [1,2,4] since the power of this hero is 2 and monsters[1], monsters[2], monsters[4] &lt;= 2. So this hero collects coins[1] + coins[2] + coins[4] = 10 coins.
So the answer would be [5,16,10].</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> heroes = [5], monsters = [2,3,1,2], coins = [10,6,5,2]
<strong>Output:</strong> [23]
<strong>Explanation:</strong> This hero can defeat all the monsters since monsters[i] &lt;= 5. So he collects all of the coins: coins[1] + coins[2] + coins[3] + coins[4] = 23, and the answer would be [23].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> heroes = [4,4], monsters = [5,7,8], coins = [1,1,1]
<strong>Output:</strong> [0,0]
<strong>Explanation:</strong> In this example, no hero can defeat a monster. So the answer would be [0,0],
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == heroes.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m == monsters.length &lt;= 10<sup>5</sup></code></li>
	<li><code>coins.length == m</code></li>
	<li><code>1 &lt;= heroes[i], monsters[i], coins[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Sorting + Prefix Sum + Binary Search

We can sort the monsters and coins in ascending order of the monsters' combat power, and then use prefix sum to calculate the total number of coins each hero can get by defeating the first $i$ monsters.

Next, for each hero, we can use binary search to find the strongest monster he can defeat, and then use prefix sum to calculate the total number of coins he can get.

The time complexity is $O((m + n) \times \log n)$, and the space complexity is $O(m)$. Here, $m$ and $n$ are the number of monsters and heroes, respectively.

<!-- tabs:start -->

```python
class Solution:
    def maximumCoins(
        self, heroes: List[int], monsters: List[int], coins: List[int]
    ) -> List[int]:
        m = len(monsters)
        idx = sorted(range(m), key=lambda i: monsters[i])
        s = list(accumulate((coins[i] for i in idx), initial=0))
        ans = []
        for h in heroes:
            i = bisect_right(idx, h, key=lambda i: monsters[i])
            ans.append(s[i])
        return ans
```

```java
class Solution {
    public long[] maximumCoins(int[] heroes, int[] monsters, int[] coins) {
        int m = monsters.length;
        Integer[] idx = new Integer[m];
        for (int i = 0; i < m; ++i) {
            idx[i] = i;
        }

        Arrays.sort(idx, Comparator.comparingInt(j -> monsters[j]));
        long[] s = new long[m + 1];
        for (int i = 0; i < m; ++i) {
            s[i + 1] = s[i] + coins[idx[i]];
        }
        int n = heroes.length;
        long[] ans = new long[n];
        for (int k = 0; k < n; ++k) {
            int i = search(monsters, idx, heroes[k]);
            ans[k] = s[i];
        }
        return ans;
    }

    private int search(int[] nums, Integer[] idx, int x) {
        int l = 0, r = idx.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[idx[mid]] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

```cpp
class Solution {
public:
    vector<long long> maximumCoins(vector<int>& heroes, vector<int>& monsters, vector<int>& coins) {
        int m = monsters.size();
        vector<int> idx(m);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) {
            return monsters[i] < monsters[j];
        });
        long long s[m + 1];
        s[0] = 0;
        for (int i = 1; i <= m; ++i) {
            s[i] = s[i - 1] + coins[idx[i - 1]];
        }
        vector<long long> ans;
        auto search = [&](int x) {
            int l = 0, r = m;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (monsters[idx[mid]] > x) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        };
        for (int h : heroes) {
            ans.push_back(s[search(h)]);
        }
        return ans;
    }
};
```

```go
func maximumCoins(heroes []int, monsters []int, coins []int) (ans []int64) {
	m := len(monsters)
	idx := make([]int, m)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return monsters[idx[i]] < monsters[idx[j]] })
	s := make([]int64, m+1)
	for i, j := range idx {
		s[i+1] = s[i] + int64(coins[j])
	}
	for _, h := range heroes {
		i := sort.Search(m, func(i int) bool { return monsters[idx[i]] > h })
		ans = append(ans, s[i])
	}
	return
}
```

```ts
function maximumCoins(heroes: number[], monsters: number[], coins: number[]): number[] {
    const m = monsters.length;
    const idx: number[] = Array.from({ length: m }, (_, i) => i);
    idx.sort((i, j) => monsters[i] - monsters[j]);
    const s: number[] = Array(m + 1).fill(0);
    for (let i = 0; i < m; ++i) {
        s[i + 1] = s[i] + coins[idx[i]];
    }
    const search = (x: number): number => {
        let l = 0;
        let r = m;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (monsters[idx[mid]] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    return heroes.map(h => s[search(h)]);
}
```

<!-- tabs:end -->

<!-- end -->

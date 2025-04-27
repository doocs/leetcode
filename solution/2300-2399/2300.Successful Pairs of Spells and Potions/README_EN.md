---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2300.Successful%20Pairs%20of%20Spells%20and%20Potions/README_EN.md
rating: 1476
source: Biweekly Contest 80 Q2
tags:
    - Array
    - Two Pointers
    - Binary Search
    - Sorting
---

<!-- problem:start -->

# [2300. Successful Pairs of Spells and Potions](https://leetcode.com/problems/successful-pairs-of-spells-and-potions)

[中文文档](/solution/2300-2399/2300.Successful%20Pairs%20of%20Spells%20and%20Potions/README.md)

## Description

<!-- description:start -->

<p>You are given two positive integer arrays <code>spells</code> and <code>potions</code>, of length <code>n</code> and <code>m</code> respectively, where <code>spells[i]</code> represents the strength of the <code>i<sup>th</sup></code> spell and <code>potions[j]</code> represents the strength of the <code>j<sup>th</sup></code> potion.</p>

<p>You are also given an integer <code>success</code>. A spell and potion pair is considered <strong>successful</strong> if the <strong>product</strong> of their strengths is <strong>at least</strong> <code>success</code>.</p>

<p>Return <em>an integer array </em><code>pairs</code><em> of length </em><code>n</code><em> where </em><code>pairs[i]</code><em> is the number of <strong>potions</strong> that will form a successful pair with the </em><code>i<sup>th</sup></code><em> spell.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> spells = [5,1,3], potions = [1,2,3,4,5], success = 7
<strong>Output:</strong> [4,0,3]
<strong>Explanation:</strong>
- 0<sup>th</sup> spell: 5 * [1,2,3,4,5] = [5,<u><strong>10</strong></u>,<u><strong>15</strong></u>,<u><strong>20</strong></u>,<u><strong>25</strong></u>]. 4 pairs are successful.
- 1<sup>st</sup> spell: 1 * [1,2,3,4,5] = [1,2,3,4,5]. 0 pairs are successful.
- 2<sup>nd</sup> spell: 3 * [1,2,3,4,5] = [3,6,<u><strong>9</strong></u>,<u><strong>12</strong></u>,<u><strong>15</strong></u>]. 3 pairs are successful.
Thus, [4,0,3] is returned.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> spells = [3,1,2], potions = [8,5,8], success = 16
<strong>Output:</strong> [2,0,2]
<strong>Explanation:</strong>
- 0<sup>th</sup> spell: 3 * [8,5,8] = [<u><strong>24</strong></u>,15,<u><strong>24</strong></u>]. 2 pairs are successful.
- 1<sup>st</sup> spell: 1 * [8,5,8] = [8,5,8]. 0 pairs are successful. 
- 2<sup>nd</sup> spell: 2 * [8,5,8] = [<strong><u>16</u></strong>,10,<u><strong>16</strong></u>]. 2 pairs are successful. 
Thus, [2,0,2] is returned.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == spells.length</code></li>
	<li><code>m == potions.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= spells[i], potions[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= success &lt;= 10<sup>10</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Binary Search

We can sort the potion array, then traverse the spell array. For each spell $v$, we use binary search to find the first potion that is greater than or equal to $\frac{success}{v}$. We mark its index as $i$. The length of the potion array minus $i$ is the number of potions that can successfully combine with this spell.

The time complexity is $O((m + n) \times \log m)$, and the space complexity is $O(\log n)$. Here, $m$ and $n$ are the lengths of the potion array and the spell array, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def successfulPairs(
        self, spells: List[int], potions: List[int], success: int
    ) -> List[int]:
        potions.sort()
        m = len(potions)
        return [m - bisect_left(potions, success / v) for v in spells]
```

#### Java

```java
class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = spells.length, m = potions.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int left = 0, right = m;
            while (left < right) {
                int mid = (left + right) >> 1;
                if ((long) spells[i] * potions[mid] >= success) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            ans[i] = m - left;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> successfulPairs(vector<int>& spells, vector<int>& potions, long long success) {
        sort(potions.begin(), potions.end());
        vector<int> ans;
        int m = potions.size();
        for (int& v : spells) {
            int i = lower_bound(potions.begin(), potions.end(), success * 1.0 / v) - potions.begin();
            ans.push_back(m - i);
        }
        return ans;
    }
};
```

#### Go

```go
func successfulPairs(spells []int, potions []int, success int64) (ans []int) {
	sort.Ints(potions)
	m := len(potions)
	for _, v := range spells {
		i := sort.Search(m, func(i int) bool { return int64(potions[i]*v) >= success })
		ans = append(ans, m-i)
	}
	return ans
}
```

#### TypeScript

```ts
function successfulPairs(spells: number[], potions: number[], success: number): number[] {
    potions.sort((a, b) => a - b);
    const m = potions.length;
    const ans: number[] = [];
    for (const v of spells) {
        let left = 0;
        let right = m;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (v * potions[mid] >= success) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        ans.push(m - left);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn successful_pairs(spells: Vec<i32>, mut potions: Vec<i32>, success: i64) -> Vec<i32> {
        potions.sort();
        let m = potions.len();

        spells.into_iter().map(|v| {
            let i = potions.binary_search_by(|&p| {
                let prod = (p as i64) * (v as i64);
                if prod >= success {
                    std::cmp::Ordering::Greater
                } else {
                    std::cmp::Ordering::Less
                }
            }).unwrap_or_else(|x| x);
            (m - i) as i32
        }).collect()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

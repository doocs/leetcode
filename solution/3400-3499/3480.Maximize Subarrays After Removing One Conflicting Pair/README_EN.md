---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3480.Maximize%20Subarrays%20After%20Removing%20One%20Conflicting%20Pair/README_EN.md
rating: 2763
source: Weekly Contest 440 Q4
tags:
    - Segment Tree
    - Array
    - Enumeration
    - Prefix Sum
---

<!-- problem:start -->

# [3480. Maximize Subarrays After Removing One Conflicting Pair](https://leetcode.com/problems/maximize-subarrays-after-removing-one-conflicting-pair)

[中文文档](/solution/3400-3499/3480.Maximize%20Subarrays%20After%20Removing%20One%20Conflicting%20Pair/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> which represents an array <code>nums</code> containing the numbers from 1 to <code>n</code> in order. Additionally, you are given a 2D array <code>conflictingPairs</code>, where <code>conflictingPairs[i] = [a, b]</code> indicates that <code>a</code> and <code>b</code> form a conflicting pair.</p>

<p>Remove <strong>exactly</strong> one element from <code>conflictingPairs</code>. Afterward, count the number of <span data-keyword="subarray-nonempty">non-empty subarrays</span> of <code>nums</code> which do not contain both <code>a</code> and <code>b</code> for any remaining conflicting pair <code>[a, b]</code>.</p>

<p>Return the <strong>maximum</strong> number of subarrays possible after removing <strong>exactly</strong> one conflicting pair.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, conflictingPairs = [[2,3],[1,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Remove <code>[2, 3]</code> from <code>conflictingPairs</code>. Now, <code>conflictingPairs = [[1, 4]]</code>.</li>
	<li>There are 9 subarrays in <code>nums</code> where <code>[1, 4]</code> do not appear together. They are <code>[1]</code>, <code>[2]</code>, <code>[3]</code>, <code>[4]</code>, <code>[1, 2]</code>, <code>[2, 3]</code>, <code>[3, 4]</code>, <code>[1, 2, 3]</code> and <code>[2, 3, 4]</code>.</li>
	<li>The maximum number of subarrays we can achieve after removing one element from <code>conflictingPairs</code> is 9.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, conflictingPairs = [[1,2],[2,5],[3,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Remove <code>[1, 2]</code> from <code>conflictingPairs</code>. Now, <code>conflictingPairs = [[2, 5], [3, 5]]</code>.</li>
	<li>There are 12 subarrays in <code>nums</code> where <code>[2, 5]</code> and <code>[3, 5]</code> do not appear together.</li>
	<li>The maximum number of subarrays we can achieve after removing one element from <code>conflictingPairs</code> is 12.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= conflictingPairs.length &lt;= 2 * n</code></li>
	<li><code>conflictingPairs[i].length == 2</code></li>
	<li><code>1 &lt;= conflictingPairs[i][j] &lt;= n</code></li>
	<li><code>conflictingPairs[i][0] != conflictingPairs[i][1]</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration + Maintaining Minimum and Second Minimum Values

We store all conflicting pairs $(a, b)$ (assuming $a < b$) in a list $g$, where $g[a]$ represents the set of all numbers $b$ that conflict with $a$.

If no deletion occurs, we can enumerate each subarray's left endpoint $a$ in reverse order. The upper bound of its right endpoint is the minimum value $b_1$ among all $g[x \geq a]$ (excluding $b_1$), and the contribution to the answer is $b_1 - a$.

If we delete a conflicting pair containing $b_1$, then the new $b_1$ becomes the second minimum value $b_2$ among all $g[x \geq a]$, and its additional contribution to the answer is $b_2 - b_1$. We use an array $\text{cnt}$ to record the additional contribution for each $b_1$.

The final answer is the sum of all $b_1 - a$ contributions plus the maximum value of $\text{cnt}[b_1]$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of conflicting pairs.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSubarrays(self, n: int, conflictingPairs: List[List[int]]) -> int:
        g = [[] for _ in range(n + 1)]
        for a, b in conflictingPairs:
            if a > b:
                a, b = b, a
            g[a].append(b)
        cnt = [0] * (n + 2)
        ans = add = 0
        b1 = b2 = n + 1
        for a in range(n, 0, -1):
            for b in g[a]:
                if b < b1:
                    b2, b1 = b1, b
                elif b < b2:
                    b2 = b
            ans += b1 - a
            cnt[b1] += b2 - b1
            add = max(add, cnt[b1])
        ans += add
        return ans
```

#### Java

```java
class Solution {
    public long maxSubarrays(int n, int[][] conflictingPairs) {
        List<Integer>[] g = new List[n + 1];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] pair : conflictingPairs) {
            int a = pair[0], b = pair[1];
            if (a > b) {
                int c = a;
                a = b;
                b = c;
            }
            g[a].add(b);
        }
        long[] cnt = new long[n + 2];
        long ans = 0, add = 0;
        int b1 = n + 1, b2 = n + 1;
        for (int a = n; a > 0; --a) {
            for (int b : g[a]) {
                if (b < b1) {
                    b2 = b1;
                    b1 = b;
                } else if (b < b2) {
                    b2 = b;
                }
            }
            ans += b1 - a;
            cnt[b1] += b2 - b1;
            add = Math.max(add, cnt[b1]);
        }
        ans += add;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxSubarrays(int n, vector<vector<int>>& conflictingPairs) {
        vector<vector<int>> g(n + 1);
        for (auto& pair : conflictingPairs) {
            int a = pair[0], b = pair[1];
            if (a > b) {
                swap(a, b);
            }
            g[a].push_back(b);
        }

        vector<long long> cnt(n + 2, 0);
        long long ans = 0, add = 0;
        int b1 = n + 1, b2 = n + 1;

        for (int a = n; a > 0; --a) {
            for (int b : g[a]) {
                if (b < b1) {
                    b2 = b1;
                    b1 = b;
                } else if (b < b2) {
                    b2 = b;
                }
            }
            ans += b1 - a;
            cnt[b1] += b2 - b1;
            add = max(add, cnt[b1]);
        }

        ans += add;
        return ans;
    }
};
```

#### Go

```go
func maxSubarrays(n int, conflictingPairs [][]int) (ans int64) {
	g := make([][]int, n+1)
	for _, pair := range conflictingPairs {
		a, b := pair[0], pair[1]
		if a > b {
			a, b = b, a
		}
		g[a] = append(g[a], b)
	}

	cnt := make([]int64, n+2)
	var add int64
	b1, b2 := n+1, n+1

	for a := n; a > 0; a-- {
		for _, b := range g[a] {
			if b < b1 {
				b2 = b1
				b1 = b
			} else if b < b2 {
				b2 = b
			}
		}
		ans += int64(b1 - a)
		cnt[b1] += int64(b2 - b1)
		if cnt[b1] > add {
			add = cnt[b1]
		}
	}

	ans += add
	return ans
}
```

#### TypeScript

```ts
function maxSubarrays(n: number, conflictingPairs: number[][]): number {
    const g: number[][] = Array.from({ length: n + 1 }, () => []);
    for (let [a, b] of conflictingPairs) {
        if (a > b) {
            [a, b] = [b, a];
        }
        g[a].push(b);
    }

    const cnt: number[] = Array(n + 2).fill(0);
    let ans = 0,
        add = 0;
    let b1 = n + 1,
        b2 = n + 1;

    for (let a = n; a > 0; a--) {
        for (const b of g[a]) {
            if (b < b1) {
                b2 = b1;
                b1 = b;
            } else if (b < b2) {
                b2 = b;
            }
        }
        ans += b1 - a;
        cnt[b1] += b2 - b1;
        add = Math.max(add, cnt[b1]);
    }

    ans += add;
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_subarrays(n: i32, conflicting_pairs: Vec<Vec<i32>>) -> i64 {
        let mut g: Vec<Vec<i32>> = vec![vec![]; (n + 1) as usize];
        for pair in conflicting_pairs {
            let mut a = pair[0];
            let mut b = pair[1];
            if a > b {
                std::mem::swap(&mut a, &mut b);
            }
            g[a as usize].push(b);
        }

        let mut cnt: Vec<i64> = vec![0; (n + 2) as usize];
        let mut ans = 0i64;
        let mut add = 0i64;
        let mut b1 = n + 1;
        let mut b2 = n + 1;

        for a in (1..=n).rev() {
            for &b in &g[a as usize] {
                if b < b1 {
                    b2 = b1;
                    b1 = b;
                } else if b < b2 {
                    b2 = b;
                }
            }
            ans += (b1 - a) as i64;
            cnt[b1 as usize] += (b2 - b1) as i64;
            add = std::cmp::max(add, cnt[b1 as usize]);
        }

        ans += add;
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3897.Maximum%20Value%20of%20Concatenated%20Binary%20Segments/README_EN.md
rating: 1998
source: Biweekly Contest 180 Q4
---

<!-- problem:start -->

# [3897. Maximum Value of Concatenated Binary Segments](https://leetcode.com/problems/maximum-value-of-concatenated-binary-segments)

[中文文档](/solution/3800-3899/3897.Maximum%20Value%20of%20Concatenated%20Binary%20Segments/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>nums1</code> and <code>nums0</code>, each of size <code>n</code>.</p>

<ul>
	<li><code>nums1[i]</code> represents the number of <code>&#39;1&#39;</code>s in the <code>i<sup>th</sup></code> segment.</li>
	<li><code>nums0[i]</code> represents the number of <code>&#39;0&#39;</code>s in the <code>i<sup>th</sup></code> segment.</li>
</ul>

<p>For each index <code>i</code>, construct a binary segment consisting of:</p>

<ul>
	<li><code>nums1[i]</code> occurrences of <code>&#39;1&#39;</code> followed by</li>
	<li><code>nums0[i]</code> occurrences of <code>&#39;0&#39;</code>.</li>
</ul>

<p>You may <strong>rearrange</strong> the order of these <strong>segments</strong> in any way. After rearranging, <strong>concatenate</strong> all segments to form a single binary string.</p>

<p>Return the <strong>maximum</strong> possible integer value of the concatenated binary string.</p>

<p>Since the result can be very large, return the answer <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [1,2], nums0 = [1,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">14</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>At index 0, <code>nums1[0] = 1</code> and <code>nums0[0] = 1</code>, so the segment formed is <code>&quot;10&quot;</code>.</li>
	<li>At index 1, <code>nums1[1] = 2</code> and <code>nums0[1] = 0</code>, so the segment formed is <code>&quot;11&quot;</code>.</li>
	<li>Reordering the segments as <code>&quot;11&quot;</code> followed by <code>&quot;10&quot;</code> produces the binary string <code>&quot;1110&quot;</code>.</li>
	<li>The binary number <code>&quot;1110&quot;</code> has value 14 which is the maximum possible value.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [3,1], nums0 = [0,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">120</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>At index 0, <code>nums1[0] = 3</code> and <code>nums0[0] = 0</code>, so the segment formed is <code>&quot;111&quot;</code>.</li>
	<li>At index 1, <code>nums1[1] = 1</code> and <code>nums0[1] = 3</code>, so the segment formed is <code>&quot;1000&quot;</code>.</li>
	<li>Reordering the segments as <code>&quot;111&quot;</code> followed by <code>&quot;1000&quot;</code> produces the binary string <code>&quot;1111000&quot;</code>.</li>
	<li>The binary number <code>&quot;1111000&quot;</code> has value 120 which is the maximum possible value.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums1.length == nums0.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums1[i], nums0[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums1[i] + nums0[i] &gt; 0</code></li>
	<li>The total sum of all elements in <code>nums1</code> and <code>nums0</code> does not exceed 2 * 10<sup>5</sup>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Greedy

Let the binary string corresponding to the $i$-th segment be $1^{x_i}0^{y_i}$, where $x_i = \textit{nums1}[i]$ and $y_i = \textit{nums0}[i]$.

The problem allows us to rearrange these segments arbitrarily, and the goal is to maximize the integer value represented by the final concatenated binary string. Since comparing binary strings by value is essentially equivalent to comparing them lexicographically, we want as many `1`s as possible to appear earlier.

Consider the relative order of two segments $A = 1^a0^b$ and $B = 1^c0^d$. If we concatenate them as $AB$ or $BA$, we should clearly choose the one with the larger lexicographical order.

Based on this rule, we can derive the following sorting strategy:

- If a segment satisfies $y = 0$, it consists only of some `1`s. Such segments should be placed as early as possible because they do not introduce any `0` prematurely. Among these segments, the one with more `1`s should come first.
- If two segments both satisfy $x > 0$ and $y > 0$, then the segment with more leading `1`s should come first, so we sort by $x$ in descending order. If $x$ is the same, then the segment with fewer `0`s should come first, so we sort by $y$ in ascending order.
- If a segment satisfies $x = 0$, it consists only of some `0`s. Such segments should be placed at the end.

After sorting in this way, the concatenated binary string is maximized.

Next, we do not need to actually construct the whole binary string. Let the total length of all concatenated segments be $m$. We preprocess $2^0, 2^1, \dots, 2^{m-1}$ modulo $10^9 + 7$. Then we traverse the segments in sorted order:

- When we encounter a `1`, we add the weight of the current highest bit to the answer.
- When we encounter a `0`, we only need to move the current position backward.

Finally, we obtain the answer.

The time complexity is $O(n \log n + m)$, and the space complexity is $O(n + m)$. Here, $n$ is the number of segments, and $m = \sum \textit{nums1}[i] + \sum \textit{nums0}[i]$. The problem guarantees that $m \le 2 \times 10^5$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxValue(self, nums1: list[int], nums0: list[int]) -> int:
        MOD = 10**9 + 7
        pairs = list(zip(nums1, nums0))
        b = sum(x + y for x, y in pairs)

        def key(p: tuple[int, int]) -> tuple[int, int, int]:
            x, y = p
            if y == 0:
                return (0, -x, 0)
            if x > 0:
                return (1, -x, y)
            return (2, y, 0)

        pairs.sort(key=key)

        ans = 0
        p = [1] * b
        for i in range(1, b):
            p[i] = p[i - 1] * 2 % MOD

        b -= 1
        for cnt1, cnt0 in pairs:
            while cnt1:
                ans = (ans + p[b]) % MOD
                b -= 1
                cnt1 -= 1
            b -= cnt0
        return ans
```

#### Java

```java
class Solution {
    private static final int MOD = 1_000_000_007;

    public int maxValue(int[] nums1, int[] nums0) {
        int n = nums1.length;
        int[][] pairs = new int[n][2];
        int b = 0;
        for (int i = 0; i < n; ++i) {
            pairs[i][0] = nums1[i];
            pairs[i][1] = nums0[i];
            b += nums1[i] + nums0[i];
        }

        Arrays.sort(pairs, (a, c) -> {
            int x1 = a[0], y1 = a[1];
            int x2 = c[0], y2 = c[1];
            int g1 = y1 == 0 ? 0 : x1 > 0 ? 1 : 2;
            int g2 = y2 == 0 ? 0 : x2 > 0 ? 1 : 2;
            if (g1 != g2) {
                return Integer.compare(g1, g2);
            }
            if (g1 == 0) {
                return Integer.compare(x2, x1);
            }
            if (g1 == 1) {
                if (x1 != x2) {
                    return Integer.compare(x2, x1);
                }
                return Integer.compare(y1, y2);
            }
            return Integer.compare(y1, y2);
        });

        long[] p = new long[b];
        p[0] = 1;
        for (int i = 1; i < b; ++i) {
            p[i] = p[i - 1] * 2 % MOD;
        }

        long ans = 0;
        --b;
        for (int[] pair : pairs) {
            int cnt1 = pair[0], cnt0 = pair[1];
            while (cnt1-- > 0) {
                ans = (ans + p[b--]) % MOD;
            }
            b -= cnt0;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    static constexpr int MOD = 1'000'000'007;

    int maxValue(vector<int>& nums1, vector<int>& nums0) {
        vector<pair<int, int>> pairs;
        int b = 0;
        for (int i = 0; i < nums1.size(); ++i) {
            pairs.emplace_back(nums1[i], nums0[i]);
            b += nums1[i] + nums0[i];
        }

        sort(pairs.begin(), pairs.end(), [](const auto& a, const auto& b) {
            auto group = [](const pair<int, int>& p) {
                if (p.second == 0) {
                    return 0;
                }
                if (p.first > 0) {
                    return 1;
                }
                return 2;
            };

            int g1 = group(a), g2 = group(b);
            if (g1 != g2) {
                return g1 < g2;
            }
            if (g1 == 0) {
                return a.first > b.first;
            }
            if (g1 == 1) {
                if (a.first != b.first) {
                    return a.first > b.first;
                }
                return a.second < b.second;
            }
            return a.second < b.second;
        });

        vector<long long> p(b, 1);
        for (int i = 1; i < b; ++i) {
            p[i] = p[i - 1] * 2 % MOD;
        }

        long long ans = 0;
        --b;
        for (auto& [cnt1, cnt0] : pairs) {
            while (cnt1--) {
                ans = (ans + p[b--]) % MOD;
            }
            b -= cnt0;
        }
        return (int) ans;
    }
};
```

#### Go

```go
const MOD int = 1_000_000_007

func maxValue(nums1 []int, nums0 []int) int {
	type pair struct{ x, y int }

	pairs := make([]pair, len(nums1))
	b := 0
	for i := range nums1 {
		pairs[i] = pair{nums1[i], nums0[i]}
		b += nums1[i] + nums0[i]
	}

	group := func(p pair) int {
		if p.y == 0 {
			return 0
		}
		if p.x > 0 {
			return 1
		}
		return 2
	}

	sort.Slice(pairs, func(i, j int) bool {
		a, b := pairs[i], pairs[j]
		g1, g2 := group(a), group(b)
		if g1 != g2 {
			return g1 < g2
		}
		if g1 == 0 {
			return a.x > b.x
		}
		if g1 == 1 {
			if a.x != b.x {
				return a.x > b.x
			}
			return a.y < b.y
		}
		return a.y < b.y
	})

	p := make([]int, b)
	p[0] = 1
	for i := 1; i < b; i++ {
		p[i] = p[i-1] * 2 % MOD
	}

	ans := 0
	b--
	for _, pr := range pairs {
		cnt1, cnt0 := pr.x, pr.y
		for cnt1 > 0 {
			ans = (ans + p[b]) % MOD
			b--
			cnt1--
		}
		b -= cnt0
	}
	return ans
}
```

#### TypeScript

```ts
function maxValue(nums1: number[], nums0: number[]): number {
    const MOD = 1_000_000_007;
    const pairs: [number, number][] = [];
    let b = 0;

    for (let i = 0; i < nums1.length; ++i) {
        pairs.push([nums1[i], nums0[i]]);
        b += nums1[i] + nums0[i];
    }

    const group = ([x, y]: [number, number]): number => {
        if (y === 0) {
            return 0;
        }
        if (x > 0) {
            return 1;
        }
        return 2;
    };

    pairs.sort((a, c) => {
        const g1 = group(a);
        const g2 = group(c);
        if (g1 !== g2) {
            return g1 - g2;
        }
        if (g1 === 0) {
            return c[0] - a[0];
        }
        if (g1 === 1) {
            if (a[0] !== c[0]) {
                return c[0] - a[0];
            }
            return a[1] - c[1];
        }
        return a[1] - c[1];
    });

    const p = Array<number>(b).fill(1);
    for (let i = 1; i < b; ++i) {
        p[i] = (p[i - 1] * 2) % MOD;
    }

    let ans = 0;
    --b;
    for (let [cnt1, cnt0] of pairs) {
        while (cnt1 > 0) {
            ans = (ans + p[b]) % MOD;
            --b;
            --cnt1;
        }
        b -= cnt0;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

---
comments: true
difficulty: Hard
---

<!-- problem:start -->

# [4051. Count Subarrays with Distant Sums](https://leetcode.com/problems/count-subarrays-with-distant-sums)

[中文文档](/solution/4000-4099/4051.Count%20Subarrays%20with%20Distant%20Sums/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and two integers <code>goal</code> and <code>k</code>.</p>

<p>A <strong>subarray</strong> <code>nums[i..j]</code> is considered <strong>distant</strong> if the <strong>absolute difference</strong> between its sum and <code>goal</code> is <strong>at least</strong> <code>k</code>.</p>

<p>Return the number of <strong>distant</strong> subarrays.</p>

<p>A <strong>subarray</strong> is a contiguous <strong>non-empty</strong> sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,1], goal = 4, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>The distant subarrays for <code>k = 1</code> are:</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>j</code></th>
			<th style="border: 1px solid black;"><code>nums[i..j]</code></th>
			<th style="border: 1px solid black;">Sum</th>
			<th style="border: 1px solid black;"><code>abs(sum - goal)</code></th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;"><code>[1]</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>[2]</code></td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>[1]</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>[1, 2]</code></td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>[2, 1]</code></td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
	</tbody>
</table>

<p>Thus, the answer is 5.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,-1,3], goal = 2, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The distant subarrays for <code>k = 2</code> are:</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>j</code></th>
			<th style="border: 1px solid black;"><code>nums[i..j]</code></th>
			<th style="border: 1px solid black;">Sum</th>
			<th style="border: 1px solid black;"><code>abs(sum - goal)</code></th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>[-1]</code></td>
			<td style="border: 1px solid black;">-1</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>[2, -1, 3]</code></td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
	</tbody>
</table>

<p>Thus, the answer is 2.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-3,1,2], goal = 0, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The distant subarrays for <code>k = 3</code> are:</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>j</code></th>
			<th style="border: 1px solid black;"><code>nums[i..j]</code></th>
			<th style="border: 1px solid black;">Sum</th>
			<th style="border: 1px solid black;"><code>abs(sum - goal)</code></th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;"><code>[-3]</code></td>
			<td style="border: 1px solid black;">-3</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>[1, 2]</code></td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
	</tbody>
</table>

<p>Thus, the answer is 2.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= goal &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum + Binary Indexed Tree

<!-- thinking:start -->

> **Thinking**
>
> There are quadratically many subarrays, so $n = 10^5$ rules out enumeration. The complement of $|sum - \textit{goal}| \ge k$ is $|sum - \textit{goal}| < k$; subtracting that count from the total is cleaner.
>
> Prefix sums turn a subarray sum into a difference of two points. For each right endpoint we need how many earlier prefix sums fall inside a numeric interval.
>
> After sorting the prefix sums we binary-search the Fenwick indices, query the interval, then insert the current value.

<!-- thinking:end -->

Let $s$ be the prefix-sum array of $\textit{nums}$ ($s[0] = 0$). The sum of the subarray $\textit{nums}[L..R-1]$ is $s[R] - s[L]$, and it is distant if and only if $|s[R] - s[L] - \textit{goal}| \ge k$.

There are $\frac{n(n+1)}{2}$ nonempty subarrays. We count those that fail the condition, i.e. $|s[R] - s[L] - \textit{goal}| < k$, and subtract that count from the total.

The inequality is equivalent to

$$
s[R] - \textit{goal} - k < s[L] < s[R] - \textit{goal} + k
$$

that is, $s[L]$ lies in the closed interval $[s[R] - \textit{goal} - k + 1,\, s[R] - \textit{goal} + k - 1]$.

Enumerate the prefix sums $v = s[R]$ from left to right. Among the prefix sums already inserted, query how many fall in $[a, b]$ and subtract that from the answer, then insert $v$. For discretization we sort $s$ and locate Binary Indexed Tree indices by binary search.

The time complexity is $O(n \times \log n)$ and the space complexity is $O(n)$, where $n$ is the length of $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class BinaryIndexedTree:
    __slots__ = "n", "c"

    def __init__(self, n: int):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x: int, delta: int) -> None:
        while x <= self.n:
            self.c[x] += delta
            x += x & -x

    def query(self, x: int) -> int:
        s = 0
        while x:
            s += self.c[x]
            x -= x & -x
        return s


class Solution:
    def distantSubarrays(self, nums: list[int], goal: int, k: int) -> int:
        s = list(accumulate(nums, initial=0))
        st = sorted(s)
        n = len(nums)
        ans = (1 + n) * n // 2
        bit = BinaryIndexedTree(len(st) + 1)
        for v in s:
            a = v - goal - k + 1
            b = v - goal + k - 1

            l = bisect_left(st, a) + 1
            r = bisect_left(st, b + 1)
            if l <= r:
                ans -= bit.query(r) - bit.query(l - 1)
            bit.update(bisect_left(st, v) + 1, 1)
        return ans
```

#### Java

```java
class BinaryIndexedTree {
    private final int n;
    private final long[] c;

    BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new long[n + 1];
    }

    void update(int x, long delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    long query(int x) {
        long s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
}

class Solution {
    public long distantSubarrays(int[] nums, int goal, int k) {
        int n = nums.length;
        long[] s = new long[n + 1];

        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + nums[i];
        }

        long[] st = s.clone();
        Arrays.sort(st);

        long ans = (long) n * (n + 1) / 2;
        BinaryIndexedTree bit = new BinaryIndexedTree(st.length + 1);

        for (long v : s) {
            long a = v - goal - k + 1L;
            long b = v - goal + k - 1L;

            int l = lowerBound(st, a) + 1;
            int r = lowerBound(st, b + 1);

            if (l <= r) {
                ans -= bit.query(r) - bit.query(l - 1);
            }

            bit.update(lowerBound(st, v) + 1, 1);
        }

        return ans;
    }

    private int lowerBound(long[] nums, long target) {
        int l = 0;
        int r = nums.length;

        while (l < r) {
            int m = (l + r) >>> 1;
            if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        return l;
    }
}
```

#### C++

```cpp
class BinaryIndexedTree {
    int n;
    vector<long long> c;

public:
    BinaryIndexedTree(int n)
        : n(n)
        , c(n + 1) {}

    void update(int x, long long delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    long long query(int x) {
        long long s = 0;
        while (x) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
};

class Solution {
public:
    long long distantSubarrays(vector<int>& nums, int goal, int k) {
        int n = nums.size();
        vector<long long> s(n + 1);

        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + nums[i];
        }

        vector<long long> st = s;
        sort(st.begin(), st.end());

        long long ans = 1LL * n * (n + 1) / 2;
        BinaryIndexedTree bit(st.size() + 1);

        for (long long v : s) {
            long long a = v - goal - k + 1LL;
            long long b = v - goal + k - 1LL;

            int l = lower_bound(st.begin(), st.end(), a) - st.begin() + 1;
            int r = lower_bound(st.begin(), st.end(), b + 1) - st.begin();

            if (l <= r) {
                ans -= bit.query(r) - bit.query(l - 1);
            }

            int pos = lower_bound(st.begin(), st.end(), v) - st.begin() + 1;
            bit.update(pos, 1);
        }

        return ans;
    }
};
```

#### Go

```go
type BinaryIndexedTree struct {
	n int
	c []int
}

func NewBinaryIndexedTree(n int) *BinaryIndexedTree {
	return &BinaryIndexedTree{
		n: n,
		c: make([]int, n+1),
	}
}

func (t *BinaryIndexedTree) update(x, delta int) {
	for x <= t.n {
		t.c[x] += delta
		x += x & -x
	}
}

func (t *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += t.c[x]
		x -= x & -x
	}
	return s
}

func distantSubarrays(nums []int, goal int, k int) int64 {
	n := len(nums)
	s := make([]int, n+1)

	for i, x := range nums {
		s[i+1] = s[i] + x
	}

	st := append([]int(nil), s...)
	sort.Ints(st)

	ans := n * (n + 1) / 2
	bit := NewBinaryIndexedTree(len(st) + 1)

	for _, v := range s {
		a := v - goal - k + 1
		b := v - goal + k - 1

		l := sort.SearchInts(st, a) + 1
		r := sort.SearchInts(st, b+1)

		if l <= r {
			ans -= bit.query(r) - bit.query(l-1)
		}

		bit.update(sort.SearchInts(st, v)+1, 1)
	}

	return int64(ans)
}
```

#### TypeScript

```ts
class BinaryIndexedTree {
    private readonly n: number;
    private readonly c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = new Array(n + 1).fill(0);
    }

    update(x: number, delta: number): void {
        while (x <= this.n) {
            this.c[x] += delta;
            x += x & -x;
        }
    }

    query(x: number): number {
        let s = 0;
        while (x > 0) {
            s += this.c[x];
            x -= x & -x;
        }
        return s;
    }
}

function distantSubarrays(nums: number[], goal: number, k: number): number {
    const n = nums.length;
    const s = new Array<number>(n + 1).fill(0);

    for (let i = 0; i < n; i++) {
        s[i + 1] = s[i] + nums[i];
    }

    const st = [...s].sort((a, b) => a - b);

    let ans = (n * (n + 1)) / 2;
    const bit = new BinaryIndexedTree(st.length + 1);

    for (const v of s) {
        const a = v - goal - k + 1;
        const b = v - goal + k - 1;

        const l = _.sortedIndex(st, a) + 1;
        const r = _.sortedIndex(st, b + 1);

        if (l <= r) {
            ans -= bit.query(r) - bit.query(l - 1);
        }

        bit.update(_.sortedIndex(st, v) + 1, 1);
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

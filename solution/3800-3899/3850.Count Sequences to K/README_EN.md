---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3850.Count%20Sequences%20to%20K/README_EN.md
---

<!-- problem:start -->

# [3850. Count Sequences to K](https://leetcode.com/problems/count-sequences-to-k)

[中文文档](/solution/3800-3899/3850.Count%20Sequences%20to%20K/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>, and an integer <code>k</code>.</p>

<p>Start with an initial value <code>val = 1</code> and process <code>nums</code> from left to right. At each index <code>i</code>, you must choose <strong>exactly one</strong> of the following actions:</p>

<ul>
	<li>Multiply <code>val</code> by <code>nums[i]</code>.</li>
	<li>Divide <code>val</code> by <code>nums[i]</code>.</li>
	<li>Leave <code>val</code> unchanged.</li>
</ul>

<p>After processing all elements, <code>val</code> is considered <strong>equal</strong> to <code>k</code> only if its final rational value <strong>exactly</strong> equals <code>k</code>.</p>

<p>Return the count of <strong>distinct</strong> sequences of choices that result in <code>val == k</code>.</p>

<p><strong>Note:</strong> Division is rational (exact), not integer division. For example, <code>2 / 4 = 1 / 2</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,2], k = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The following 2 distinct sequences of choices result in <code>val == k</code>:</p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Sequence</th>
			<th style="border: 1px solid black;">Operation on <code>nums[0]</code></th>
			<th style="border: 1px solid black;">Operation on <code>nums[1]</code></th>
			<th style="border: 1px solid black;">Operation on <code>nums[2]</code></th>
			<th style="border: 1px solid black;">Final <code>val</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">Multiply: <code>val = 1 * 2 = 2</code></td>
			<td style="border: 1px solid black;">Multiply: <code>val = 2 * 3 = 6</code></td>
			<td style="border: 1px solid black;">Leave <code>val</code> unchanged</td>
			<td style="border: 1px solid black;">6</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">Leave <code>val</code> unchanged</td>
			<td style="border: 1px solid black;">Multiply: <code>val = 1 * 3 = 3</code></td>
			<td style="border: 1px solid black;">Multiply: <code>val = 3 * 2 = 6</code></td>
			<td style="border: 1px solid black;">6</td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,6,3], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The following 2 distinct sequences of choices result in <code>val == k</code>:</p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Sequence</th>
			<th style="border: 1px solid black;">Operation on <code>nums[0]</code></th>
			<th style="border: 1px solid black;">Operation on <code>nums[1]</code></th>
			<th style="border: 1px solid black;">Operation on <code>nums[2]</code></th>
			<th style="border: 1px solid black;">Final <code>val</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">Multiply: <code>val = 1 * 4 = 4</code></td>
			<td style="border: 1px solid black;">Divide: <code>val = 4 / 6 = 2 / 3</code></td>
			<td style="border: 1px solid black;">Multiply: <code>val = (2 / 3) * 3 = 2</code></td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">Leave <code>val</code> unchanged</td>
			<td style="border: 1px solid black;">Multiply: <code>val = 1 * 6 = 6</code></td>
			<td style="border: 1px solid black;">Divide: <code>val = 6 / 3 = 2</code></td>
			<td style="border: 1px solid black;">2</td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,5], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The following 3 distinct sequences of choices result in <code>val == k</code>:</p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Sequence</th>
			<th style="border: 1px solid black;">Operation on <code>nums[0]</code></th>
			<th style="border: 1px solid black;">Operation on <code>nums[1]</code></th>
			<th style="border: 1px solid black;">Final <code>val</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">Multiply: <code>val = 1 * 1 = 1</code></td>
			<td style="border: 1px solid black;">Leave <code>val</code> unchanged</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">Divide: <code>val = 1 / 1 = 1</code></td>
			<td style="border: 1px solid black;">Leave <code>val</code> unchanged</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">Leave <code>val</code> unchanged</td>
			<td style="border: 1px solid black;">Leave <code>val</code> unchanged</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
	</tbody>
</table>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 19</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 6</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization Search

We define a function $\text{dfs}(i, p, q)$ that represents the number of different choice sequences when processing at index $i$ with the current rational value being $\frac{p}{q}$. Initially, $\text{dfs}(0, 1, 1)$ represents starting from the initial value of $1$.

For each index $i$, we have three choices:

1. Keep it unchanged, i.e., $\text{dfs}(i + 1, p, q)$.
2. Multiply by $nums[i]$, i.e., $\text{dfs}(i + 1, p \cdot nums[i], q)$.
3. Divide by $nums[i]$, i.e., $\text{dfs}(i + 1, p, q \cdot nums[i])$.

To avoid excessively large numbers, we simplify the numerator and denominator after each multiplication or division. Finally, when $i$ equals $n$, if $\frac{p}{q}$ exactly equals $k$, we return $1$; otherwise, we return $0$.

The time complexity is $O(n^4 + \log k)$, and the space complexity is $O(n^4)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSequences(self, nums: List[int], k: int) -> int:
        @cache
        def dfs(i: int, p: int, q: int) -> int:
            if i == n:
                return 1 if p == k and q == 1 else 0
            x = nums[i]
            res = dfs(i + 1, p, q)
            g = gcd(p * x, q)
            res += dfs(i + 1, p * x // g, q // g)
            g = gcd(p, q * x)
            res += dfs(i + 1, p // g, q * x // g)
            return res

        n = len(nums)
        ans = dfs(0, 1, 1)
        dfs.cache_clear()
        return ans
```

#### Java

```java
class Solution {

    record State(int i, long p, long q) {
    }

    private Map<State, Integer> f;
    private int[] nums;
    private int n;
    private long k;

    public int countSequences(int[] nums, long k) {
        this.nums = nums;
        this.n = nums.length;
        this.k = k;
        this.f = new HashMap<>();
        return dfs(0, 1L, 1L);
    }

    private int dfs(int i, long p, long q) {
        if (i == n) {
            return (p == k && q == 1L) ? 1 : 0;
        }

        State key = new State(i, p, q);
        if (f.containsKey(key)) {
            return f.get(key);
        }

        int res = dfs(i + 1, p, q);

        long x = nums[i];

        long g1 = gcd(p * x, q);
        res += dfs(i + 1, (p * x) / g1, q / g1);

        long g2 = gcd(p, q * x);
        res += dfs(i + 1, p / g2, (q * x) / g2);

        f.put(key, res);
        return res;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int n;
    long long k;
    vector<int>* nums;
    map<tuple<int, long long, long long>, int> f;

    int countSequences(vector<int>& nums, long long k) {
        this->nums = &nums;
        this->n = nums.size();
        this->k = k;
        f.clear();
        return dfs(0, 1LL, 1LL);
    }

    int dfs(int i, long long p, long long q) {
        if (i == n) {
            return (p == k && q == 1LL) ? 1 : 0;
        }

        auto key = make_tuple(i, p, q);
        if (f.count(key)) return f[key];

        int res = dfs(i + 1, p, q);

        long long x = (*nums)[i];

        long long g1 = gcd(p * x, q);
        res += dfs(i + 1, (p * x) / g1, q / g1);

        long long g2 = gcd(p, q * x);
        res += dfs(i + 1, p / g2, (q * x) / g2);

        f[key] = res;
        return res;
    }
};
```

#### Go

```go
func countSequences(nums []int, k int64) int {
	n := len(nums)
	type state struct {
		i int
		p int64
		q int64
	}
	f := make(map[state]int)

	var gcd func(int64, int64) int64
	gcd = func(a, b int64) int64 {
		for b != 0 {
			a, b = b, a%b
		}
		return a
	}

	var dfs func(int, int64, int64) int
	dfs = func(i int, p int64, q int64) int {
		if i == n {
			if p == k && q == 1 {
				return 1
			}
			return 0
		}

		key := state{i, p, q}
		if v, ok := f[key]; ok {
			return v
		}

		res := dfs(i+1, p, q)

		x := int64(nums[i])

		g1 := gcd(p*x, q)
		res += dfs(i+1, (p*x)/g1, q/g1)

		g2 := gcd(p, q*x)
		res += dfs(i+1, p/g2, (q*x)/g2)

		f[key] = res
		return res
	}

	return dfs(0, 1, 1)
}
```

#### TypeScript

```ts
function countSequences(nums: number[], k: number): number {
    const n = nums.length;
    const f = new Map<string, number>();

    function gcd(a: number, b: number): number {
        while (b !== 0) {
            const t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    function dfs(i: number, p: number, q: number): number {
        if (i === n) {
            return p === k && q === 1 ? 1 : 0;
        }

        const key = `${i},${p},${q}`;
        if (f.has(key)) return f.get(key)!;

        let res = dfs(i + 1, p, q);

        const x = nums[i];

        const g1 = gcd(p * x, q);
        res += dfs(i + 1, (p * x) / g1, q / g1);

        const g2 = gcd(p, q * x);
        res += dfs(i + 1, p / g2, (q * x) / g2);

        f.set(key, res);
        return res;
    }

    return dfs(0, 1, 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

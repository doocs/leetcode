---
comments: true
difficulty: Hard
rating: 2816
source: Biweekly Contest 116 Q4
tags:
    - Binary Indexed Tree
    - Segment Tree
    - Array
    - Hash Table
---

<!-- problem:start -->

# [2916. Subarrays Distinct Element Sum of Squares II](https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-ii)

[中文文档](/solution/2900-2999/2916.Subarrays%20Distinct%20Element%20Sum%20of%20Squares%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed </strong>integer array <code>nums</code>.</p>

<p>The <strong>distinct count</strong> of a subarray of <code>nums</code> is defined as:</p>

<ul>
	<li>Let <code>nums[i..j]</code> be a subarray of <code>nums</code> consisting of all the indices from <code>i</code> to <code>j</code> such that <code>0 &lt;= i &lt;= j &lt; nums.length</code>. Then the number of distinct values in <code>nums[i..j]</code> is called the distinct count of <code>nums[i..j]</code>.</li>
</ul>

<p>Return <em>the sum of the <strong>squares</strong> of <strong>distinct counts</strong> of all subarrays of </em><code>nums</code>.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>A subarray is a contiguous <strong>non-empty</strong> sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,1]
<strong>Output:</strong> 15
<strong>Explanation:</strong> Six possible subarrays are:
[1]: 1 distinct value
[2]: 1 distinct value
[1]: 1 distinct value
[1,2]: 2 distinct values
[2,1]: 2 distinct values
[1,2,1]: 2 distinct values
The sum of the squares of the distinct counts in all subarrays is equal to 1<sup>2</sup> + 1<sup>2</sup> + 1<sup>2</sup> + 2<sup>2</sup> + 2<sup>2</sup> + 2<sup>2</sup> = 15.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Three possible subarrays are:
[2]: 1 distinct value
[2]: 1 distinct value
[2,2]: 1 distinct value
The sum of the squares of the distinct counts in all subarrays is equal to 1<sup>2</sup> + 1<sup>2</sup> + 1<sup>2</sup> = 3.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> The sum is the same as part I, but $n \le 10^5$ forbids enumerating subarrays. When the right end moves from $r-1$ to $r$, the new slices are all $[L,r]$. If $x=nums[r]$ last occurred at $p$, every $L \in (p,r]$ gains one distinct value, so the square sum grows by $2 \cdot \mathrm{cnt}+1$.
>
> That is a range add on indices together with a sum-of-squares query, which a lazy segment tree can store. Scan by right end, add one on $(last[x], r]$, and accumulate the global square sum. The code tabs in this directory are still empty; the structure above is the intended state and update order.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java
class Solution {
    private static final long MOD = 1000000007L;
    private long[] sum;
    private long[] sumSq;
    private long[] lazy;
    public int sumCounts(int[] nums) {
        int n = nums.length;
        int size = 4 * n;
        sum = new long[size];
        sumSq = new long[size];
        lazy = new long[size];

        Map<Integer, Integer> last = new HashMap<>();
        long ans = 0;

        for (int r = 0; r < n; r++) {
            int x = nums[r];
            int p = last.getOrDefault(x, -1);
            update(1, 0, n - 1, p + 1, r, 1L);
            last.put(x, r);
            ans = (ans + sumSq[1]) % MOD;
        }

        return (int) ans;
    }
    private void apply(int node, int l, int r, long v) {
        long len = r - l + 1;
        sumSq[node] = (sumSq[node] + 2L * v % MOD * sum[node] % MOD + v * v % MOD * len % MOD) % MOD;
        sum[node] = (sum[node] + v * len) % MOD;
        lazy[node] = (lazy[node] + v) % MOD;
    }
    private void pushDown(int node, int l, int r) {
        if (lazy[node] != 0 && l != r) {
            int mid = (l + r) >> 1;
            long v = lazy[node];
            apply(node << 1, l, mid, v);
            apply(node << 1 | 1, mid + 1, r, v);
            lazy[node] = 0;
        }
    }
    private void update(int node, int l, int r, int ql, int qr, long v) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            apply(node, l, r, v);
            return;
        }
        pushDown(node, l, r);
        int mid = (l + r) >> 1;
        update(node << 1, l, mid, ql, qr, v);
        update(node << 1 | 1, mid + 1, r, ql, qr, v);
        sum[node] = (sum[node << 1] + sum[node << 1 | 1]) % MOD;
        sumSq[node] = (sumSq[node << 1] + sumSq[node << 1 | 1]) % MOD;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int sumCounts(vector<int>& nums) {
        const long long MOD = 1000000007LL;
        int n = nums.size();
        vector<long long> sum(4 * n, 0);     // sum of cnt
        vector<long long> sumSq(4 * n, 0);   // sum of cnt^2
        vector<long long> lazy(4 * n, 0);    // pending add
        function<void(int,int,int,long long)> apply = [&](int node, int l, int r, long long v) {
            long long len = r - l + 1;
            sumSq[node] = (sumSq[node] + 2LL * v % MOD * sum[node] % MOD + v * v % MOD * len % MOD) % MOD;
            sum[node] = (sum[node] + v * len) % MOD;
            lazy[node] = (lazy[node] + v) % MOD;
        };
        function<void(int,int,int)> pushDown = [&](int node, int l, int r) {
            if (lazy[node] != 0 && l != r) {
                int mid = (l + r) >> 1;
                long long v = lazy[node];
                apply(node << 1, l, mid, v);
                apply(node << 1 | 1, mid + 1, r, v);
                lazy[node] = 0;
            }
        };
        function<void(int,int,int,int,int,long long)> update = [&](int node, int l, int r, int ql, int qr, long long v) {
            if (ql > r || qr < l) return;
            if (ql <= l && r <= qr) {
                apply(node, l, r, v);
                return;
            }
            pushDown(node, l, r);
            int mid = (l + r) >> 1;
            update(node << 1, l, mid, ql, qr, v);
            update(node << 1 | 1, mid + 1, r, ql, qr, v);
            sum[node] = (sum[node << 1] + sum[node << 1 | 1]) % MOD;
            sumSq[node] = (sumSq[node << 1] + sumSq[node << 1 | 1]) % MOD;
        };
        unordered_map<int, int> last;
        long long ans = 0;
        for (int r = 0; r < n; r++) {
            int x = nums[r];
            int p = last.count(x) ? last[x] : -1;
            update(1, 0, n - 1, p + 1, r, 1LL);
            last[x] = r;
            ans = (ans + sumSq[1]) % MOD;
        }
        return (int)ans;
    }
};
```

#### Go

```go
func sumCounts(nums []int) int {
	const MOD = 1000000007
	n := len(nums)
	size := 4 * n
	sum := make([]int64, size)
	sumSq := make([]int64, size)
	lazy := make([]int64, size)   
	var apply func(node, l, r int, v int64)
	apply = func(node, l, r int, v int64) {
		length := int64(r - l + 1)
		sumSq[node] = (sumSq[node] + 2*v*sum[node] + v*v%MOD*length) % MOD
		sum[node] = (sum[node] + v*length) % MOD
		lazy[node] = (lazy[node] + v) % MOD
	}
	var pushDown func(node, l, r int)
	pushDown = func(node, l, r int) {
		if lazy[node] != 0 && l != r {
			mid := (l + r) >> 1
			v := lazy[node]
			apply(node<<1, l, mid, v)
			apply(node<<1|1, mid+1, r, v)
			lazy[node] = 0
		}
	}
	var update func(node, l, r, ql, qr int, v int64)
	update = func(node, l, r, ql, qr int, v int64) {
		if ql > r || qr < l {
			return
		}
		if ql <= l && r <= qr {
			apply(node, l, r, v)
			return
		}
		pushDown(node, l, r)
		mid := (l + r) >> 1
		update(node<<1, l, mid, ql, qr, v)
		update(node<<1|1, mid+1, r, ql, qr, v)
		sum[node] = (sum[node<<1] + sum[node<<1|1]) % MOD
		sumSq[node] = (sumSq[node<<1] + sumSq[node<<1|1]) % MOD
	}
	last := make(map[int]int)
	var ans int64 = 0
	for r := 0; r < n; r++ {
		x := nums[r]
		p := -1
		if v, ok := last[x]; ok {
			p = v
		}
		update(1, 0, n-1, p+1, r, 1)
		last[x] = r
		ans = (ans + sumSq[1]) % MOD
	}
	return int(ans)
}
```
#### TypeScript

```ts
function sumCounts(nums: number[]): number {
    const MOD = 1000000007n;
    const n = nums.length;
    const size = 4 * n;
    const sum = new Array<bigint>(size).fill(0n);      // sum of cnt
    const sumSq = new Array<bigint>(size).fill(0n);    // sum of cnt^2
    const lazy = new Array<bigint>(size).fill(0n);     // pending add to cnt

    const build = (node: number, l: number, r: number): void => {
        if (l === r) {
            sum[node] = 0n;
            sumSq[node] = 0n;
            return;
        }
        const mid = (l + r) >> 1;
        build(node << 1, l, mid);
        build((node << 1) | 1, mid + 1, r);
    };

    const apply = (node: number, l: number, r: number, v: bigint): void => {
        const len = BigInt(r - l + 1);
        sumSq[node] = (sumSq[node] + 2n * v * sum[node] + v * v * len) % MOD;
        sum[node] = (sum[node] + v * len) % MOD;
        lazy[node] = (lazy[node] + v) % MOD;
    };

    const pushDown = (node: number, l: number, r: number): void => {
        if (lazy[node] !== 0n && l !== r) {
            const mid = (l + r) >> 1;
            const v = lazy[node];
            apply(node << 1, l, mid, v);
            apply((node << 1) | 1, mid + 1, r, v);
            lazy[node] = 0n;
        }
    };

    const update = (node: number, l: number, r: number, ql: number, qr: number, v: bigint): void => {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            apply(node, l, r, v);
            return;
        }
        pushDown(node, l, r);
        const mid = (l + r) >> 1;
        update(node << 1, l, mid, ql, qr, v);
        update((node << 1) | 1, mid + 1, r, ql, qr, v);
        sum[node] = (sum[node << 1] + sum[(node << 1) | 1]) % MOD;
        sumSq[node] = (sumSq[node << 1] + sumSq[(node << 1) | 1]) % MOD;
    };

    build(1, 0, n - 1);

    const last = new Map<number, number>();
    let ans = 0n;

    for (let r = 0; r < n; r++) {
        const x = nums[r];
        const p = last.has(x) ? last.get(x)! : -1;
        // All L in (p, r] gain one distinct value
        update(1, 0, n - 1, p + 1, r, 1n);
        last.set(x, r);
        ans = (ans + sumSq[1]) % MOD;
    }

    return Number(ans);
}
```
<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

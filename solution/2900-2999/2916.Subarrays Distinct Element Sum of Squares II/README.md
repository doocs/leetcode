---
comments: true
difficulty: 困难
rating: 2816
source: 第 116 场双周赛 Q4
tags:
    - 树状数组
    - 线段树
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [2916. 子数组不同元素数目的平方和 II](https://leetcode.cn/problems/subarrays-distinct-element-sum-of-squares-ii)

[English Version](/solution/2900-2999/2916.Subarrays%20Distinct%20Element%20Sum%20of%20Squares%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>定义 <code>nums</code>&nbsp;一个子数组的 <strong>不同计数</strong>&nbsp;值如下：</p>

<ul>
	<li>令&nbsp;<code>nums[i..j]</code>&nbsp;表示 <code>nums</code> 中所有下标在 <code>i</code> 到 <code>j</code> 范围内的元素构成的子数组（满足 <code>0 &lt;= i &lt;= j &lt; nums.length</code> ），那么我们称子数组&nbsp;<code>nums[i..j]</code>&nbsp;中不同值的数目为&nbsp;<code>nums[i..j]</code>&nbsp;的不同计数。</li>
</ul>

<p>请你返回 <code>nums</code>&nbsp;中所有子数组的 <strong>不同计数</strong>&nbsp;的 <strong>平方</strong>&nbsp;和。</p>

<p>由于答案可能会很大，请你将它对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p>子数组指的是一个数组里面一段连续 <strong>非空</strong>&nbsp;的元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,1]
<b>输出：</b>15
<b>解释：</b>六个子数组分别为：
[1]: 1 个互不相同的元素。
[2]: 1 个互不相同的元素。
[1]: 1 个互不相同的元素。
[1,2]: 2 个互不相同的元素。
[2,1]: 2 个互不相同的元素。
[1,2,1]: 2 个互不相同的元素。
所有不同计数的平方和为 1<sup>2</sup> + 1<sup>2</sup> + 1<sup>2</sup> + 2<sup>2</sup> + 2<sup>2</sup> + 2<sup>2</sup> = 15 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2,2]
<b>输出：3</b>
<strong>解释：</strong>三个子数组分别为：
[2]: 1 个互不相同的元素。
[2]: 1 个互不相同的元素。
[2,2]: 1 个互不相同的元素。
所有不同计数的平方和为 1<sup>2</sup> + 1<sup>2</sup> + 1<sup>2</sup> = 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 题意与 I 相同，但 $n \le 10^5$，枚举子数组并维护集合不可行。将右端点从 $r-1$ 推到 $r$ 时，新增的子数组是所有 $[L,r]$。若 $x=nums[r]$ 上次出现在 $p$，则 $L \in (p,r]$ 的不同计数加一，从而平方和按 $2 \cdot \mathrm{cnt}+1$ 增加。
>
> 这是对一段下标的区间加，并询问全体平方和，可用懒标记线段树维护「不同计数」与其平方和。按右端扫描，每次在 $(last[x], r]$ 上加一并累加全局平方和。仓库中代码页尚未填入实现，上述结构即该做法的状态与操作顺序。

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
    const sum = new Array<bigint>(size).fill(0n);     
    const sumSq = new Array<bigint>(size).fill(0n);    
    const lazy = new Array<bigint>(size).fill(0n);     
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

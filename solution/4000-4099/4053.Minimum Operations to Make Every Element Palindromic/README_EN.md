---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4053. Minimum Operations to Make Every Element Palindromic](https://leetcode.com/problems/minimum-operations-to-make-every-element-palindromic)

[中文文档](/solution/4000-4099/4053.Minimum%20Operations%20to%20Make%20Every%20Element%20Palindromic/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>In one <strong>operation</strong>, you may choose an index <code>i</code> and either increment or decrement <code>nums[i]</code> by 2.</p>

<p>Return the <strong>minimum</strong> number of operations required to make every element in <code>nums</code> a <strong>positive</strong> <span data-keyword="palindrome-integer">palindrome</span>. Different elements may be changed into different palindromic integers.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [10,12,14,16]</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal sequence of operations is:</p>

<ul>
	<li>Decrement <code>nums[0]</code> by 2 once to change it from 10 to 8.</li>
	<li>Decrement <code>nums[1]</code> by 2 twice to change it from 12 to 8.</li>
	<li>Decrement <code>nums[2]</code> by 2 three times to change it from 14 to 8.</li>
	<li>Increment <code>nums[3]</code> by 2 three times to change it from 16 to 22.</li>
</ul>

<p>After <code>1 + 2 + 3 + 3 = 9</code> operations, <code>nums = [8, 8, 8, 22]</code>, and every element is a positive palindromic integer.</p>

<p>It can be shown that fewer than 9 operations cannot achieve this.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [9,10,11,10]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Decrement <code>nums[1]</code> and <code>nums[3]</code> by 2 once each.</p>

<p>After 2 operations, <code>nums = [9, 8, 11, 8]</code>, and every element is a positive palindromic integer.</p>

<p>At least one operation is needed for each of these two elements, so the minimum number of operations is 2.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [125]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Decrement <code>nums[0]</code> by 2 twice to change it from 125 to 121, which is a positive palindromic integer.</p>

<p>A single operation would change it to 123 or 127, neither of which is palindromic. Thus, the minimum number of operations is 2.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Precompute Palindromes + Binary Search

<!-- thinking:start -->

> **Thinking**
>
> Each operation adds or subtracts $2$, so parity never changes: $\textit{nums}[i]$ can only become a positive palindrome of the same parity. The elements are independent, and the answer is the sum of each value's distance to the nearest same-parity palindrome, divided by $2$.
>
> With $n = 10^5$ and values up to $10^9$, walking from $x$ by steps of $2$ until a palindrome appears is too slow.
>
> Every palindrome is a mirrored prefix. Enumerating prefixes $1 \ldots 10^5$ and forming both even-length and odd-length palindromes covers everything around $10^9$. Split them by parity, sort each list, and binary-search the nearest neighbor for every $x$.

<!-- thinking:end -->

An operation increments or decrements an element by $2$, so its parity is invariant and the target palindrome must have the same parity. The elements are independent: for each $x$, find the nearest same-parity positive palindrome $p$ and add $\lvert x - p \rvert / 2$.

During preprocessing, enumerate prefixes $i = 1, 2, \ldots, 10^5$ and let $s$ be the decimal representation of $i$:

- Even-length palindrome: $s + \mathrm{reverse}(s)$
- Odd-length palindrome: $s + \mathrm{reverse}(s[:-1])$

Store them in two lists by parity and sort each list. This range covers all palindromes with up to about $12$ digits, which is enough for values up to $10^9$.

For each $x$, binary-search the first palindrome that is at least $x$ in the same-parity list, compare it with the previous one, and take the smaller distance divided by $2$.

Let $M$ be the number of palindromes (about $2 \times 10^5$). Preprocessing takes $O(M \log M)$ and each query takes $O(\log M)$. The overall time complexity is $O(M \log M + n \log M)$, and the space complexity is $O(M)$.

<!-- tabs:start -->

#### Python3

```python
ps = [[], []]
for i in range(1, 10**5 + 1):
    s = str(i)
    t1 = s[::-1]
    t2 = s[:-1][::-1]
    x = int(s + t1)
    ps[x & 1].append(x)
    y = int(s + t2)
    ps[y & 1].append(y)
for p in ps:
    p.sort()


class Solution:
    def minOperations(self, nums: list[int]) -> int:
        ans = 0
        for x in nums:
            p = ps[x & 1]
            i = bisect_left(p, x)
            t = inf
            if i < len(p):
                t = p[i] - x
            if i:
                t = min(t, x - p[i - 1])
            ans += t // 2
        return ans
```

#### Java

```java
class Solution {
    static List<Long>[] ps = new ArrayList[2];

    static {
        ps[0] = new ArrayList<>();
        ps[1] = new ArrayList<>();
        for (int i = 1; i <= 100000; i++) {
            String s = String.valueOf(i);
            String t1 = new StringBuilder(s).reverse().toString();
            String t2 = new StringBuilder(s.substring(0, s.length() - 1)).reverse().toString();
            long x = Long.parseLong(s + t1);
            ps[(int) (x & 1)].add(x);
            long y = Long.parseLong(s + t2);
            ps[(int) (y & 1)].add(y);
        }
        ps[0].sort(Long::compare);
        ps[1].sort(Long::compare);
    }

    public long minOperations(int[] nums) {
        long ans = 0;
        for (int x : nums) {
            List<Long> p = ps[x & 1];
            int l = 0, r = p.size();
            while (l < r) {
                int m = (l + r) >>> 1;
                if (p.get(m) < x) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            long t = Long.MAX_VALUE;
            if (l < p.size()) {
                t = p.get(l) - x;
            }
            if (l > 0) {
                t = Math.min(t, (long) x - p.get(l - 1));
            }
            ans += t / 2;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minOperations(vector<int>& nums) {
        static vector<long long> ps[2];
        if (ps[0].empty()) {
            for (long long i = 1; i <= 100000; ++i) {
                string s = to_string(i);
                string t1 = s;
                reverse(t1.begin(), t1.end());
                string t2 = s.substr(0, s.size() - 1);
                reverse(t2.begin(), t2.end());
                long long x = stoll(s + t1);
                ps[x & 1].push_back(x);
                long long y = stoll(s + t2);
                ps[y & 1].push_back(y);
            }
            sort(ps[0].begin(), ps[0].end());
            sort(ps[1].begin(), ps[1].end());
        }
        long long ans = 0;
        for (int x : nums) {
            auto& p = ps[x & 1];
            auto it = lower_bound(p.begin(), p.end(), x);
            long long t = LLONG_MAX;
            if (it != p.end()) {
                t = *it - x;
            }
            if (it != p.begin()) {
                t = min(t, (long long) x - *prev(it));
            }
            ans += t / 2;
        }
        return ans;
    }
};
```

#### Go

```go
var ps [2][]int64

func init() {
	for i := int64(1); i <= 100000; i++ {
		s := strconv.FormatInt(i, 10)
		t1 := reverse(s)
		t2 := reverse(s[:len(s)-1])
		x, _ := strconv.ParseInt(s+t1, 10, 64)
		ps[x&1] = append(ps[x&1], x)
		y, _ := strconv.ParseInt(s+t2, 10, 64)
		ps[y&1] = append(ps[y&1], y)
	}
	sort.Slice(ps[0], func(a, b int) bool { return ps[0][a] < ps[0][b] })
	sort.Slice(ps[1], func(a, b int) bool { return ps[1][a] < ps[1][b] })
}

func reverse(s string) string {
	b := []byte(s)
	for i, j := 0, len(b)-1; i < j; i, j = i+1, j-1 {
		b[i], b[j] = b[j], b[i]
	}
	return string(b)
}

func minOperations(nums []int) int64 {
	var ans int64
	for _, x := range nums {
		p := ps[x&1]
		i := sort.Search(len(p), func(i int) bool { return p[i] >= int64(x) })
		t := int64(1 << 62)
		if i < len(p) {
			t = p[i] - int64(x)
		}
		if i > 0 {
			t = min(t, int64(x)-p[i-1])
		}
		ans += t / 2
	}
	return ans
}
```

#### TypeScript

```ts
const ps: number[][] = [[], []];

for (let i = 1; i <= 10 ** 5; i++) {
    const s = String(i);
    const t1 = [...s].reverse().join('');
    const t2 = [...s.slice(0, -1)].reverse().join('');
    const x = Number(s + t1);
    ps[x & 1].push(x);
    const y = Number(s + t2);
    ps[y & 1].push(y);
}
ps[0].sort((a, b) => a - b);
ps[1].sort((a, b) => a - b);

function minOperations(nums: number[]): number {
    let ans = 0;
    for (const x of nums) {
        const p = ps[x & 1];
        let l = 0;
        let r = p.length;
        while (l < r) {
            const m = (l + r) >> 1;
            if (p[m] < x) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        let t = Number.MAX_SAFE_INTEGER;
        if (l < p.length) {
            t = p[l] - x;
        }
        if (l > 0) {
            t = Math.min(t, x - p[l - 1]);
        }
        ans += Math.floor(t / 2);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

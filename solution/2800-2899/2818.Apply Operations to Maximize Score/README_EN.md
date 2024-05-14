---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2818.Apply%20Operations%20to%20Maximize%20Score/README_EN.md
rating: 2396
tags:
    - Stack
    - Greedy
    - Array
    - Math
    - Number Theory
    - Monotonic Stack
---

# [2818. Apply Operations to Maximize Score](https://leetcode.com/problems/apply-operations-to-maximize-score)

[中文文档](/solution/2800-2899/2818.Apply%20Operations%20to%20Maximize%20Score/README.md)

## Description

<p>You are given an array <code>nums</code> of <code>n</code> positive integers and an integer <code>k</code>.</p>

<p>Initially, you start with a score of <code>1</code>. You have to maximize your score by applying the following operation at most <code>k</code> times:</p>

<ul>
	<li>Choose any <strong>non-empty</strong> subarray <code>nums[l, ..., r]</code> that you haven&#39;t chosen previously.</li>
	<li>Choose an element <code>x</code> of <code>nums[l, ..., r]</code> with the highest <strong>prime score</strong>. If multiple such elements exist, choose the one with the smallest index.</li>
	<li>Multiply your score by <code>x</code>.</li>
</ul>

<p>Here, <code>nums[l, ..., r]</code> denotes the subarray of <code>nums</code> starting at index <code>l</code> and ending at the index <code>r</code>, both ends being inclusive.</p>

<p>The <strong>prime score</strong> of an integer <code>x</code> is equal to the number of distinct prime factors of <code>x</code>. For example, the prime score of <code>300</code> is <code>3</code> since <code>300 = 2 * 2 * 3 * 5 * 5</code>.</p>

<p>Return <em>the <strong>maximum possible score</strong> after applying at most </em><code>k</code><em> operations</em>.</p>

<p>Since the answer may be large, return it modulo <code>10<sup>9 </sup>+ 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [8,3,9,3,8], k = 2
<strong>Output:</strong> 81
<strong>Explanation:</strong> To get a score of 81, we can apply the following operations:
- Choose subarray nums[2, ..., 2]. nums[2] is the only element in this subarray. Hence, we multiply the score by nums[2]. The score becomes 1 * 9 = 9.
- Choose subarray nums[2, ..., 3]. Both nums[2] and nums[3] have a prime score of 1, but nums[2] has the smaller index. Hence, we multiply the score by nums[2]. The score becomes 9 * 9 = 81.
It can be proven that 81 is the highest score one can obtain.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [19,12,14,6,10,18], k = 3
<strong>Output:</strong> 4788
<strong>Explanation:</strong> To get a score of 4788, we can apply the following operations: 
- Choose subarray nums[0, ..., 0]. nums[0] is the only element in this subarray. Hence, we multiply the score by nums[0]. The score becomes 1 * 19 = 19.
- Choose subarray nums[5, ..., 5]. nums[5] is the only element in this subarray. Hence, we multiply the score by nums[5]. The score becomes 19 * 18 = 342.
- Choose subarray nums[2, ..., 3]. Both nums[2] and nums[3] have a prime score of 2, but nums[2] has the smaller index. Hence, we multipy the score by nums[2]. The score becomes 342 * 14 = 4788.
It can be proven that 4788 is the highest score one can obtain.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length == n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= min(n * (n + 1) / 2, 10<sup>9</sup>)</code></li>
</ul>

## Solutions

### Solution 1: Monotonic Stack + Greedy

It is not difficult to see that the number of subarrays with the highest prime score of an element $nums[i]$ is $cnt = (i - l) \times (r - i)$, where $l$ is the leftmost index such that $primeScore(nums[l]) \ge primeScore(nums[i])$, and $r$ is the rightmost index such that $primeScore(nums[r]) \ge primeScore(nums[i])$.

Since we are allowed to operate at most $k$ times, we can greedily enumerate $nums[i]$ from large to small, and compute the $cnt$ of each element. If $cnt \le k$, then the contribution of $nums[i]$ to the answer is $nums[i]^{cnt}$, and we update $k = k - cnt$. If $cnt \gt k$, then the contribution of $nums[i]$ to the answer is $nums[i]^{k}$, and we break out the loop.

Return the answer after the loop. Note that the power is large, so we need to use fast power.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array.

<!-- tabs:start -->

```python
def primeFactors(n):
    i = 2
    ans = set()
    while i * i <= n:
        while n % i == 0:
            ans.add(i)
            n //= i
        i += 1
    if n > 1:
        ans.add(n)
    return len(ans)


class Solution:
    def maximumScore(self, nums: List[int], k: int) -> int:
        mod = 10**9 + 7
        arr = [(i, primeFactors(x), x) for i, x in enumerate(nums)]
        n = len(nums)

        left = [-1] * n
        right = [n] * n
        stk = []
        for i, f, x in arr:
            while stk and stk[-1][0] < f:
                stk.pop()
            if stk:
                left[i] = stk[-1][1]
            stk.append((f, i))

        stk = []
        for i, f, x in arr[::-1]:
            while stk and stk[-1][0] <= f:
                stk.pop()
            if stk:
                right[i] = stk[-1][1]
            stk.append((f, i))

        arr.sort(key=lambda x: -x[2])
        ans = 1
        for i, f, x in arr:
            l, r = left[i], right[i]
            cnt = (i - l) * (r - i)
            if cnt <= k:
                ans = ans * pow(x, cnt, mod) % mod
                k -= cnt
            else:
                ans = ans * pow(x, k, mod) % mod
                break
        return ans
```

```java
class Solution {
    private final int mod = (int) 1e9 + 7;

    public int maximumScore(List<Integer> nums, int k) {
        int n = nums.size();
        int[][] arr = new int[n][0];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {i, primeFactors(nums.get(i)), nums.get(i)};
        }
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int[] e : arr) {
            int i = e[0], f = e[1];
            while (!stk.isEmpty() && arr[stk.peek()][1] < f) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                left[i] = stk.peek();
            }
            stk.push(i);
        }
        stk.clear();
        for (int i = n - 1; i >= 0; --i) {
            int f = arr[i][1];
            while (!stk.isEmpty() && arr[stk.peek()][1] <= f) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                right[i] = stk.peek();
            }
            stk.push(i);
        }
        Arrays.sort(arr, (a, b) -> b[2] - a[2]);
        long ans = 1;
        for (int[] e : arr) {
            int i = e[0], x = e[2];
            int l = left[i], r = right[i];
            long cnt = (long) (i - l) * (r - i);
            if (cnt <= k) {
                ans = ans * qpow(x, cnt) % mod;
                k -= cnt;
            } else {
                ans = ans * qpow(x, k) % mod;
                break;
            }
        }
        return (int) ans;
    }

    private int primeFactors(int n) {
        int i = 2;
        Set<Integer> ans = new HashSet<>();
        while (i <= n / i) {
            while (n % i == 0) {
                ans.add(i);
                n /= i;
            }
            ++i;
        }
        if (n > 1) {
            ans.add(n);
        }
        return ans.size();
    }

    private int qpow(long a, long n) {
        long ans = 1;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ans = ans * a % mod;
            }
            a = a * a % mod;
        }
        return (int) ans;
    }
}
```

```cpp
class Solution {
public:
    int maximumScore(vector<int>& nums, int k) {
        const int mod = 1e9 + 7;
        int n = nums.size();
        vector<tuple<int, int, int>> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = {i, primeFactors(nums[i]), nums[i]};
        }
        vector<int> left(n, -1);
        vector<int> right(n, n);
        stack<int> stk;
        for (auto [i, f, _] : arr) {
            while (!stk.empty() && get<1>(arr[stk.top()]) < f) {
                stk.pop();
            }
            if (!stk.empty()) {
                left[i] = stk.top();
            }
            stk.push(i);
        }
        stk = stack<int>();
        for (int i = n - 1; ~i; --i) {
            int f = get<1>(arr[i]);
            while (!stk.empty() && get<1>(arr[stk.top()]) <= f) {
                stk.pop();
            }
            if (!stk.empty()) {
                right[i] = stk.top();
            }
            stk.push(i);
        }
        sort(arr.begin(), arr.end(), [](const auto& lhs, const auto& rhs) {
            return get<2>(rhs) < get<2>(lhs);
        });
        long long ans = 1;
        auto qpow = [&](long long a, int n) {
            long long ans = 1;
            for (; n; n >>= 1) {
                if (n & 1) {
                    ans = ans * a % mod;
                }
                a = a * a % mod;
            }
            return ans;
        };
        for (auto [i, _, x] : arr) {
            int l = left[i], r = right[i];
            long long cnt = 1LL * (i - l) * (r - i);
            if (cnt <= k) {
                ans = ans * qpow(x, cnt) % mod;
                k -= cnt;
            } else {
                ans = ans * qpow(x, k) % mod;
                break;
            }
        }
        return ans;
    }

    int primeFactors(int n) {
        int i = 2;
        unordered_set<int> ans;
        while (i <= n / i) {
            while (n % i == 0) {
                ans.insert(i);
                n /= i;
            }
            ++i;
        }
        if (n > 1) {
            ans.insert(n);
        }
        return ans.size();
    }
};
```

```go
func maximumScore(nums []int, k int) int {
	n := len(nums)
	const mod = 1e9 + 7
	qpow := func(a, n int) int {
		ans := 1
		for ; n > 0; n >>= 1 {
			if n&1 == 1 {
				ans = ans * a % mod
			}
			a = a * a % mod
		}
		return ans
	}
	arr := make([][3]int, n)
	left := make([]int, n)
	right := make([]int, n)
	for i, x := range nums {
		left[i] = -1
		right[i] = n
		arr[i] = [3]int{i, primeFactors(x), x}
	}
	stk := []int{}
	for _, e := range arr {
		i, f := e[0], e[1]
		for len(stk) > 0 && arr[stk[len(stk)-1]][1] < f {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			left[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	stk = []int{}
	for i := n - 1; i >= 0; i-- {
		f := arr[i][1]
		for len(stk) > 0 && arr[stk[len(stk)-1]][1] <= f {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			right[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i][2] > arr[j][2] })
	ans := 1
	for _, e := range arr {
		i, x := e[0], e[2]
		l, r := left[i], right[i]
		cnt := (i - l) * (r - i)
		if cnt <= k {
			ans = ans * qpow(x, cnt) % mod
			k -= cnt
		} else {
			ans = ans * qpow(x, k) % mod
			break
		}
	}
	return ans
}

func primeFactors(n int) int {
	i := 2
	ans := map[int]bool{}
	for i <= n/i {
		for n%i == 0 {
			ans[i] = true
			n /= i
		}
		i++
	}
	if n > 1 {
		ans[n] = true
	}
	return len(ans)
}
```

```ts
function maximumScore(nums: number[], k: number): number {
    const mod = 10 ** 9 + 7;
    const n = nums.length;
    const arr: number[][] = Array(n)
        .fill(0)
        .map(() => Array(3).fill(0));
    const left: number[] = Array(n).fill(-1);
    const right: number[] = Array(n).fill(n);
    for (let i = 0; i < n; ++i) {
        arr[i] = [i, primeFactors(nums[i]), nums[i]];
    }
    const stk: number[] = [];
    for (const [i, f, _] of arr) {
        while (stk.length && arr[stk.at(-1)!][1] < f) {
            stk.pop();
        }
        if (stk.length) {
            left[i] = stk.at(-1)!;
        }
        stk.push(i);
    }
    stk.length = 0;
    for (let i = n - 1; i >= 0; --i) {
        const f = arr[i][1];
        while (stk.length && arr[stk.at(-1)!][1] <= f) {
            stk.pop();
        }
        if (stk.length) {
            right[i] = stk.at(-1)!;
        }
        stk.push(i);
    }
    arr.sort((a, b) => b[2] - a[2]);
    let ans = 1n;
    for (const [i, _, x] of arr) {
        const l = left[i];
        const r = right[i];
        const cnt = (i - l) * (r - i);
        if (cnt <= k) {
            ans = (ans * qpow(BigInt(x), cnt, mod)) % BigInt(mod);
            k -= cnt;
        } else {
            ans = (ans * qpow(BigInt(x), k, mod)) % BigInt(mod);
            break;
        }
    }
    return Number(ans);
}

function primeFactors(n: number): number {
    let i = 2;
    const s: Set<number> = new Set();
    while (i * i <= n) {
        while (n % i === 0) {
            s.add(i);
            n = Math.floor(n / i);
        }
        ++i;
    }
    if (n > 1) {
        s.add(n);
    }
    return s.size;
}

function qpow(a: bigint, n: number, mod: number): bigint {
    let ans = 1n;
    for (; n; n >>>= 1) {
        if (n & 1) {
            ans = (ans * a) % BigInt(mod);
        }
        a = (a * a) % BigInt(mod);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->

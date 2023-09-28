# [2818. 操作使得分最大](https://leetcode.cn/problems/apply-operations-to-maximize-score)

[English Version](/solution/2800-2899/2818.Apply%20Operations%20to%20Maximize%20Score/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code>&nbsp;的正整数数组&nbsp;<code>nums</code>&nbsp;和一个整数 <code>k</code>&nbsp;。</p>

<p>一开始，你的分数为 <code>1</code>&nbsp;。你可以进行以下操作至多 <code>k</code>&nbsp;次，目标是使你的分数最大：</p>

<ul>
	<li>选择一个之前没有选过的 <strong>非空</strong>&nbsp;子数组&nbsp;<code>nums[l, ..., r]</code>&nbsp;。</li>
	<li>从&nbsp;<code>nums[l, ..., r]</code>&nbsp;里面选择一个 <strong>质数分数</strong>&nbsp;最高的元素 <code>x</code>&nbsp;。如果多个元素质数分数相同且最高，选择下标最小的一个。</li>
	<li>将你的分数乘以&nbsp;<code>x</code>&nbsp;。</li>
</ul>

<p><code>nums[l, ..., r]</code>&nbsp;表示&nbsp;<code>nums</code>&nbsp;中起始下标为&nbsp;<code>l</code>&nbsp;，结束下标为 <code>r</code>&nbsp;的子数组，两个端点都包含。</p>

<p>一个整数的 <strong>质数分数</strong>&nbsp;等于&nbsp;<code>x</code>&nbsp;不同质因子的数目。比方说，&nbsp;<code>300</code>&nbsp;的质数分数为&nbsp;<code>3</code>&nbsp;，因为&nbsp;<code>300 = 2 * 2 * 3 * 5 * 5</code>&nbsp;。</p>

<p>请你返回进行至多 <code>k</code>&nbsp;次操作后，可以得到的 <strong>最大分数</strong>&nbsp;。</p>

<p>由于答案可能很大，请你将结果对&nbsp;<code>10<sup>9 </sup>+ 7</code>&nbsp;取余后返回。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [8,3,9,3,8], k = 2
<b>输出：</b>81
<b>解释：</b>进行以下操作可以得到分数 81 ：
- 选择子数组 nums[2, ..., 2] 。nums[2] 是子数组中唯一的元素。所以我们将分数乘以 nums[2] ，分数变为 1 * 9 = 9 。
- 选择子数组 nums[2, ..., 3] 。nums[2] 和 nums[3] 质数分数都为 1 ，但是 nums[2] 下标更小。所以我们将分数乘以 nums[2] ，分数变为 9 * 9 = 81 。
81 是可以得到的最高得分。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [19,12,14,6,10,18], k = 3
<b>输出：</b>4788
<b>解释：</b>进行以下操作可以得到分数 4788 ：
- 选择子数组 nums[0, ..., 0] 。nums[0] 是子数组中唯一的元素。所以我们将分数乘以 nums[0] ，分数变为 1 * 19 = 19 。
- 选择子数组 nums[5, ..., 5] 。nums[5] 是子数组中唯一的元素。所以我们将分数乘以 nums[5] ，分数变为 19 * 18 = 342 。
- 选择子数组 nums[2, ..., 3] 。nums[2] 和 nums[3] 质数分数都为 2，但是 nums[2] 下标更小。所以我们将分数乘以 nums[2] ，分数变为  342 * 14 = 4788 。
4788 是可以得到的最高的分。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length == n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= min(n * (n + 1) / 2, 10<sup>9</sup>)</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：单调栈 + 排序贪心**

我们不妨考虑枚举每个元素 $nums[i]$ 作为质数分数最高的元素，那么我们需要找出左边第一个质数分数大于等于当前元素的位置 $l$，以及右边第一个质数分数大于当前元素的位置 $r$，那么以当前元素为最高质数分数的子数组有 $cnt = (i - l) \times (r - i)$ 个，它对答案的贡献为 $nums[i]^{cnt}$。

而题目限制了最多进行 $k$ 次操作，我们可以贪心地从大到小枚举 $nums[i]$，每一次算出其子数组的个数 $cnt$，如果 $cnt \le k$，那么 $nums[i]$ 对答案的贡献就是 $nums[i]^{cnt}$，然后我们更新 $k = k - cnt$，继续枚举下一个元素。如果 $cnt \gt k$，那么 $nums[i]$ 对答案的贡献就是 $nums[i]^{k}$，然后退出循环。

枚举结束，返回答案即可。注意，由于幂次较大，我们需要用到快速幂。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
        mod = 10 ** 9 + 7
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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **Go**

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

### **TypeScript**

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

### **...**

```

```

<!-- tabs:end -->

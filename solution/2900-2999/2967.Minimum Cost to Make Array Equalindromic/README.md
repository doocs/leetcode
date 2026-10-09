---
comments: true
difficulty: 中等
rating: 2116
source: 第 376 场周赛 Q3
tags:
    - 贪心
    - 数组
    - 数学
    - 二分查找
    - 排序
---

<!-- problem:start -->

# [2967. 使数组成为等数数组的最小代价](https://leetcode.cn/problems/minimum-cost-to-make-array-equalindromic)

[English Version](/solution/2900-2999/2967.Minimum%20Cost%20to%20Make%20Array%20Equalindromic/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>。</p>

<p>在一次 <strong>操作</strong> 中，你可以选择一个下标 <code>i</code>，并将 <code>nums[i]</code> 加 <code>1</code> 或减 <code>1</code>。</p>

<p>返回将 <code>nums</code> 中每个元素都变为 <strong>相同</strong> 的 <span data-keyword="palindrome-integer">正回文整数</span> 所需的 <strong>最小</strong> 操作数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,4,5]
<b>输出：</b>6
<b>解释：</b>增加 nums[0] 两次和 nums[1] 一次，然后减少 nums[3] 一次和 nums[4] 两次。经过 6 次操作，nums 变为 [3,3,3,3,3]，3 是一个正回文整数。
可以证明这是所需的最小操作次数。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [10,12,13,14,15]
<b>输出：</b>11
<b>解释：</b>增加 nums[0] 一次，然后分别减少 nums[1]、nums[2]、nums[3] 和 nums[4] 1、2、3 和 4 次。经过 11 次操作后，nums 变为 [11,11,11,11,11]，11 是一个正回文整数。
可以证明这是所需的最小操作次数。
</pre>

<p><strong class="example">示例 3 ：</strong></p>

<pre>
<b>输入：</b>nums = [22,33,22,33,22]
<b>输出：</b>22
<b>解释：</b>减少 nums[1] 和 nums[3] 各 11 次。经过 22 次操作，nums 变为 [22,22,22,22,22]，22 是一个正回文整数。
可以证明这是所需的最小操作次数。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：预处理 + 排序 + 二分查找

<!-- thinking:start -->

> **思考**
>
> 把所有数改成同一个回文数，代价为绝对差之和，最优目标接近中位数。回文数可在 $[1,10^5]$ 枚举半边并镜像，预处理排序后的 $ps$。
>
> 对 $nums$ 排序取中位数，在 $ps$ 中二分出邻近的两三个回文，计算代价取最小。 $n \le 10^5$，避免枚举全部目标。

<!-- thinking:end -->

题目中回文数的范围是 $[1, 10^9]$，回文数由于对称性，我们可以在 $[1, 10^5]$ 的范围内枚举，然后将其翻转后拼接，得到所有的回文数，注意，如果是奇数长度的回文数，我们在翻转前要去掉最后一位。预处理得到的回文数数组记为 $ps$。我们对数组 $ps$ 进行排序。

接下来，我们对数组 $nums$ 进行排序，然后取 $nums$ 的中位数 $x$，我们只需要通过二分查找，在回文数组 $ps$ 中，找到一个与 $x$ 最接近的数，然后计算 $nums$ 变成这个数的代价，即可得到答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(M)$。其中 $n$ 是数组 $nums$ 的长度，而 $M$ 是回文数组 $ps$ 的长度。

相似题目：

- [906. 超级回文数](https://github.com/doocs/leetcode/blob/main/solution/0900-0999/0906.Super%20Palindromes/README.md)

<!-- tabs:start -->

#### Python3

```python
ps = []
for i in range(1, 10**5 + 1):
    s = str(i)
    t1 = s[::-1]
    t2 = s[:-1][::-1]
    ps.append(int(s + t1))
    ps.append(int(s + t2))
ps.sort()


class Solution:
    def minimumCost(self, nums: List[int]) -> int:
        def f(x: int) -> int:
            return sum(abs(v - x) for v in nums)

        nums.sort()
        i = bisect_left(ps, nums[len(nums) // 2])
        return min(f(ps[j]) for j in range(i - 1, i + 2) if 0 <= j < len(ps))
```

#### Java

```java
public class Solution {
    private static long[] ps;
    private int[] nums;

    static {
        ps = new long[2 * (int) 1e5];
        for (int i = 1; i <= 1e5; i++) {
            String s = Integer.toString(i);
            String t1 = new StringBuilder(s).reverse().toString();
            String t2 = new StringBuilder(s.substring(0, s.length() - 1)).reverse().toString();
            ps[2 * i - 2] = Long.parseLong(s + t1);
            ps[2 * i - 1] = Long.parseLong(s + t2);
        }
        Arrays.sort(ps);
    }

    public long minimumCost(int[] nums) {
        this.nums = nums;
        Arrays.sort(nums);
        int i = Arrays.binarySearch(ps, nums[nums.length / 2]);
        i = i < 0 ? -i - 1 : i;
        long ans = 1L << 60;
        for (int j = i - 1; j <= i + 1; j++) {
            if (0 <= j && j < ps.length) {
                ans = Math.min(ans, f(ps[j]));
            }
        }
        return ans;
    }

    private long f(long x) {
        long ans = 0;
        for (int v : nums) {
            ans += Math.abs(v - x);
        }
        return ans;
    }
}
```

#### C++

```cpp
using ll = long long;

ll ps[2 * 100000];

int init = [] {
    for (int i = 1; i <= 100000; i++) {
        string s = to_string(i);
        string t1 = s;
        reverse(t1.begin(), t1.end());
        string t2 = s.substr(0, s.length() - 1);
        reverse(t2.begin(), t2.end());
        ps[2 * i - 2] = stoll(s + t1);
        ps[2 * i - 1] = stoll(s + t2);
    }
    sort(ps, ps + 2 * 100000);
    return 0;
}();

class Solution {
public:
    long long minimumCost(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int i = lower_bound(ps, ps + 2 * 100000, nums[nums.size() / 2]) - ps;
        auto f = [&](ll x) {
            ll ans = 0;
            for (int& v : nums) {
                ans += abs(v - x);
            }
            return ans;
        };
        ll ans = LLONG_MAX;
        for (int j = i - 1; j <= i + 1; j++) {
            if (0 <= j && j < 2 * 100000) {
                ans = min(ans, f(ps[j]));
            }
        }
        return ans;
    }
};
```

#### Go

```go
var ps [2 * 100000]int64

func init() {
	for i := 1; i <= 100000; i++ {
		s := strconv.Itoa(i)
		t1 := reverseString(s)
		t2 := reverseString(s[:len(s)-1])
		ps[2*i-2], _ = strconv.ParseInt(s+t1, 10, 64)
		ps[2*i-1], _ = strconv.ParseInt(s+t2, 10, 64)
	}
	sort.Slice(ps[:], func(i, j int) bool {
		return ps[i] < ps[j]
	})
}

func reverseString(s string) string {
	cs := []rune(s)
	for i, j := 0, len(cs)-1; i < j; i, j = i+1, j-1 {
		cs[i], cs[j] = cs[j], cs[i]
	}
	return string(cs)
}

func minimumCost(nums []int) int64 {
	sort.Ints(nums)
	i := sort.Search(len(ps), func(i int) bool {
		return ps[i] >= int64(nums[len(nums)/2])
	})

	f := func(x int64) int64 {
		var ans int64
		for _, v := range nums {
			ans += int64(abs(int(x - int64(v))))
		}
		return ans
	}

	ans := int64(math.MaxInt64)
	for j := i - 1; j <= i+1; j++ {
		if 0 <= j && j < len(ps) {
			ans = min(ans, f(ps[j]))
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
const ps = Array(2e5).fill(0);

const init = (() => {
    for (let i = 1; i <= 1e5; ++i) {
        const s: string = i.toString();
        const t1: string = s.split('').reverse().join('');
        const t2: string = s.slice(0, -1).split('').reverse().join('');
        ps[2 * i - 2] = parseInt(s + t1, 10);
        ps[2 * i - 1] = parseInt(s + t2, 10);
    }
    ps.sort((a, b) => a - b);
})();

function minimumCost(nums: number[]): number {
    const search = (x: number): number => {
        let [l, r] = [0, ps.length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (ps[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const f = (x: number): number => {
        return nums.reduce((acc, v) => acc + Math.abs(v - x), 0);
    };

    nums.sort((a, b) => a - b);
    const i: number = search(nums[nums.length >> 1]);
    let ans: number = Number.MAX_SAFE_INTEGER;
    for (let j = i - 1; j <= i + 1; j++) {
        if (j >= 0 && j < ps.length) {
            ans = Math.min(ans, f(ps[j]));
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn minimum_cost(nums: Vec<i32>) -> i64 {
        use std::sync::Once;
        use std::cmp::min;

        static INIT: Once = Once::new();
        static mut PS: Vec<i64> = Vec::new();

        INIT.call_once(|| {
            let mut ps_local = Vec::with_capacity(2 * 100_000);
            for i in 1..=100_000 {
                let s = i.to_string();

                let mut t1 = s.clone();
                t1 = t1.chars().rev().collect();
                ps_local.push(format!("{}{}", s, t1).parse::<i64>().unwrap());

                let mut t2 = s[0..s.len() - 1].to_string();
                t2 = t2.chars().rev().collect();
                ps_local.push(format!("{}{}", s, t2).parse::<i64>().unwrap());
            }
            ps_local.sort();
            unsafe {
                PS = ps_local;
            }
        });

        let mut nums = nums;
        nums.sort();

        let mid = nums[nums.len() / 2] as i64;

        let i = unsafe {
            match PS.binary_search(&mid) {
                Ok(i) => i,
                Err(i) => i,
            }
        };

        let f = |x: i64| -> i64 {
            nums.iter().map(|&v| (v as i64 - x).abs()).sum()
        };

        let mut ans = i64::MAX;

        for j in i.saturating_sub(1)..=(i + 1).min(2 * 100_000 - 1) {
            let x = unsafe { PS[j] };
            ans = min(ans, f(x));
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

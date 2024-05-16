---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2967.Minimum%20Cost%20to%20Make%20Array%20Equalindromic/README.md
rating: 2116
source: 第 376 场周赛 Q3
tags:
    - 贪心
    - 数组
    - 数学
    - 排序
---

<!-- problem:start -->

# [2967. 使数组成为等数数组的最小代价](https://leetcode.cn/problems/minimum-cost-to-make-array-equalindromic)

[English Version](/solution/2900-2999/2967.Minimum%20Cost%20to%20Make%20Array%20Equalindromic/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code>&nbsp;下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>你可以对 <code>nums</code>&nbsp;执行特殊操作 <strong>任意次</strong>&nbsp;（也可以 <strong>0</strong>&nbsp;次）。每一次特殊操作中，你需要 <strong>按顺序</strong>&nbsp;执行以下步骤：</p>

<ul>
	<li>从范围&nbsp;<code>[0, n - 1]</code>&nbsp;里选择一个下标 <code>i</code>&nbsp;和一个 <strong>正</strong>&nbsp;整数&nbsp;<code>x</code>&nbsp;。</li>
	<li>将&nbsp;<code>|nums[i] - x|</code>&nbsp;添加到总代价里。</li>
	<li>将 <code>nums[i]</code>&nbsp;变为&nbsp;<code>x</code>&nbsp;。</li>
</ul>

<p>如果一个正整数正着读和反着读都相同，那么我们称这个数是<strong>&nbsp;回文数</strong>&nbsp;。比方说，<code>121</code>&nbsp;，<code>2552</code> 和&nbsp;<code>65756</code>&nbsp;都是回文数，但是&nbsp;<code>24</code>&nbsp;，<code>46</code>&nbsp;，<code>235</code>&nbsp;都不是回文数。</p>

<p>如果一个数组中的所有元素都等于一个整数&nbsp;<code>y</code>&nbsp;，且&nbsp;<code>y</code>&nbsp;是一个小于&nbsp;<code>10<sup>9</sup></code>&nbsp;的&nbsp;<strong>回文数</strong>&nbsp;，那么我们称这个数组是一个 <strong>等数数组&nbsp;</strong>。</p>

<p>请你返回一个整数，表示执行任意次特殊操作后使 <code>nums</code>&nbsp;成为 <strong>等数数组</strong>&nbsp;的 <strong>最小</strong>&nbsp;总代价。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,4,5]
<b>输出：</b>6
<b>解释：</b>我们可以将数组中所有元素变为回文数 3 得到等数数组，数组变成 [3,3,3,3,3] 需要执行 4 次特殊操作，代价为 |1 - 3| + |2 - 3| + |4 - 3| + |5 - 3| = 6 。
将所有元素变为其他回文数的总代价都大于 6 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [10,12,13,14,15]
<b>输出：</b>11
<b>解释：</b>我们可以将数组中所有元素变为回文数 11 得到等数数组，数组变成 [11,11,11,11,11] 需要执行 5 次特殊操作，代价为 |10 - 11| + |12 - 11| + |13 - 11| + |14 - 11| + |15 - 11| = 11 。
将所有元素变为其他回文数的总代价都大于 11 。
</pre>

<p><strong class="example">示例 3 ：</strong></p>

<pre>
<b>输入：</b>nums = [22,33,22,33,22]
<b>输出：</b>22
<b>解释：</b>我们可以将数组中所有元素变为回文数 22 得到等数数组，数组变为 [22,22,22,22,22] 需要执行 2 次特殊操作，代价为 |33 - 22| + |33 - 22| = 22 。
将所有元素变为其他回文数的总代价都大于 22 。
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

题目中回文数的范围是 $[1, 10^9]$，回文数由于对称性，我们可以在 $[1, 10^5]$ 的范围内枚举，然后将其翻转后拼接，得到所有的回文数，注意，如果是奇数长度的回文数，我们在翻转前要去掉最后一位。预处理得到的回文数数组记为 $ps$。我们对数组 $ps$ 进行排序。

接下来，我们对数组 $nums$ 进行排序，然后取 $nums$ 的中位数 $x$，我们只需要通过二分查找，在回文数组 $ps$ 中，找到一个与 $x$ 最接近的数，然后计算 $nums$ 变成这个数的代价，即可得到答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(M)$。其中 $n$ 是数组 $nums$ 的长度，而 $M$ 是回文数组 $ps$ 的长度。

相似题目：

-   [906. 超级回文数](https://github.com/doocs/leetcode/blob/main/solution/0900-0999/0906.Super%20Palindromes/README.md)

<!-- tabs:start -->

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

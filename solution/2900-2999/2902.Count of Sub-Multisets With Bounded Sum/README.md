# [2902. 和带限制的子多重集合的数目](https://leetcode.cn/problems/count-of-sub-multisets-with-bounded-sum)

[English Version](/solution/2900-2999/2902.Count%20of%20Sub-Multisets%20With%20Bounded%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的非负整数数组&nbsp;<code>nums</code>&nbsp;和两个整数&nbsp;<code>l</code> 和&nbsp;<code>r</code>&nbsp;。</p>

<p>请你返回&nbsp;<code>nums</code>&nbsp;中子多重集合的和在闭区间&nbsp;<code>[l, r]</code>&nbsp;之间的 <strong>子多重集合的数目</strong> 。</p>

<p>由于答案可能很大，请你将答案对&nbsp;<code>10<sup>9 </sup>+ 7</code>&nbsp;取余后返回。</p>

<p><strong>子多重集合</strong> 指的是从数组中选出一些元素构成的 <strong>无序</strong>&nbsp;集合，每个元素 <code>x</code>&nbsp;出现的次数可以是&nbsp;<code>0, 1, ..., occ[x]</code>&nbsp;次，其中&nbsp;<code>occ[x]</code>&nbsp;是元素&nbsp;<code>x</code>&nbsp;在数组中的出现次数。</p>

<p><b>注意：</b></p>

<ul>
	<li>如果两个子多重集合中的元素排序后一模一样，那么它们两个是相同的&nbsp;<strong>子多重集合</strong>&nbsp;。</li>
	<li><strong>空</strong>&nbsp;集合的和是 <code>0</code>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,2,3], l = 6, r = 6
<b>输出：</b>1
<b>解释：</b>唯一和为 6 的子集合是 {1, 2, 3} 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2,1,4,2,7], l = 1, r = 5
<b>输出：</b>7
<b>解释：</b>和在闭区间 [1, 5] 之间的子多重集合为 {1} ，{2} ，{4} ，{2, 2} ，{1, 2} ，{1, 4} 和 {1, 2, 2} 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,1,3,5,2], l = 3, r = 5
<b>输出：</b>9
<b>解释：</b>和在闭区间 [3, 5] 之间的子多重集合为 {3} ，{5} ，{1, 2} ，{1, 3} ，{2, 2} ，{2, 3} ，{1, 1, 2} ，{1, 1, 3} 和 {1, 2, 2} 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>nums</code> 的和不超过&nbsp;<code>2 * 10<sup>4</sup></code> 。</li>
	<li><code>0 &lt;= l &lt;= r &lt;= 2 * 10<sup>4</sup></code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def countSubMultisets(self, nums: List[int], l: int, r: int) -> int:
        kMod = 1_000_000_007
        # dp[i] := # of submultisets of nums with sum i
        dp = [1] + [0] * r
        count = collections.Counter(nums)
        zeros = count.pop(0, 0)

        for num, freq in count.items():
            # stride[i] := dp[i] + dp[i - num] + dp[i - 2 * num] + ...
            stride = dp.copy()
            for i in range(num, r + 1):
                stride[i] += stride[i - num]
            for i in range(r, 0, -1):
                if i >= num * (freq + 1):
                    # dp[i] + dp[i - num] + dp[i - freq * num]
                    dp[i] = stride[i] - stride[i - num * (freq + 1)]
                else:
                    dp[i] = stride[i]

        return (zeros + 1) * sum(dp[l : r + 1]) % kMod
```

```java
class Solution {
    static final int MOD = 1_000_000_007;
    public int countSubMultisets(List<Integer> nums, int l, int r) {
        Map<Integer, Integer> count = new HashMap<>();
        int total = 0;
        for (int num : nums) {
            total += num;
            if (num <= r) {
                count.merge(num, 1, Integer::sum);
            }
        }
        if (total < l) {
            return 0;
        }
        r = Math.min(r, total);
        int[] dp = new int[r + 1];
        dp[0] = count.getOrDefault(0, 0) + 1;
        count.remove(Integer.valueOf(0));
        int sum = 0;
        for (Map.Entry<Integer, Integer> e : count.entrySet()) {
            int num = e.getKey();
            int c = e.getValue();
            sum = Math.min(sum + c * num, r);
            // prefix part
            // dp[i] = dp[i] + dp[i - num] + ... + dp[i - c*num] + dp[i-(c+1)*num] + ... + dp[i %
            // num]
            for (int i = num; i <= sum; i++) {
                dp[i] = (dp[i] + dp[i - num]) % MOD;
            }
            int temp = (c + 1) * num;
            // correction part
            // subtract dp[i - (freq + 1) * num] to the end part.
            // leves dp[i] = dp[i] + dp[i-num] +...+ dp[i - c*num];
            for (int i = sum; i >= temp; i--) {
                dp[i] = (dp[i] - dp[i - temp] + MOD) % MOD;
            }
        }
        int ans = 0;
        for (int i = l; i <= r; i++) {
            ans += dp[i];
            ans %= MOD;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countSubMultisets(const vector<int> &nums, int l, int r) {
        int cnt[20001] = {};
        int memo[20001] = {};
        const int mod = 1000000007;
        for (int n : nums) {
            ++cnt[n];
        }
        fill_n(memo, cnt[1] + 1, 1);
        for (int n = 2, total = cnt[1]; n <= r; ++n) {
            if (!cnt[n]) {
                continue;
            }
            int top = (cnt[n] + 1) * n;
            total += n * cnt[n];
            for (int i = n, ii = min(total, r); i <= ii; ++i) {
                memo[i] = (memo[i] + memo[i - n]) % mod;
            }
            for (int i = min(total, r); i >= top; --i) {
                memo[i] = (mod + memo[i] - memo[i - top]) % mod;
            }
        }
        return accumulate(memo + l, memo + r + 1, 0LL) * (cnt[0] + 1) % mod;
    }
};
```

```go
func countSubMultisets(nums []int, l int, r int) int {
	multiset := make(map[int]int)
	for _, num := range nums {
		multiset[num]++
	}
	mem := make([]int, r+1)
	mem[0] = 1
	prefix := make([]int, len(mem))
	for num, occ := range multiset {
		copy(prefix, mem)
		for sum := num; sum <= r; sum++ {
			prefix[sum] = (prefix[sum] + prefix[sum-num]) % mod
		}
		for sum := r; sum >= 0; sum-- {
			if num > 0 {
				mem[sum] = prefix[sum]
				if sum >= num*(occ+1) {
					mem[sum] = (mem[sum] - prefix[sum-num*(occ+1)] + mod) % mod
				}
			} else {
				mem[sum] = (mem[sum] * (occ + 1)) % mod
			}
		}
	}
	var result int
	for sum := l; sum <= r; sum++ {
		result = (result + mem[sum]) % mod
	}
	return result
}
var mod int = 1e9 + 7
```

```ts
function countSubMultisets(nums: number[], l: number, r: number): number {
    const cnt: number[] = Array(20001).fill(0);
    const memo: number[] = Array(20001).fill(0);
    const mod: number = 1000000007;
    for (const n of nums) {
        cnt[n]++;
    }
    memo.fill(1, 0, cnt[1] + 1);
    let total: number = cnt[1];
    for (let n = 2; n <= r; ++n) {
        if (!cnt[n]) {
            continue;
        }
        const top: number = (cnt[n] + 1) * n;
        total += n * cnt[n];
        for (let i = n, ii = Math.min(total, r); i <= ii; ++i) {
            memo[i] = (memo[i] + memo[i - n]) % mod;
        }
        for (let i = Math.min(total, r); i >= top; --i) {
            memo[i] = (mod + memo[i] - memo[i - top]) % mod;
        }
    }
    let result: number = 0;
    for (let i = l; i <= r; i++) {
        result = (result + memo[i]) % mod;
    }
    return (result * (cnt[0] + 1)) % mod;
}
```

<!-- tabs:end -->

<!-- end -->

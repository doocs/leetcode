# [2835. 使子序列的和等于目标的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-form-subsequence-with-target-sum)

[English Version](/solution/2800-2899/2835.Minimum%20Operations%20to%20Form%20Subsequence%20With%20Target%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的数组&nbsp;<code>nums</code>&nbsp;，它包含 <strong>非负</strong>&nbsp;整数，且全部为 <code>2</code>&nbsp;的幂，同时给你一个整数&nbsp;<code>target</code>&nbsp;。</p>

<p>一次操作中，你必须对数组做以下修改：</p>

<ul>
	<li>选择数组中一个元素&nbsp;<code>nums[i]</code>&nbsp;，满足&nbsp;<code>nums[i] &gt; 1</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[i]</code>&nbsp;从数组中删除。</li>
	<li>在 <code>nums</code>&nbsp;的 <strong>末尾</strong>&nbsp;添加 <strong>两个</strong>&nbsp;数，值都为&nbsp;<code>nums[i] / 2</code>&nbsp;。</li>
</ul>

<p>你的目标是让 <code>nums</code>&nbsp;的一个 <strong>子序列</strong>&nbsp;的元素和等于&nbsp;<code>target</code>&nbsp;，请你返回达成这一目标的 <strong>最少操作次数</strong>&nbsp;。如果无法得到这样的子序列，请你返回 <code>-1</code>&nbsp;。</p>

<p>数组中一个 <strong>子序列</strong>&nbsp;是通过删除原数组中一些元素，并且不改变剩余元素顺序得到的剩余数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,8], target = 7
<b>输出：</b>1
<b>解释：</b>第一次操作中，我们选择元素 nums[2] 。数组变为 nums = [1,2,4,4] 。
这时候，nums 包含子序列 [1,2,4] ，和为 7 。
无法通过更少的操作得到和为 7 的子序列。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,32,1,2], target = 12
<b>输出：</b>2
<b>解释：</b>第一次操作中，我们选择元素 nums[1] 。数组变为 nums = [1,1,2,16,16] 。
第二次操作中，我们选择元素 nums[3] 。数组变为 nums = [1,1,2,16,8,8] 。
这时候，nums 包含子序列 [1,1,2,8] ，和为 12 。
无法通过更少的操作得到和为 12 的子序列。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [1,32,1], target = 35
<b>输出：</b>-1
<b>解释：</b>无法得到和为 35 的子序列。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 2<sup>30</sup></code></li>
	<li><code>nums</code>&nbsp;只包含非负整数，且均为 2 的幂。</li>
	<li><code>1 &lt;= target &lt; 2<sup>31</sup></code></li>
</ul>

## 解法

### 方法一：贪心 + 位运算

观察题目中的操作，我们发现，每次操作实际上是把一个大于 $1$ 的数拆分为两个相等的数，这意味着操作后数组的元素和不会发生变化。因此，如果数组的元素和 $s$ 小于 $target$，则无法通过题目描述的操作得到和为 $target$ 的子序列，直接返回 $-1$ 即可。否则，我们一定可以通过拆分操作，使得数组某个子序列的元素和等于 $target$。

另外，拆分操作实际上会把二进制高位为 $1$ 的数置为 $0$，并把低一位的数加上 $2$。因此，我们先用一个长度为 $32$ 的数组记录数组 $nums$ 所有元素的二进制表示中每个二进制位上 $1$ 的出现次数。

接下来，从 $target$ 的低位开始遍历，对于 $target$ 的第 $i$ 位，如果当前位数字为 $0$，则直接跳过，即 $i = i + 1$。如果当前位数字为 $1$，我们需要在数组 $cnt$ 中，找到最小的数字 $j$（其中 $j \ge i$），使得 $cnt[j] \gt 0$，然后我们将该位的数字 $1$ 往低位 $i$ 拆分，即把 $cnt[j]$ 减 $1$，而 $cnt[i..j-1]$ 的每一位置为 $1$，操作次数为 $j-i$。接下来，我们令 $j = i$，然后 $i = i + 1$。重复上述操作，直到 $i$ 超出数组 $cnt$ 的下标范围，返回此时的操作次数。

注意，如果 $j \lt i$，实际上两个低位的 $1$ 可以合并成一个高一位的 $1$。因此，如果 $j \lt i$，我们将 $\frac{cnt[j]}{2}$ 加到 $cnt[j+1]$ 中，并将 $cnt[j]$ 取模 $2$，然后令 $j = j + 1$，继续上述操作。

时间复杂度 $O(n \times \log M)$，空间复杂度 $O(\log M)$。其中 $n$ 是数组 $nums$ 的长度，而 $M$ 是数组 $nums$ 中的最大值。

<!-- tabs:start -->

```python
class Solution:
    def minOperations(self, nums: List[int], target: int) -> int:
        s = sum(nums)
        if s < target:
            return -1
        cnt = [0] * 32
        for x in nums:
            for i in range(32):
                if x >> i & 1:
                    cnt[i] += 1
        i = j = 0
        ans = 0
        while 1:
            while i < 32 and (target >> i & 1) == 0:
                i += 1
            if i == 32:
                break
            while j < i:
                cnt[j + 1] += cnt[j] // 2
                cnt[j] %= 2
                j += 1
            while cnt[j] == 0:
                cnt[j] = 1
                j += 1
            ans += j - i
            cnt[j] -= 1
            j = i
            i += 1
        return ans
```

```java
class Solution {
    public int minOperations(List<Integer> nums, int target) {
        long s = 0;
        int[] cnt = new int[32];
        for (int x : nums) {
            s += x;
            for (int i = 0; i < 32; ++i) {
                if ((x >> i & 1) == 1) {
                    ++cnt[i];
                }
            }
        }
        if (s < target) {
            return -1;
        }
        int i = 0, j = 0;
        int ans = 0;
        while (true) {
            while (i < 32 && (target >> i & 1) == 0) {
                ++i;
            }
            if (i == 32) {
                return ans;
            }
            while (j < i) {
                cnt[j + 1] += cnt[j] / 2;
                cnt[j] %= 2;
                ++j;
            }
            while (cnt[j] == 0) {
                cnt[j] = 1;
                ++j;
            }
            ans += j - i;
            --cnt[j];
            j = i;
            ++i;
        }
    }
}
```

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, int target) {
        long long s = 0;
        int cnt[32]{};
        for (int x : nums) {
            s += x;
            for (int i = 0; i < 32; ++i) {
                if (x >> i & 1) {
                    ++cnt[i];
                }
            }
        }
        if (s < target) {
            return -1;
        }
        int i = 0, j = 0;
        int ans = 0;
        while (1) {
            while (i < 32 && (target >> i & 1) == 0) {
                ++i;
            }
            if (i == 32) {
                return ans;
            }
            while (j < i) {
                cnt[j + 1] += cnt[j] / 2;
                cnt[j] %= 2;
                ++j;
            }
            while (cnt[j] == 0) {
                cnt[j] = 1;
                ++j;
            }
            ans += j - i;
            --cnt[j];
            j = i;
            ++i;
        }
    }
};
```

```go
func minOperations(nums []int, target int) (ans int) {
	s := 0
	cnt := [32]int{}
	for _, x := range nums {
		s += x
		for i := 0; i < 32; i++ {
			if x>>i&1 > 0 {
				cnt[i]++
			}
		}
	}
	if s < target {
		return -1
	}
	var i, j int
	for {
		for i < 32 && target>>i&1 == 0 {
			i++
		}
		if i == 32 {
			return
		}
		for j < i {
			cnt[j+1] += cnt[j] >> 1
			cnt[j] %= 2
			j++
		}
		for cnt[j] == 0 {
			cnt[j] = 1
			j++
		}
		ans += j - i
		cnt[j]--
		j = i
		i++
	}
}
```

```ts
function minOperations(nums: number[], target: number): number {
    let s = 0;
    const cnt: number[] = Array(32).fill(0);
    for (const x of nums) {
        s += x;
        for (let i = 0; i < 32; ++i) {
            if ((x >> i) & 1) {
                ++cnt[i];
            }
        }
    }
    if (s < target) {
        return -1;
    }
    let [ans, i, j] = [0, 0, 0];
    while (1) {
        while (i < 32 && ((target >> i) & 1) === 0) {
            ++i;
        }
        if (i === 32) {
            return ans;
        }
        while (j < i) {
            cnt[j + 1] += cnt[j] >> 1;
            cnt[j] %= 2;
            ++j;
        }
        while (cnt[j] == 0) {
            cnt[j] = 1;
            j++;
        }
        ans += j - i;
        cnt[j]--;
        j = i;
        i++;
    }
}
```

<!-- tabs:end -->

<!-- end -->

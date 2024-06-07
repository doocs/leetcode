---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3153.Sum%20of%20Digit%20Differences%20of%20All%20Pairs/README.md
rating: 1645
source: 第 398 场周赛 Q3
tags:
    - 数组
    - 哈希表
    - 数学
    - 计数
---

<!-- problem:start -->

# [3153. 所有数对中数位不同之和](https://leetcode.cn/problems/sum-of-digit-differences-of-all-pairs)

[English Version](/solution/3100-3199/3153.Sum%20of%20Digit%20Differences%20of%20All%20Pairs/README_EN.md)

## 题目描述

<!-- description:start -->

<p>车尔尼有一个数组&nbsp;<code>nums</code>&nbsp;，它只包含 <strong>正</strong>&nbsp;整数，所有正整数的数位长度都 <strong>相同</strong>&nbsp;。</p>

<p>两个整数的 <strong>数位不同</strong>&nbsp;指的是两个整数 <b>相同</b>&nbsp;位置上不同数字的数目。</p>

<p>请车尔尼返回 <code>nums</code>&nbsp;中 <strong>所有</strong>&nbsp;整数对里，<strong>数位不同之和。</strong></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [13,23,12]</span></p>

<p><b>输出：</b>4</p>

<p><strong>解释：</strong><br />
计算过程如下：<br />
-&nbsp;<strong>1</strong>3 和&nbsp;<strong>2</strong>3 的数位不同为&nbsp;1 。<br />
- 1<strong>3</strong> 和 1<strong>2</strong>&nbsp;的数位不同为&nbsp;1 。<br />
-&nbsp;<strong>23</strong> 和&nbsp;<strong>12</strong>&nbsp;的数位不同为&nbsp;2 。<br />
所以所有整数数对的数位不同之和为&nbsp;<code>1 + 1 + 2 = 4</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [10,10,10,10]</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong><br />
数组中所有整数都相同，所以所有整数数对的数位不同之和为 0 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt; 10<sup>9</sup></code></li>
	<li><code>nums</code>&nbsp;中的整数都有相同的数位长度。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们先获取数组中数字的位数 $m$，然后对于每一位，我们统计数组 $\textit{nums}$ 中每个数字在该位上的出现次数，记为 $\textit{cnt}$。那么在该位上，所有数对的数位不同之和为：

$$
\sum_{v \in \textit{cnt}} v \times (n - v)
$$

其中 $n$ 是数组的长度。我们将所有位的数位不同之和相加，再除以 $2$ 即可得到答案。

时间复杂度 $O(n \times m)$，空间复杂度 $O(C)$，其中 $n$ 和 $m$ 分别是数组的长度和数字的位数；而 $C$ 是常数，本题中 $C = 10$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumDigitDifferences(self, nums: List[int]) -> int:
        n = len(nums)
        m = int(log10(nums[0])) + 1
        ans = 0
        for _ in range(m):
            cnt = Counter()
            for i, x in enumerate(nums):
                nums[i], y = divmod(x, 10)
                cnt[y] += 1
            ans += sum(v * (n - v) for v in cnt.values()) // 2
        return ans
```

#### Java

```java
class Solution {
    public long sumDigitDifferences(int[] nums) {
        int n = nums.length;
        int m = (int) Math.floor(Math.log10(nums[0])) + 1;
        int[] cnt = new int[10];
        long ans = 0;
        for (int k = 0; k < m; ++k) {
            Arrays.fill(cnt, 0);
            for (int i = 0; i < n; ++i) {
                ++cnt[nums[i] % 10];
                nums[i] /= 10;
            }
            for (int i = 0; i < 10; ++i) {
                ans += 1L * cnt[i] * (n - cnt[i]);
            }
        }
        return ans / 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long sumDigitDifferences(vector<int>& nums) {
        int n = nums.size();
        int m = floor(log10(nums[0])) + 1;
        int cnt[10];
        long long ans = 0;
        for (int k = 0; k < m; ++k) {
            memset(cnt, 0, sizeof(cnt));
            for (int i = 0; i < n; ++i) {
                ++cnt[nums[i] % 10];
                nums[i] /= 10;
            }
            for (int i = 0; i < 10; ++i) {
                ans += 1LL * (cnt[i] * (n - cnt[i]));
            }
        }
        return ans / 2;
    }
};
```

#### Go

```go
func sumDigitDifferences(nums []int) (ans int64) {
	n := len(nums)
	m := int(math.Floor(math.Log10(float64(nums[0])))) + 1
	for k := 0; k < m; k++ {
		cnt := [10]int{}
		for i, x := range nums {
			cnt[x%10]++
			nums[i] /= 10
		}
		for _, v := range cnt {
			ans += int64(v) * int64(n-v)
		}
	}
	ans /= 2
	return
}
```

#### TypeScript

```ts
function sumDigitDifferences(nums: number[]): number {
    const n = nums.length;
    const m = Math.floor(Math.log10(nums[0])) + 1;
    let ans: bigint = BigInt(0);
    for (let k = 0; k < m; ++k) {
        const cnt: number[] = Array(10).fill(0);
        for (let i = 0; i < n; ++i) {
            ++cnt[nums[i] % 10];
            nums[i] = Math.floor(nums[i] / 10);
        }
        for (let i = 0; i < 10; ++i) {
            ans += BigInt(cnt[i]) * BigInt(n - cnt[i]);
        }
    }
    ans /= BigInt(2);
    return Number(ans);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

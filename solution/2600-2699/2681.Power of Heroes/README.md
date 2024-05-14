# [2681. 英雄的力量](https://leetcode.cn/problems/power-of-heroes)

[English Version](/solution/2600-2699/2681.Power%20of%20Heroes/README_EN.md)

<!-- tags:数组,数学,动态规划,前缀和,排序 -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;，它表示英雄的能力值。如果我们选出一部分英雄，这组英雄的 <strong>力量</strong>&nbsp;定义为：</p>

<ul>
	<li><code>i<sub>0</sub></code>&nbsp;，<code>i<sub>1</sub></code>&nbsp;，<span style="">... </span><code><span style="">i<sub>k</sub></span></code><span style="">&nbsp;</span>表示这组英雄在数组中的下标。那么这组英雄的力量为&nbsp;<code><font face="monospace">max(nums[</font>i<sub>0</sub><font face="monospace">],nums[</font>i<sub>1</sub><font face="monospace">] ... nums[</font><span style="font-size:10.8333px">i<sub>k</sub></span><font face="monospace">])<sup>2</sup> * min(nums[</font>i<sub>0</sub><font face="monospace">],nums[</font>i<sub>1</sub><font face="monospace">] ... nums[</font><span style="font-size:10.8333px">i<sub>k</sub></span><font face="monospace">])</font></code> 。</li>
</ul>

<p>请你返回所有可能的 <strong>非空</strong> 英雄组的 <strong>力量</strong> 之和。由于答案可能非常大，请你将结果对&nbsp;<code>10<sup>9 </sup>+ 7</code>&nbsp;<strong>取余。</strong></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [2,1,4]
<b>输出：</b>141
<b>解释：</b>
第 1&nbsp;组：[2] 的力量为 2<sup>2</sup>&nbsp;* 2 = 8 。
第 2&nbsp;组：[1] 的力量为 1<sup>2</sup> * 1 = 1 。
第 3&nbsp;组：[4] 的力量为 4<sup>2</sup> * 4 = 64 。
第 4&nbsp;组：[2,1] 的力量为 2<sup>2</sup> * 1 = 4 。
第 5 组：[2,4] 的力量为 4<sup>2</sup> * 2 = 32 。
第 6&nbsp;组：[1,4] 的力量为 4<sup>2</sup> * 1 = 16 。
第​ ​​​​​​7&nbsp;组：[2,1,4] 的力量为 4<sup>2</sup>​​​​​​​ * 1 = 16 。
所有英雄组的力量之和为 8 + 1 + 64 + 4 + 32 + 16 + 16 = 141 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,1,1]
<b>输出：</b>7
<b>解释：</b>总共有 7 个英雄组，每一组的力量都是 1 。所以所有英雄组的力量之和为 7 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：排序 + 数学

我们注意到，题目中涉及到子序列的最大值和最小值，数组中元素的顺序不影响最终的结果，因此我们可以先对数组进行排序。

接下来，我们枚举每个元素作为子序列的最小值，不妨记数组的每个元素为 $a_1, a_2, \cdots, a_n$。以 $a_i$ 作为最小值的子序列的贡献为：

$$
a_i \times (a_{i}^{2} + a_{i+1}^2 + 2 \times a_{i+2}^2 + 4 \times a_{i+3}^2 + \cdots + 2^{n-i-1} \times a_n^2)
$$

我们注意到，每一个 $a_i$ 都会乘上 $a_i^2$，这一部分我们可以直接累加到答案中。剩下的部分，我们可以用一个变量 $p$ 来维护，初始时 $p = 0$。

接下来从右往左枚举 $a_i$，每次我们将 $a_i \times p$ 累加到答案中，然后令 $p = p \times 2 + a_i^2$。

枚举完所有的 $a_i$ 之后，返回答案即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def sumOfPower(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        nums.sort()
        ans = 0
        p = 0
        for x in nums[::-1]:
            ans = (ans + (x * x % mod) * x) % mod
            ans = (ans + x * p) % mod
            p = (p * 2 + x * x) % mod
        return ans
```

```java
class Solution {
    public int sumOfPower(int[] nums) {
        final int mod = (int) 1e9 + 7;
        Arrays.sort(nums);
        long ans = 0, p = 0;
        for (int i = nums.length - 1; i >= 0; --i) {
            long x = nums[i];
            ans = (ans + (x * x % mod) * x) % mod;
            ans = (ans + x * p % mod) % mod;
            p = (p * 2 + x * x % mod) % mod;
        }
        return (int) ans;
    }
}
```

```cpp
class Solution {
public:
    int sumOfPower(vector<int>& nums) {
        const int mod = 1e9 + 7;
        sort(nums.rbegin(), nums.rend());
        long long ans = 0, p = 0;
        for (long long x : nums) {
            ans = (ans + (x * x % mod) * x) % mod;
            ans = (ans + x * p % mod) % mod;
            p = (p * 2 + x * x % mod) % mod;
        }
        return ans;
    }
};
```

```go
func sumOfPower(nums []int) (ans int) {
	const mod = 1e9 + 7
	sort.Ints(nums)
	p := 0
	for i := len(nums) - 1; i >= 0; i-- {
		x := nums[i]
		ans = (ans + (x*x%mod)*x) % mod
		ans = (ans + x*p%mod) % mod
		p = (p*2 + x*x%mod) % mod
	}
	return
}
```

```ts
function sumOfPower(nums: number[]): number {
    const mod = 10 ** 9 + 7;
    nums.sort((a, b) => a - b);
    let ans = 0;
    let p = 0;
    for (let i = nums.length - 1; i >= 0; --i) {
        const x = BigInt(nums[i]);
        ans = (ans + Number((x * x * x) % BigInt(mod))) % mod;
        ans = (ans + Number((x * BigInt(p)) % BigInt(mod))) % mod;
        p = Number((BigInt(p) * 2n + x * x) % BigInt(mod));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->

# [2256. 最小平均差](https://leetcode.cn/problems/minimum-average-difference)

[English Version](/solution/2200-2299/2256.Minimum%20Average%20Difference/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>下标 <code>i</code>&nbsp;处的 <strong>平均差</strong>&nbsp;指的是 <code>nums</code>&nbsp;中 <strong>前</strong>&nbsp;<code>i + 1</code>&nbsp;个元素平均值和 <strong>后</strong>&nbsp;<code>n - i - 1</code>&nbsp;个元素平均值的 <strong>绝对差</strong>&nbsp;。两个平均值都需要 <strong>向下取整</strong>&nbsp;到最近的整数。</p>

<p>请你返回产生 <strong>最小平均差</strong>&nbsp;的下标。如果有多个下标最小平均差相等，请你返回 <strong>最小</strong>&nbsp;的一个下标。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>两个数的<strong>&nbsp;绝对差</strong>&nbsp;是两者差的绝对值。</li>
	<li>&nbsp;<code>n</code>&nbsp;个元素的平均值是 <code>n</code>&nbsp;个元素之 <strong>和</strong>&nbsp;除以（整数除法）&nbsp;<code>n</code>&nbsp;。</li>
	<li><code>0</code>&nbsp;个元素的平均值视为&nbsp;<code>0</code>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [2,5,3,9,5,3]
<b>输出：</b>3
<strong>解释：</strong>
- 下标 0 处的平均差为：|2 / 1 - (5 + 3 + 9 + 5 + 3) / 5| = |2 / 1 - 25 / 5| = |2 - 5| = 3 。
- 下标 1 处的平均差为：|(2 + 5) / 2 - (3 + 9 + 5 + 3) / 4| = |7 / 2 - 20 / 4| = |3 - 5| = 2 。
- 下标 2 处的平均差为：|(2 + 5 + 3) / 3 - (9 + 5 + 3) / 3| = |10 / 3 - 17 / 3| = |3 - 5| = 2 。
- 下标 3 处的平均差为：|(2 + 5 + 3 + 9) / 4 - (5 + 3) / 2| = |19 / 4 - 8 / 2| = |4 - 4| = 0 。 
- 下标 4 处的平均差为：|(2 + 5 + 3 + 9 + 5) / 5 - 3 / 1| = |24 / 5 - 3 / 1| = |4 - 3| = 1 。
- 下标 5 处的平均差为：|(2 + 5 + 3 + 9 + 5 + 3) / 6 - 0| = |27 / 6 - 0| = |4 - 0| = 4 。
下标 3 处的平均差为最小平均差，所以返回 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [0]
<b>输出：</b>0
<strong>解释：</strong>
唯一的下标是 0 ，所以我们返回 0 。
下标 0 处的平均差为：|0 / 1 - 0| = |0 - 0| = 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：遍历

我们直接遍历数组 $nums$，对于每个下标 $i$，维护前 $i + 1$ 个元素的和 $pre$ 和后 $n - i - 1$ 个元素的和 $suf$，计算平均差的绝对值 $t$，如果 $t$ 小于当前最小值 $mi$，则更新答案 $ans = i$ 和最小值 $mi = t$。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def minimumAverageDifference(self, nums: List[int]) -> int:
        pre, suf = 0, sum(nums)
        n = len(nums)
        ans, mi = 0, inf
        for i, x in enumerate(nums):
            pre += x
            suf -= x
            a = pre // (i + 1)
            b = 0 if n - i - 1 == 0 else suf // (n - i - 1)
            if (t := abs(a - b)) < mi:
                ans = i
                mi = t
        return ans
```

```java
class Solution {
    public int minimumAverageDifference(int[] nums) {
        int n = nums.length;
        long pre = 0, suf = 0;
        for (int x : nums) {
            suf += x;
        }
        int ans = 0;
        long mi = Long.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            pre += nums[i];
            suf -= nums[i];
            long a = pre / (i + 1);
            long b = n - i - 1 == 0 ? 0 : suf / (n - i - 1);
            long t = Math.abs(a - b);
            if (t < mi) {
                ans = i;
                mi = t;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumAverageDifference(vector<int>& nums) {
        int n = nums.size();
        using ll = long long;
        ll pre = 0;
        ll suf = accumulate(nums.begin(), nums.end(), 0LL);
        int ans = 0;
        ll mi = suf;
        for (int i = 0; i < n; ++i) {
            pre += nums[i];
            suf -= nums[i];
            ll a = pre / (i + 1);
            ll b = n - i - 1 == 0 ? 0 : suf / (n - i - 1);
            ll t = abs(a - b);
            if (t < mi) {
                ans = i;
                mi = t;
            }
        }
        return ans;
    }
};
```

```go
func minimumAverageDifference(nums []int) (ans int) {
	n := len(nums)
	pre, suf := 0, 0
	for _, x := range nums {
		suf += x
	}
	mi := suf
	for i, x := range nums {
		pre += x
		suf -= x
		a := pre / (i + 1)
		b := 0
		if n-i-1 != 0 {
			b = suf / (n - i - 1)
		}
		if t := abs(a - b); t < mi {
			ans = i
			mi = t
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function minimumAverageDifference(nums: number[]): number {
    const n = nums.length;
    let pre = 0;
    let suf = nums.reduce((a, b) => a + b);
    let ans = 0;
    let mi = suf;
    for (let i = 0; i < n; ++i) {
        pre += nums[i];
        suf -= nums[i];
        const a = Math.floor(pre / (i + 1));
        const b = n - i - 1 === 0 ? 0 : Math.floor(suf / (n - i - 1));
        const t = Math.abs(a - b);
        if (t < mi) {
            ans = i;
            mi = t;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->

# [2913. 子数组不同元素数目的平方和 I](https://leetcode.cn/problems/subarrays-distinct-element-sum-of-squares-i)

[English Version](/solution/2900-2999/2913.Subarrays%20Distinct%20Element%20Sum%20of%20Squares%20I/README_EN.md)

<!-- tags:数组,哈希表 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>定义 <code>nums</code>&nbsp;一个子数组的 <strong>不同计数</strong>&nbsp;值如下：</p>

<ul>
	<li>令&nbsp;<code>nums[i..j]</code>&nbsp;表示 <code>nums</code> 中所有下标在 <code>i</code> 到 <code>j</code> 范围内的元素构成的子数组（满足 <code>0 &lt;= i &lt;= j &lt; nums.length</code> ），那么我们称子数组&nbsp;<code>nums[i..j]</code>&nbsp;中不同值的数目为&nbsp;<code>nums[i..j]</code>&nbsp;的不同计数。</li>
</ul>

<p>请你返回 <code>nums</code>&nbsp;中所有子数组的 <strong>不同计数</strong>&nbsp;的 <strong>平方</strong>&nbsp;和。</p>

<p>由于答案可能会很大，请你将它对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p>子数组指的是一个数组里面一段连续 <strong>非空</strong>&nbsp;的元素序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,1]
<b>输出：</b>15
<b>解释：</b>六个子数组分别为：
[1]: 1 个互不相同的元素。
[2]: 1 个互不相同的元素。
[1]: 1 个互不相同的元素。
[1,2]: 2 个互不相同的元素。
[2,1]: 2 个互不相同的元素。
[1,2,1]: 2 个互不相同的元素。
所有不同计数的平方和为 1<sup>2</sup> + 1<sup>2</sup> + 1<sup>2</sup> + 2<sup>2</sup> + 2<sup>2</sup> + 2<sup>2</sup> = 15 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2,2]
<b>输出：3</b>
<strong>解释：</strong>三个子数组分别为：
[2]: 1 个互不相同的元素。
[2]: 1 个互不相同的元素。
[2,2]: 1 个互不相同的元素。
所有不同计数的平方和为 1<sup>2</sup> + 1<sup>2</sup> + 1<sup>2</sup> = 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## 解法

### 方法一：枚举

我们可以枚举子数组的左端点下标 $i$，对于每个 $i$，我们在 $[i, n)$ 的范围内枚举子数组的右端点下标 $j$，并统计 $nums[j]$ 的值，将其加入到集合 $s$ 中，记 $s$ 的大小为 $cnt$，那么 $nums[i..j]$ 的不同计数为 $cnt$，将其平方后加入到答案中。

枚举结束后，返回答案即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def sumCounts(self, nums: List[int]) -> int:
        ans, n = 0, len(nums)
        for i in range(n):
            s = set()
            for j in range(i, n):
                s.add(nums[j])
                ans += len(s) * len(s)
        return ans
```

```java
class Solution {
    public int sumCounts(List<Integer> nums) {
        int ans = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            int[] s = new int[101];
            int cnt = 0;
            for (int j = i; j < n; ++j) {
                if (++s[nums.get(j)] == 1) {
                    ++cnt;
                }
                ans += cnt * cnt;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int sumCounts(vector<int>& nums) {
        int ans = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            int s[101]{};
            int cnt = 0;
            for (int j = i; j < n; ++j) {
                if (++s[nums[j]] == 1) {
                    ++cnt;
                }
                ans += cnt * cnt;
            }
        }
        return ans;
    }
};
```

```go
func sumCounts(nums []int) (ans int) {
	for i := range nums {
		s := [101]int{}
		cnt := 0
		for _, x := range nums[i:] {
			s[x]++
			if s[x] == 1 {
				cnt++
			}
			ans += cnt * cnt
		}
	}
	return
}
```

```ts
function sumCounts(nums: number[]): number {
    let ans = 0;
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        const s: number[] = Array(101).fill(0);
        let cnt = 0;
        for (const x of nums.slice(i)) {
            if (++s[x] === 1) {
                ++cnt;
            }
            ans += cnt * cnt;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->

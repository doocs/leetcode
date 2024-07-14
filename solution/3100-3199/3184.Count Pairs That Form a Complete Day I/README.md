---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3184.Count%20Pairs%20That%20Form%20a%20Complete%20Day%20I/README.md
rating: 1149
source: 第 402 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 计数
---

<!-- problem:start -->

# [3184. 构成整天的下标对数目 I](https://leetcode.cn/problems/count-pairs-that-form-a-complete-day-i)

[English Version](/solution/3100-3199/3184.Count%20Pairs%20That%20Form%20a%20Complete%20Day%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>hours</code>，表示以 <strong>小时 </strong>为单位的时间，返回一个整数，表示满足 <code>i &lt; j</code> 且 <code>hours[i] + hours[j]</code> 构成 <strong>整天 </strong>的下标对&nbsp;<code>i</code>, <code>j</code> 的数目。</p>

<p><strong>整天 </strong>定义为时间持续时间是 24 小时的 <strong>整数倍 </strong>。</p>

<p>例如，1 天是 24 小时，2 天是 48 小时，3 天是 72 小时，以此类推。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">hours = [12,12,30,24,24]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>构成整天的下标对分别是 <code>(0, 1)</code> 和 <code>(3, 4)</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">hours = [72,48,24,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>构成整天的下标对分别是 <code>(0, 1)</code>、<code>(0, 2)</code> 和 <code>(1, 2)</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= hours.length &lt;= 100</code></li>
	<li><code>1 &lt;= hours[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们可以用一个哈希表或者一个长度为 $24$ 的数组 $\text{cnt}$ 来记录每个小时数模 $24$ 的出现次数。

遍历数组 $\text{hours}$，对于每个小时数 $x$，我们可以得出与 $x$ 相加为 $24$ 的倍数，且模 $24$ 之后的数为 $(24 - x \bmod 24) \bmod 24$。累加这个数在哈希表或者数组中的出现次数即可。然后我们将 $x$ 的模 $24$ 的出现次数加一。

遍历完数组 $\text{hours}$ 后，我们就可以得到满足题意的下标对数目。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\text{hours}$ 的长度。空间复杂度 $O(C)$，其中 $C=24$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countCompleteDayPairs(self, hours: List[int]) -> int:
        cnt = Counter()
        ans = 0
        for x in hours:
            ans += cnt[(24 - (x % 24)) % 24]
            cnt[x % 24] += 1
        return ans
```

#### Java

```java
class Solution {
    public int countCompleteDayPairs(int[] hours) {
        int[] cnt = new int[24];
        int ans = 0;
        for (int x : hours) {
            ans += cnt[(24 - x % 24) % 24];
            ++cnt[x % 24];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countCompleteDayPairs(vector<int>& hours) {
        int cnt[24]{};
        int ans = 0;
        for (int x : hours) {
            ans += cnt[(24 - x % 24) % 24];
            ++cnt[x % 24];
        }
        return ans;
    }
};
```

#### Go

```go
func countCompleteDayPairs(hours []int) (ans int) {
	cnt := [24]int{}
	for _, x := range hours {
		ans += cnt[(24-x%24)%24]
		cnt[x%24]++
	}
	return
}
```

#### TypeScript

```ts
function countCompleteDayPairs(hours: number[]): number {
    const cnt: number[] = Array(24).fill(0);
    let ans: number = 0;
    for (const x of hours) {
        ans += cnt[(24 - (x % 24)) % 24];
        ++cnt[x % 24];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

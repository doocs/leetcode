---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3186.Maximum%20Total%20Damage%20With%20Spell%20Casting/README.md
tags:
    - 数组
    - 哈希表
    - 双指针
    - 二分查找
    - 动态规划
    - 计数
    - 排序
---

<!-- problem:start -->

# [3186. 施咒的最大总伤害](https://leetcode.cn/problems/maximum-total-damage-with-spell-casting)

[English Version](/solution/3100-3199/3186.Maximum%20Total%20Damage%20With%20Spell%20Casting/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一个魔法师有许多不同的咒语。</p>

<p>给你一个数组&nbsp;<code>power</code>&nbsp;，其中每个元素表示一个咒语的伤害值，可能会有多个咒语有相同的伤害值。</p>

<p>已知魔法师使用伤害值为&nbsp;<code>power[i]</code>&nbsp;的咒语时，他们就&nbsp;<strong>不能</strong>&nbsp;使用伤害为&nbsp;<code>power[i] - 2</code>&nbsp;，<code>power[i] - 1</code>&nbsp;，<code>power[i] + 1</code>&nbsp;或者&nbsp;<code>power[i] + 2</code>&nbsp;的咒语。</p>

<p>每个咒语最多只能被使用 <strong>一次</strong>&nbsp;。</p>

<p>请你返回这个魔法师可以达到的伤害值之和的 <strong>最大值</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>power = [1,1,3,4]</span></p>

<p><span class="example-io"><b>输出：</b>6</span></p>

<p><strong>解释：</strong></p>

<p>可以使用咒语 0，1，3，伤害值分别为 1，1，4，总伤害值为 6 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>power = [7,1,6,6]</span></p>

<p><span class="example-io"><b>输出：</b>13</span></p>

<p><strong>解释：</strong></p>

<p>可以使用咒语 1，2，3，伤害值分别为 1，6，6，总伤害值为 13 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= power.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= power[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找 + 记忆化搜索

我们可以先对数组 $\text{power}$ 进行排序，用一个哈希表 $\text{cnt}$ 来记录每个伤害值的出现次数，然后遍历数组 $\text{power}$，对于每个伤害值 $x$，我们可以得出使用伤害值为 $x$ 的咒语时，可以使用的下一个伤害值的索引，即第一个大于 $x + 2$ 的伤害值的索引，我们可以使用二分查找来找到这个索引，记录在数组 $\text{nxt}$ 中。

接下来，我们定义一个函数 $\text{dfs}$，用来计算从第 $i$ 个伤害值开始，可以获得的最大伤害值。

在 $\text{dfs}$ 函数中，我们可以选择跳过当前伤害值，那么我们可以跳过当前伤害值的所有相同伤害值，直接跳到 $i + \text{cnt}[x]$，可以获得的伤害值为 $\text{dfs}(i + \text{cnt}[x])$；或者我们可以选择使用当前伤害值，那么我们可以使用当前伤害值的所有相同伤害值，然后跳到下一个伤害值的索引，可以获得的伤害值为 $x \times \text{cnt}[x] + \text{dfs}(\text{nxt}[i])$，其中 $\text{nxt}[i]$ 表示第一个大于 $x + 2$ 的伤害值的索引。我们取这两种情况的最大值作为函数的返回值。

为了避免重复计算，我们可以使用记忆化搜索，将已经计算过的结果保存在数组 $\text{f}$ 中，这样在计算 $\text{dfs}(i)$ 时，如果 $\text{f}[i]$ 不为 $0$，则直接返回 $\text{f}[i]$。

答案即为 $\text{dfs}(0)$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\text{power}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumTotalDamage(self, power: List[int]) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= n:
                return 0
            a = dfs(i + cnt[power[i]])
            b = power[i] * cnt[power[i]] + dfs(nxt[i])
            return max(a, b)

        n = len(power)
        cnt = Counter(power)
        power.sort()
        nxt = [bisect_right(power, x + 2, lo=i + 1) for i, x in enumerate(power)]
        return dfs(0)
```

#### Java

```java
class Solution {
    private Long[] f;
    private int[] power;
    private Map<Integer, Integer> cnt;
    private int[] nxt;
    private int n;

    public long maximumTotalDamage(int[] power) {
        Arrays.sort(power);
        this.power = power;
        n = power.length;
        f = new Long[n];
        cnt = new HashMap<>(n);
        nxt = new int[n];
        for (int i = 0; i < n; ++i) {
            cnt.merge(power[i], 1, Integer::sum);
            int l = Arrays.binarySearch(power, power[i] + 3);
            l = l < 0 ? -l - 1 : l;
            nxt[i] = l;
        }
        return dfs(0);
    }

    private long dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        long a = dfs(i + cnt.get(power[i]));
        long b = 1L * power[i] * cnt.get(power[i]) + dfs(nxt[i]);
        return f[i] = Math.max(a, b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumTotalDamage(vector<int>& power) {
        sort(power.begin(), power.end());
        this->power = power;
        n = power.size();
        f.resize(n);
        nxt.resize(n);
        for (int i = 0; i < n; ++i) {
            cnt[power[i]]++;
            nxt[i] = upper_bound(power.begin() + i + 1, power.end(), power[i] + 2) - power.begin();
        }
        return dfs(0);
    }

private:
    unordered_map<int, int> cnt;
    vector<long long> f;
    vector<int> power;
    vector<int> nxt;
    int n;

    long long dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i]) {
            return f[i];
        }
        long long a = dfs(i + cnt[power[i]]);
        long long b = 1LL * power[i] * cnt[power[i]] + dfs(nxt[i]);
        return f[i] = max(a, b);
    }
};
```

#### Go

```go
func maximumTotalDamage(power []int) int64 {
	n := len(power)
	sort.Ints(power)
	cnt := map[int]int{}
	nxt := make([]int, n)
	f := make([]int64, n)
	for i, x := range power {
		cnt[x]++
		nxt[i] = sort.SearchInts(power, x+3)
	}
	var dfs func(int) int64
	dfs = func(i int) int64 {
		if i >= n {
			return 0
		}
		if f[i] != 0 {
			return f[i]
		}
		a := dfs(i + cnt[power[i]])
		b := int64(power[i]*cnt[power[i]]) + dfs(nxt[i])
		f[i] = max(a, b)
		return f[i]
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function maximumTotalDamage(power: number[]): number {
    const n = power.length;
    power.sort((a, b) => a - b);
    const f: number[] = Array(n).fill(0);
    const cnt: Record<number, number> = {};
    const nxt: number[] = Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        cnt[power[i]] = (cnt[power[i]] || 0) + 1;
        let [l, r] = [i + 1, n];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (power[mid] > power[i] + 2) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        nxt[i] = l;
    }
    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i]) {
            return f[i];
        }
        const a = dfs(i + cnt[power[i]]);
        const b = power[i] * cnt[power[i]] + dfs(nxt[i]);
        return (f[i] = Math.max(a, b));
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

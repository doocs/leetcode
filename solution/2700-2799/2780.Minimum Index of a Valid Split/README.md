---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2780.Minimum%20Index%20of%20a%20Valid%20Split/README.md
rating: 1549
source: 第 354 场周赛 Q3
tags:
    - 数组
    - 哈希表
    - 排序
---

<!-- problem:start -->

# [2780. 合法分割的最小下标](https://leetcode.cn/problems/minimum-index-of-a-valid-split)

[English Version](/solution/2700-2799/2780.Minimum%20Index%20of%20a%20Valid%20Split/README_EN.md)

## 题目描述

<!-- description:start -->

<p>如果在长度为 <code>m</code>&nbsp;的整数数组 <code>arr</code>&nbsp;中 <strong>超过一半</strong> 的元素值为&nbsp;<code>x</code>，那么我们称 <code>x</code>&nbsp;是 <strong>支配元素</strong>&nbsp;。</p>

<p>给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;，数据保证它含有一个 <strong>支配</strong> 元素。</p>

<p>你需要在下标 <code>i</code>&nbsp;处将&nbsp;<code>nums</code>&nbsp;分割成两个数组&nbsp;<code>nums[0, ..., i]</code> 和&nbsp;<code>nums[i + 1, ..., n - 1]</code>&nbsp;，如果一个分割满足以下条件，我们称它是&nbsp;<strong>合法</strong>&nbsp;的：</p>

<ul>
	<li><code>0 &lt;= i &lt; n - 1</code></li>
	<li><code>nums[0, ..., i]</code>&nbsp;和&nbsp;<code>nums[i + 1, ..., n - 1]</code>&nbsp;的支配元素相同。</li>
</ul>

<p>这里，&nbsp;<code>nums[i, ..., j]</code>&nbsp;表示 <code>nums</code>&nbsp;的一个子数组，它开始于下标&nbsp;<code>i</code>&nbsp;，结束于下标&nbsp;<code>j</code>&nbsp;，两个端点都包含在子数组内。特别地，如果&nbsp;<code>j &lt; i</code>&nbsp;，那么&nbsp;<code>nums[i, ..., j]</code>&nbsp;表示一个空数组。</p>

<p>请你返回一个 <strong>合法分割</strong>&nbsp;的 <strong>最小</strong>&nbsp;下标。如果合法分割不存在，返回 <code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,2,2]
<b>输出：</b>2
<b>解释：</b>我们将数组在下标 2 处分割，得到 [1,2,2] 和 [2] 。
数组 [1,2,2] 中，元素 2 是支配元素，因为它在数组中出现了 2 次，且 2 * 2 &gt; 3 。
数组 [2] 中，元素 2 是支配元素，因为它在数组中出现了 1 次，且 1 * 2 &gt; 1 。
两个数组 [1,2,2] 和 [2] 都有与 nums 一样的支配元素，所以这是一个合法分割。
下标 2 是合法分割中的最小下标。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2,1,3,1,1,1,7,1,2,1]
<b>输出：</b>4
<b>解释：</b>我们将数组在下标 4 处分割，得到 [2,1,3,1,1] 和 [1,7,1,2,1] 。
数组 [2,1,3,1,1] 中，元素 1 是支配元素，因为它在数组中出现了 3 次，且 3 * 2 &gt; 5 。
数组 [1,7,1,2,1] 中，元素 1 是支配元素，因为它在数组中出现了 3 次，且 3 * 2 &gt; 5 。
两个数组 [2,1,3,1,1] 和 [1,7,1,2,1] 都有与 nums 一样的支配元素，所以这是一个合法分割。
下标 4 是所有合法分割中的最小下标。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [3,3,3,3,7,2,2]
<b>输出：</b>-1
<b>解释：</b>没有合法分割。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>nums</code>&nbsp;有且只有一个支配元素。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们用哈希表统计每个元素出现的次数，然后找出出现次数最多的元素 $x$，即为支配元素。要使得分割后的两个数组中都有支配元素，且支配元素相同，那么支配元素一定是 $x$。

接下来，我们只需要遍历数组 $nums$，累加前缀中 $x$ 的出现次数 $cur$，并判断 $x$ 在后缀中出现的次数是否满足要求即可。如果满足要求，那么当前下标 $i$ 就是一个可行的分割下标，我们只需要选择所有可行分割下标中最小的那个即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumIndex(self, nums: List[int]) -> int:
        x, cnt = Counter(nums).most_common(1)[0]
        cur = 0
        for i, v in enumerate(nums, 1):
            if v == x:
                cur += 1
                if cur * 2 > i and (cnt - cur) * 2 > len(nums) - i:
                    return i - 1
        return -1
```

#### Java

```java
class Solution {
    public int minimumIndex(List<Integer> nums) {
        int x = 0, cnt = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int v : nums) {
            int t = freq.merge(v, 1, Integer::sum);
            if (cnt < t) {
                cnt = t;
                x = v;
            }
        }
        int cur = 0;
        for (int i = 1; i <= nums.size(); ++i) {
            if (nums.get(i - 1) == x) {
                ++cur;
                if (cur * 2 > i && (cnt - cur) * 2 > nums.size() - i) {
                    return i - 1;
                }
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumIndex(vector<int>& nums) {
        int x = 0, cnt = 0;
        unordered_map<int, int> freq;
        for (int v : nums) {
            ++freq[v];
            if (freq[v] > cnt) {
                cnt = freq[v];
                x = v;
            }
        }
        int cur = 0;
        for (int i = 1; i <= nums.size(); ++i) {
            if (nums[i - 1] == x) {
                ++cur;
                if (cur * 2 > i && (cnt - cur) * 2 > nums.size() - i) {
                    return i - 1;
                }
            }
        }
        return -1;
    }
};
```

#### Go

```go
func minimumIndex(nums []int) int {
	x, cnt := 0, 0
	freq := map[int]int{}
	for _, v := range nums {
		freq[v]++
		if freq[v] > cnt {
			x, cnt = v, freq[v]
		}
	}
	cur := 0
	for i, v := range nums {
		i++
		if v == x {
			cur++
			if cur*2 > i && (cnt-cur)*2 > len(nums)-i {
				return i - 1
			}
		}
	}
	return -1
}
```

#### TypeScript

```ts
function minimumIndex(nums: number[]): number {
    let [x, cnt] = [0, 0];
    const freq: Map<number, number> = new Map();
    for (const v of nums) {
        freq.set(v, (freq.get(v) ?? 0) + 1);
        if (freq.get(v)! > cnt) {
            [x, cnt] = [v, freq.get(v)!];
        }
    }
    let cur = 0;
    for (let i = 1; i <= nums.length; ++i) {
        if (nums[i - 1] === x) {
            ++cur;
            if (cur * 2 > i && (cnt - cur) * 2 > nums.length - i) {
                return i - 1;
            }
        }
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

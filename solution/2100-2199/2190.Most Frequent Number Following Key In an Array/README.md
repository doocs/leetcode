---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2190.Most%20Frequent%20Number%20Following%20Key%20In%20an%20Array/README.md
rating: 1289
source: 第 73 场双周赛 Q1
tags:
    - 数组
    - 哈希表
    - 计数
---

<!-- problem:start -->

# [2190. 数组中紧跟 key 之后出现最频繁的数字](https://leetcode.cn/problems/most-frequent-number-following-key-in-an-array)

[English Version](/solution/2100-2199/2190.Most%20Frequent%20Number%20Following%20Key%20In%20an%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;，同时给你一个整数&nbsp;<code>key</code>&nbsp;，它在&nbsp;<code>nums</code>&nbsp;出现过。</p>

<p><strong>统计&nbsp;</strong>在 <code>nums</code>&nbsp;数组中紧跟着 <code>key</code>&nbsp;后面出现的不同整数&nbsp;<code>target</code>&nbsp;的出现次数。换言之，<code>target</code>&nbsp;的出现次数为满足以下条件的 <code>i</code>&nbsp;的数目：</p>

<ul>
	<li><code>0 &lt;= i &lt;= n - 2</code></li>
	<li><code>nums[i] == key</code>&nbsp;且</li>
	<li><code>nums[i + 1] == target</code>&nbsp;。</li>
</ul>

<p>请你返回出现 <strong>最多</strong>&nbsp;次数的<em>&nbsp;</em><code>target</code>&nbsp;。测试数据保证出现次数最多的 <code>target</code>&nbsp;是唯一的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,100,200,1,100], key = 1
<b>输出：</b>100
<b>解释：</b>对于 target = 100 ，在下标 1 和 4 处出现过 2 次，且都紧跟着 key 。
没有其他整数在 key 后面紧跟着出现，所以我们返回 100 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [2,2,2,2,3], key = 2
<b>输出：</b>2
<b>解释：</b>对于 target = 2 ，在下标 1 ，2 和 3 处出现过 3 次，且都紧跟着 key 。
对于 target = 3 ，在下标 4 出出现过 1 次，且紧跟着 key 。
target = 2 是紧跟着 key 之后出现次数最多的数字，所以我们返回 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li>测试数据保证答案是唯一的。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历计数

我们用一个哈希表或数组 $\textit{cnt}$ 记录每个 $\textit{target}$ 出现的次数，用一个变量 $\textit{mx}$ 维护 $\textit{target}$ 出现的最大次数，初始时 $\textit{mx} = 0$。

遍历数组 $\textit{nums}$，如果 $\textit{nums}[i] = \textit{key}$，则 $\textit{nums}[i + 1]$ 出现的次数 $\textit{cnt}[\textit{nums}[i + 1]]$ 加一，如果此时 $\textit{mx} \lt \textit{cnt}[\textit{nums}[i + 1]]$，则更新 $\textit{mx} = \textit{cnt}[\textit{nums}[i + 1]]$，并更新答案 $\textit{ans} = \textit{nums}[i + 1]$。

遍历结束后，返回答案 $\textit{ans}$。

时间复杂度 $O(n)$，空间复杂度 $O(M)$。其中 $n$ 和 $M$ 分别为数组 $\textit{nums}$ 的长度和数组 $\textit{nums}$ 中元素的最大值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def mostFrequent(self, nums: List[int], key: int) -> int:
        cnt = Counter()
        ans = mx = 0
        for a, b in pairwise(nums):
            if a == key:
                cnt[b] += 1
                if mx < cnt[b]:
                    mx = cnt[b]
                    ans = b
        return ans
```

#### Java

```java
class Solution {
    public int mostFrequent(int[] nums, int key) {
        int[] cnt = new int[1001];
        int ans = 0, mx = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] == key) {
                if (mx < ++cnt[nums[i + 1]]) {
                    mx = cnt[nums[i + 1]];
                    ans = nums[i + 1];
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int mostFrequent(vector<int>& nums, int key) {
        int cnt[1001]{};
        int ans = 0, mx = 0;
        for (int i = 0; i < nums.size() - 1; ++i) {
            if (nums[i] == key) {
                if (mx < ++cnt[nums[i + 1]]) {
                    mx = cnt[nums[i + 1]];
                    ans = nums[i + 1];
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func mostFrequent(nums []int, key int) (ans int) {
	cnt := [1001]int{}
	mx := 0
	for i, x := range nums[1:] {
		if nums[i] == key {
			cnt[x]++
			if mx < cnt[x] {
				mx = cnt[x]
				ans = x
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function mostFrequent(nums: number[], key: number): number {
    const cnt: number[] = Array(Math.max(...nums) + 1).fill(0);
    let [ans, mx] = [0, 0];
    for (let i = 0; i < nums.length - 1; ++i) {
        if (nums[i] === key) {
            if (mx < ++cnt[nums[i + 1]]) {
                mx = cnt[nums[i + 1]];
                ans = nums[i + 1];
            }
        }
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} key
 * @return {number}
 */
var mostFrequent = function (nums, key) {
    const cnt = Array(Math.max(...nums) + 1).fill(0);
    let [ans, mx] = [0, 0];
    for (let i = 0; i < nums.length - 1; ++i) {
        if (nums[i] === key) {
            if (mx < ++cnt[nums[i + 1]]) {
                mx = cnt[nums[i + 1]];
                ans = nums[i + 1];
            }
        }
    }
    return ans;
};
```

#### PHP

```php
class Solution {
    function mostFrequent($nums, $key) {
        $cnt = array_fill(0, max($nums) + 1, 0);
        $ans = 0;
        $mx = 0;
        for ($i = 0; $i < count($nums) - 1; ++$i) {
            if ($nums[$i] === $key) {
                $cnt[$nums[$i + 1]]++;
                if ($mx < $cnt[$nums[$i + 1]]) {
                    $mx = $cnt[$nums[$i + 1]];
                    $ans = $nums[$i + 1];
                }
            }
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

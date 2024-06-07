---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0923.3Sum%20With%20Multiplicity/README.md
tags:
    - 数组
    - 哈希表
    - 双指针
    - 计数
    - 排序
---

<!-- problem:start -->

# [923. 三数之和的多种可能](https://leetcode.cn/problems/3sum-with-multiplicity)

[English Version](/solution/0900-0999/0923.3Sum%20With%20Multiplicity/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组<meta charset="UTF-8" />&nbsp;<code>arr</code>&nbsp;，以及一个整数&nbsp;<code>target</code>&nbsp;作为目标值，返回满足 <code>i &lt; j &lt; k</code> 且<meta charset="UTF-8" />&nbsp;<code>arr[i] + arr[j] + arr[k] == target</code>&nbsp;的元组&nbsp;<code>i, j, k</code>&nbsp;的数量。</p>

<p>由于结果会非常大，请返回 <code>10<sup>9</sup>&nbsp;+ 7</code>&nbsp;的模。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,1,2,2,3,3,4,4,5,5], target = 8
<strong>输出：</strong>20
<strong>解释：</strong>
按值枚举(arr[i], arr[j], arr[k])：
(1, 2, 5) 出现 8 次；
(1, 3, 4) 出现 8 次；
(2, 2, 4) 出现 2 次；
(2, 3, 3) 出现 2 次。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,1,2,2,2,2], target = 5
<strong>输出：</strong>12
<strong>解释：</strong>
arr[i] = 1, arr[j] = arr[k] = 2 出现 12 次：
我们从 [1,1] 中选择一个 1，有 2 种情况，
从 [2,2,2,2] 中选出两个 2，有 6 种情况。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= arr.length &lt;= 3000</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 100</code></li>
	<li><code>0 &lt;= target &lt;= 300</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数 + 枚举

我们可以用一个哈希表或者一个长度为 $101$ 的数组 $cnt$ 统计数组 $arr$ 中每个元素的出现次数。

然后，我们枚举数组 $arr$ 中的每个元素 $arr[j]$，先将 $cnt[arr[j]]$ 减一，然后再枚举 $arr[j]$ 之前的元素 $arr[i]$，计算 $c = target - arr[i] - arr[j]$，如果 $c$ 在 $[0, 100]$ 的范围内，那么答案就加上 $cnt[c]$，最后返回答案。

注意，这里的答案可能会超过 ${10}^9 + 7$，所以在每次加法操作后都要取模。

时间复杂度 $O(n^2)$，其中 $n$ 为数组 $arr$ 的长度。空间复杂度 $O(C)$，其中 $C$ 为数组 $arr$ 中元素的最大值，本题中 $C = 100$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def threeSumMulti(self, arr: List[int], target: int) -> int:
        mod = 10**9 + 7
        cnt = Counter(arr)
        ans = 0
        for j, b in enumerate(arr):
            cnt[b] -= 1
            for a in arr[:j]:
                c = target - a - b
                ans = (ans + cnt[c]) % mod
        return ans
```

#### Java

```java
class Solution {
    public int threeSumMulti(int[] arr, int target) {
        final int mod = (int) 1e9 + 7;
        int[] cnt = new int[101];
        for (int x : arr) {
            ++cnt[x];
        }
        int n = arr.length;
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            --cnt[arr[j]];
            for (int i = 0; i < j; ++i) {
                int c = target - arr[i] - arr[j];
                if (c >= 0 && c < cnt.length) {
                    ans = (ans + cnt[c]) % mod;
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
    int threeSumMulti(vector<int>& arr, int target) {
        const int mod = 1e9 + 7;
        int cnt[101]{};
        for (int x : arr) {
            ++cnt[x];
        }
        int n = arr.size();
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            --cnt[arr[j]];
            for (int i = 0; i < j; ++i) {
                int c = target - arr[i] - arr[j];
                if (c >= 0 && c <= 100) {
                    ans = (ans + cnt[c]) % mod;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func threeSumMulti(arr []int, target int) (ans int) {
	const mod int = 1e9 + 7
	cnt := [101]int{}
	for _, x := range arr {
		cnt[x]++
	}
	for j, b := range arr {
		cnt[b]--
		for _, a := range arr[:j] {
			if c := target - a - b; c >= 0 && c < len(cnt) {
				ans = (ans + cnt[c]) % mod
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function threeSumMulti(arr: number[], target: number): number {
    const mod = 10 ** 9 + 7;
    const cnt: number[] = Array(101).fill(0);
    for (const x of arr) {
        ++cnt[x];
    }
    let ans = 0;
    const n = arr.length;
    for (let j = 0; j < n; ++j) {
        --cnt[arr[j]];
        for (let i = 0; i < j; ++i) {
            const c = target - arr[i] - arr[j];
            if (c >= 0 && c < cnt.length) {
                ans = (ans + cnt[c]) % mod;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

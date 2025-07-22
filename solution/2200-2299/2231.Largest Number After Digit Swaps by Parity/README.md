---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2231.Largest%20Number%20After%20Digit%20Swaps%20by%20Parity/README.md
rating: 1365
source: 第 288 场周赛 Q1
tags:
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [2231. 按奇偶性交换后的最大数字](https://leetcode.cn/problems/largest-number-after-digit-swaps-by-parity)

[English Version](/solution/2200-2299/2231.Largest%20Number%20After%20Digit%20Swaps%20by%20Parity/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数 <code>num</code> 。你可以交换 <code>num</code> 中 <strong>奇偶性</strong> 相同的任意两位数字（即，都是奇数或者偶数）。</p>

<p>返回交换 <strong>任意</strong> 次之后 <code>num</code> 的 <strong>最大</strong> 可能值<em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>num = 1234
<strong>输出：</strong>3412
<strong>解释：</strong>交换数字 3 和数字 1 ，结果得到 3214 。
交换数字 2 和数字 4 ，结果得到 3412 。
注意，可能存在其他交换序列，但是可以证明 3412 是最大可能值。
注意，不能交换数字 4 和数字 1 ，因为它们奇偶性不同。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>num = 65875
<strong>输出：</strong>87655
<strong>解释：</strong>交换数字 8 和数字 6 ，结果得到 85675 。
交换数字 5 和数字 7 ，结果得到 87655 。
注意，可能存在其他交换序列，但是可以证明 87655 是最大可能值。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们可以用一个长度为 $10$ 的数组 $\textit{cnt}$ 统计整数 $\textit{num}$ 中所有数字出现的次数，用一个下标数组 $\textit{idx}$ 记录当前最大可用偶数和奇数，初始时 $\textit{idx}$ 为 $[8, 9]$。

接下来，我们依次遍历整数 $\textit{num}$ 的每个数字，如果数字为奇数，则取 $\textit{idx}$ 下标为 $1$ 对应的数字，否则取下标为 $0$ 对应的数字。如果该数字出现的次数为 $0$，则需要将数字减 $2$，继续判断，直到存在满足条件的数。然后，我们更新答案，以及数字出现的次数，继续遍历，直到遍历到整数 $\textit{num}$。

时间复杂度 $O(\log \textit{num})$，空间复杂度 $O(\log \textit{num})$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestInteger(self, num: int) -> int:
        nums = [int(c) for c in str(num)]
        cnt = Counter(nums)
        idx = [8, 9]
        ans = 0
        for x in nums:
            while cnt[idx[x & 1]] == 0:
                idx[x & 1] -= 2
            ans = ans * 10 + idx[x & 1]
            cnt[idx[x & 1]] -= 1
        return ans
```

#### Java

```java
class Solution {
    public int largestInteger(int num) {
        char[] s = String.valueOf(num).toCharArray();
        int[] cnt = new int[10];
        for (char c : s) {
            ++cnt[c - '0'];
        }
        int[] idx = {8, 9};
        int ans = 0;
        for (char c : s) {
            int x = c - '0';
            while (cnt[idx[x & 1]] == 0) {
                idx[x & 1] -= 2;
            }
            ans = ans * 10 + idx[x & 1];
            cnt[idx[x & 1]]--;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int largestInteger(int num) {
        string s = to_string(num);
        int cnt[10] = {0};
        for (char c : s) {
            cnt[c - '0']++;
        }
        int idx[2] = {8, 9};
        int ans = 0;
        for (char c : s) {
            int x = c - '0';
            while (cnt[idx[x & 1]] == 0) {
                idx[x & 1] -= 2;
            }
            ans = ans * 10 + idx[x & 1];
            cnt[idx[x & 1]]--;
        }
        return ans;
    }
};
```

#### Go

```go
func largestInteger(num int) int {
	s := []byte(fmt.Sprint(num))
	cnt := [10]int{}

	for _, c := range s {
		cnt[c-'0']++
	}

	idx := [2]int{8, 9}
	ans := 0

	for _, c := range s {
		x := int(c - '0')
		for cnt[idx[x&1]] == 0 {
			idx[x&1] -= 2
		}
		ans = ans*10 + idx[x&1]
		cnt[idx[x&1]]--
	}

	return ans
}
```

#### TypeScript

```ts
function largestInteger(num: number): number {
    const s = num.toString().split('');
    const cnt = Array(10).fill(0);

    for (const c of s) {
        cnt[+c]++;
    }

    const idx = [8, 9];
    let ans = 0;

    for (const c of s) {
        const x = +c;
        while (cnt[idx[x % 2]] === 0) {
            idx[x % 2] -= 2;
        }
        ans = ans * 10 + idx[x % 2];
        cnt[idx[x % 2]]--;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：分组 + 排序

<!-- solution:end -->

<!-- problem:end -->

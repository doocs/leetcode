---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2094.Finding%203-Digit%20Even%20Numbers/README.md
rating: 1454
source: 第 270 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 枚举
    - 排序
---

<!-- problem:start -->

# [2094. 找出 3 位偶数](https://leetcode.cn/problems/finding-3-digit-even-numbers)

[English Version](/solution/2000-2099/2094.Finding%203-Digit%20Even%20Numbers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>digits</code> ，其中每个元素是一个数字（<code>0 - 9</code>）。数组中可能存在重复元素。</p>

<p>你需要找出 <strong>所有</strong> 满足下述条件且 <strong>互不相同</strong> 的整数：</p>

<ul>
	<li>该整数由 <code>digits</code> 中的三个元素按 <strong>任意</strong> 顺序 <strong>依次连接</strong> 组成。</li>
	<li>该整数不含 <strong>前导零</strong></li>
	<li>该整数是一个 <strong>偶数</strong></li>
</ul>

<p>例如，给定的 <code>digits</code> 是 <code>[1, 2, 3]</code> ，整数 <code>132</code> 和 <code>312</code> 满足上面列出的全部条件。</p>

<p>将找出的所有互不相同的整数按 <strong>递增顺序</strong> 排列，并以数组形式返回<em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>digits = [2,1,3,0]
<strong>输出：</strong>[102,120,130,132,210,230,302,310,312,320]
<strong>解释：</strong>
所有满足题目条件的整数都在输出数组中列出。 
注意，答案数组中不含有 <strong>奇数</strong> 或带 <strong>前导零</strong> 的整数。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>digits = [2,2,8,8,2]
<strong>输出：</strong>[222,228,282,288,822,828,882]
<strong>解释：</strong>
同样的数字（0 - 9）在构造整数时可以重复多次，重复次数最多与其在 <code>digits</code> 中出现的次数一样。 
在这个例子中，数字 8 在构造 288、828 和 882 时都重复了两次。 
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>digits = [3,7,5]
<strong>输出：</strong>[]
<strong>解释：</strong>
使用给定的 digits 无法构造偶数。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;=&nbsp;digits.length &lt;= 100</code></li>
	<li><code>0 &lt;= digits[i] &lt;= 9</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数 + 枚举

我们先统计 $\textit{digits}$ 中每个数字出现的次数，记录在数组或哈希表 $\textit{cnt}$ 中。

然后，我们在 $[100, 1000)$ 的范围内枚举所有的偶数，判断这个偶数的每一位数字是否都不超过 $\textit{cnt}$ 中对应的数字的次数。如果是，则将这个偶数加入答案数组中。

最后，返回答案数组。

时间复杂度 $O(k \times 10^k)$，其中 $k$ 是目标偶数的位数，本题中 $k = 3$。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findEvenNumbers(self, digits: List[int]) -> List[int]:
        cnt = Counter(digits)
        ans = []
        for x in range(100, 1000, 2):
            cnt1 = Counter()
            y = x
            while y:
                y, v = divmod(y, 10)
                cnt1[v] += 1
            if all(cnt[i] >= cnt1[i] for i in range(10)):
                ans.append(x)
        return ans
```

#### Java

```java
class Solution {
    public int[] findEvenNumbers(int[] digits) {
        int[] cnt = new int[10];
        for (int x : digits) {
            ++cnt[x];
        }
        List<Integer> ans = new ArrayList<>();
        for (int x = 100; x < 1000; x += 2) {
            int[] cnt1 = new int[10];
            for (int y = x; y > 0; y /= 10) {
                ++cnt1[y % 10];
            }
            boolean ok = true;
            for (int i = 0; i < 10 && ok; ++i) {
                ok = cnt[i] >= cnt1[i];
            }
            if (ok) {
                ans.add(x);
            }
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> findEvenNumbers(vector<int>& digits) {
        int cnt[10]{};
        for (int x : digits) {
            ++cnt[x];
        }
        vector<int> ans;
        for (int x = 100; x < 1000; x += 2) {
            int cnt1[10]{};
            for (int y = x; y; y /= 10) {
                ++cnt1[y % 10];
            }
            bool ok = true;
            for (int i = 0; i < 10 && ok; ++i) {
                ok = cnt[i] >= cnt1[i];
            }
            if (ok) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findEvenNumbers(digits []int) (ans []int) {
	cnt := [10]int{}
	for _, x := range digits {
		cnt[x]++
	}
	for x := 100; x < 1000; x += 2 {
		cnt1 := [10]int{}
		for y := x; y > 0; y /= 10 {
			cnt1[y%10]++
		}
		ok := true
		for i := 0; i < 10 && ok; i++ {
			ok = cnt[i] >= cnt1[i]
		}
		if ok {
			ans = append(ans, x)
		}
	}
	return
}
```

#### TypeScript

```ts
function findEvenNumbers(digits: number[]): number[] {
    const cnt: number[] = Array(10).fill(0);
    for (const x of digits) {
        ++cnt[x];
    }
    const ans: number[] = [];
    for (let x = 100; x < 1000; x += 2) {
        const cnt1: number[] = Array(10).fill(0);
        for (let y = x; y; y = Math.floor(y / 10)) {
            ++cnt1[y % 10];
        }
        let ok = true;
        for (let i = 0; i < 10 && ok; ++i) {
            ok = cnt[i] >= cnt1[i];
        }
        if (ok) {
            ans.push(x);
        }
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[]} digits
 * @return {number[]}
 */
var findEvenNumbers = function (digits) {
    const cnt = Array(10).fill(0);
    for (const x of digits) {
        ++cnt[x];
    }
    const ans = [];
    for (let x = 100; x < 1000; x += 2) {
        const cnt1 = Array(10).fill(0);
        for (let y = x; y; y = Math.floor(y / 10)) {
            ++cnt1[y % 10];
        }
        let ok = true;
        for (let i = 0; i < 10 && ok; ++i) {
            ok = cnt[i] >= cnt1[i];
        }
        if (ok) {
            ans.push(x);
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

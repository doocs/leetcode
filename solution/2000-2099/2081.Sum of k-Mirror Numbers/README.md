---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2081.Sum%20of%20k-Mirror%20Numbers/README.md
rating: 2209
source: 第 268 场周赛 Q4
tags:
    - 数学
    - 枚举
---

<!-- problem:start -->

# [2081. k 镜像数字的和](https://leetcode.cn/problems/sum-of-k-mirror-numbers)

[English Version](/solution/2000-2099/2081.Sum%20of%20k-Mirror%20Numbers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一个 <strong>k 镜像数字</strong>&nbsp;指的是一个在十进制和 k 进制下从前往后读和从后往前读都一样的&nbsp;<strong>没有前导 0</strong>&nbsp;的&nbsp;<strong>正</strong>&nbsp;整数。</p>

<ul>
	<li>比方说，<code>9</code>&nbsp;是一个 2 镜像数字。<code>9</code>&nbsp;在十进制下为&nbsp;<code>9</code>&nbsp;，二进制下为&nbsp;<code>1001</code>&nbsp;，两者从前往后读和从后往前读都一样。</li>
	<li>相反地，<code>4</code>&nbsp;不是一个 2 镜像数字。<code>4</code>&nbsp;在二进制下为&nbsp;<code>100</code>&nbsp;，从前往后和从后往前读不相同。</li>
</ul>

<p>给你进制&nbsp;<code>k</code>&nbsp;和一个数字&nbsp;<code>n</code>&nbsp;，请你返回 k 镜像数字中 <strong>最小</strong> 的 <code>n</code>&nbsp;个数 <strong>之和</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre><b>输入：</b>k = 2, n = 5
<b>输出：</b>25
<strong>解释：
</strong>最小的 5 个 2 镜像数字和它们的二进制表示如下：
  十进制       二进制
    1          1
    3          11
    5          101
    7          111
    9          1001
它们的和为 1 + 3 + 5 + 7 + 9 = 25 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>k = 3, n = 7
<b>输出：</b>499
<strong>解释：
</strong>7 个最小的 3 镜像数字和它们的三进制表示如下：
  十进制       三进制
    1          1
    2          2
    4          11
    8          22
    121        11111
    151        12121
    212        21212
它们的和为 1 + 2 + 4 + 8 + 121 + 151 + 212 = 499 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>k = 7, n = 17
<b>输出：</b>20379000
<b>解释：</b>17 个最小的 7 镜像数字分别为：
1, 2, 3, 4, 5, 6, 8, 121, 171, 242, 292, 16561, 65656, 2137312, 4602064, 6597956, 6958596
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= k &lt;= 9</code></li>
	<li><code>1 &lt;= n &lt;= 30</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：折半枚举 + 数学

对于一个 k 镜像数字，我们可以将其分为两部分：前半部分和后半部分。对于偶数长度的数字，前半部分和后半部分完全相同；对于奇数长度的数字，前半部分和后半部分相同，但中间的数字可以是任意数字。

我们可以通过枚举前半部分的数字，然后根据前半部分构造出完整的 k 镜像数字。具体步骤如下：

1. **枚举长度**：从 1 开始枚举数字的长度，直到找到满足条件的 k 镜像数字。
2. **计算前半部分的范围**：对于长度为 $l$ 的数字，前半部分的范围是 $[10^{(l-1)/2}, 10^{(l+1)/2})$。
3. **构造 k 镜像数字**：对于每个前半部分的数字 $i$，如果长度为偶数，则直接将 $i$ 作为前半部分；如果长度为奇数，则将 $i$ 除以 10 得到前半部分。然后将前半部分的数字反转并添加到后半部分，构造出完整的 k 镜像数字。
4. **检查 k 镜像数字**：将构造出的数字转换为 k 进制，检查其是否是回文数。
5. **累加结果**：如果是 k 镜像数字，则将其累加到结果中，并减少计数器 $n$。当计数器 $n$ 减至 0 时，返回结果。

时间复杂度主要取决于枚举的长度和前半部分的范围。由于 $n$ 的最大值为 30，因此在实际操作中，枚举的次数是有限的。空间复杂度 $O(1)$，因为我们只使用了常数级别的额外空间。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kMirror(self, k: int, n: int) -> int:
        def check(x: int, k: int) -> bool:
            s = []
            while x:
                s.append(x % k)
                x //= k
            return s == s[::-1]

        ans = 0
        for l in count(1):
            x = 10 ** ((l - 1) // 2)
            y = 10 ** ((l + 1) // 2)
            for i in range(x, y):
                v = i
                j = i if l % 2 == 0 else i // 10
                while j > 0:
                    v = v * 10 + j % 10
                    j //= 10
                if check(v, k):
                    ans += v
                    n -= 1
                    if n == 0:
                        return ans
```

#### Java

```java
class Solution {
    public long kMirror(int k, int n) {
        long ans = 0;
        for (int l = 1;; l++) {
            int x = (int) Math.pow(10, (l - 1) / 2);
            int y = (int) Math.pow(10, (l + 1) / 2);
            for (int i = x; i < y; i++) {
                long v = i;
                int j = (l % 2 == 0) ? i : i / 10;
                while (j > 0) {
                    v = v * 10 + j % 10;
                    j /= 10;
                }
                if (check(v, k)) {
                    ans += v;
                    n--;
                    if (n == 0) {
                        return ans;
                    }
                }
            }
        }
    }

    private boolean check(long x, int k) {
        List<Integer> s = new ArrayList<>();
        while (x > 0) {
            s.add((int) (x % k));
            x /= k;
        }
        for (int i = 0, j = s.size() - 1; i < j; ++i, --j) {
            if (!s.get(i).equals(s.get(j))) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long kMirror(int k, int n) {
        long long ans = 0;
        for (int l = 1;; ++l) {
            int x = pow(10, (l - 1) / 2);
            int y = pow(10, (l + 1) / 2);
            for (int i = x; i < y; ++i) {
                long long v = i;
                int j = (l % 2 == 0) ? i : i / 10;
                while (j > 0) {
                    v = v * 10 + j % 10;
                    j /= 10;
                }
                if (check(v, k)) {
                    ans += v;
                    if (--n == 0) {
                        return ans;
                    }
                }
            }
        }
    }

private:
    bool check(long long x, int k) {
        vector<int> s;
        while (x > 0) {
            s.push_back(x % k);
            x /= k;
        }
        for (int i = 0, j = s.size() - 1; i < j; ++i, --j) {
            if (s[i] != s[j]) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func kMirror(k int, n int) int64 {
	check := func(x int64, k int) bool {
		s := []int{}
		for x > 0 {
			s = append(s, int(x%int64(k)))
			x /= int64(k)
		}
		for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
			if s[i] != s[j] {
				return false
			}
		}
		return true
	}

	var ans int64 = 0
	for l := 1; ; l++ {
		x := pow10((l - 1) / 2)
		y := pow10((l + 1) / 2)
		for i := x; i < y; i++ {
			v := int64(i)
			j := i
			if l%2 != 0 {
				j = i / 10
			}
			for j > 0 {
				v = v*10 + int64(j%10)
				j /= 10
			}
			if check(v, k) {
				ans += v
				n--
				if n == 0 {
					return ans
				}
			}
		}
	}
}

func pow10(exp int) int {
	res := 1
	for i := 0; i < exp; i++ {
		res *= 10
	}
	return res
}
```

#### TypeScript

```ts
function kMirror(k: number, n: number): number {
    function check(x: number, k: number): boolean {
        const s: number[] = [];
        while (x > 0) {
            s.push(x % k);
            x = Math.floor(x / k);
        }
        for (let i = 0, j = s.length - 1; i < j; i++, j--) {
            if (s[i] !== s[j]) {
                return false;
            }
        }
        return true;
    }

    let ans = 0;
    for (let l = 1; ; l++) {
        const x = Math.pow(10, Math.floor((l - 1) / 2));
        const y = Math.pow(10, Math.floor((l + 1) / 2));
        for (let i = x; i < y; i++) {
            let v = i;
            let j = l % 2 === 0 ? i : Math.floor(i / 10);
            while (j > 0) {
                v = v * 10 + (j % 10);
                j = Math.floor(j / 10);
            }
            if (check(v, k)) {
                ans += v;
                n--;
                if (n === 0) {
                    return ans;
                }
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

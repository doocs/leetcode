---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3483.Unique%203-Digit%20Even%20Numbers/README.md
tags:
    - 递归
    - 数组
    - 哈希表
    - 枚举
---

<!-- problem:start -->

# [3483. 不同三位偶数的数目](https://leetcode.cn/problems/unique-3-digit-even-numbers)

[English Version](/solution/3400-3499/3483.Unique%203-Digit%20Even%20Numbers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数字数组 <code>digits</code>，你需要从中选择三个数字组成一个三位偶数，你的任务是求出&nbsp;<strong>不同&nbsp;</strong>三位偶数的数量。</p>

<p><strong>注意</strong>：每个数字在三位偶数中都只能使用&nbsp;<strong>一次&nbsp;</strong>，并且&nbsp;<strong>不能&nbsp;</strong>有前导零。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">digits = [1,2,3,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">12</span></p>

<p><strong>解释：</strong> 可以形成的 12 个不同的三位偶数是 124，132，134，142，214，234，312，314，324，342，412 和 432。注意，不能形成 222，因为数字 2 只有一个。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">digits = [0,2,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong> 可以形成的三位偶数是 202 和 220。注意，数字 2 可以使用两次，因为数组中有两个 2 。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">digits = [6,6,6]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong> 只能形成 666。</p>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">digits = [1,3,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong> 无法形成三位偶数。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= digits.length &lt;= 10</code></li>
	<li><code>0 &lt;= digits[i] &lt;= 9</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 枚举

我们用一个哈希表 $\textit{s}$ 记录所有不同的三位偶数，然后枚举所有可能的三位偶数，将其加入哈希表中。

最后返回哈希表的大小即可。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^3)$。其中 $n$ 为数组 $\textit{digits}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def totalNumbers(self, digits: List[int]) -> int:
        s = set()
        for i, a in enumerate(digits):
            if a & 1:
                continue
            for j, b in enumerate(digits):
                if i == j:
                    continue
                for k, c in enumerate(digits):
                    if c == 0 or k in (i, j):
                        continue
                    s.add(c * 100 + b * 10 + a)
        return len(s)
```

#### Java

```java
class Solution {
    public int totalNumbers(int[] digits) {
        Set<Integer> s = new HashSet<>();
        int n = digits.length;
        for (int i = 0; i < n; ++i) {
            if (digits[i] % 2 == 1) {
                continue;
            }
            for (int j = 0; j < n; ++j) {
                if (i == j) {
                    continue;
                }
                for (int k = 0; k < n; ++k) {
                    if (digits[k] == 0 || k == i || k == j) {
                        continue;
                    }
                    s.add(digits[k] * 100 + digits[j] * 10 + digits[i]);
                }
            }
        }
        return s.size();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int totalNumbers(vector<int>& digits) {
        unordered_set<int> s;
        int n = digits.size();
        for (int i = 0; i < n; ++i) {
            if (digits[i] % 2 == 1) {
                continue;
            }
            for (int j = 0; j < n; ++j) {
                if (i == j) {
                    continue;
                }
                for (int k = 0; k < n; ++k) {
                    if (digits[k] == 0 || k == i || k == j) {
                        continue;
                    }
                    s.insert(digits[k] * 100 + digits[j] * 10 + digits[i]);
                }
            }
        }
        return s.size();
    }
};
```

#### Go

```go
func totalNumbers(digits []int) int {
	s := make(map[int]struct{})
	for i, a := range digits {
		if a%2 == 1 {
			continue
		}
		for j, b := range digits {
			if i == j {
				continue
			}
			for k, c := range digits {
				if c == 0 || k == i || k == j {
					continue
				}
				s[c*100+b*10+a] = struct{}{}
			}
		}
	}
	return len(s)
}
```

#### TypeScript

```ts
function totalNumbers(digits: number[]): number {
    const s = new Set<number>();
    const n = digits.length;
    for (let i = 0; i < n; ++i) {
        if (digits[i] % 2 === 1) {
            continue;
        }
        for (let j = 0; j < n; ++j) {
            if (i === j) {
                continue;
            }
            for (let k = 0; k < n; ++k) {
                if (digits[k] === 0 || k === i || k === j) {
                    continue;
                }
                s.add(digits[k] * 100 + digits[j] * 10 + digits[i]);
            }
        }
    }
    return s.size;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

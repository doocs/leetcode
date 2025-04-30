---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3483.Unique%203-Digit%20Even%20Numbers/README_EN.md
rating: 1323
source: Biweekly Contest 152 Q1
tags:
    - Recursion
    - Array
    - Hash Table
    - Enumeration
---

<!-- problem:start -->

# [3483. Unique 3-Digit Even Numbers](https://leetcode.com/problems/unique-3-digit-even-numbers)

[中文文档](/solution/3400-3499/3483.Unique%203-Digit%20Even%20Numbers/README.md)

## Description

<!-- description:start -->

<p>You are given an array of digits called <code>digits</code>. Your task is to determine the number of <strong>distinct</strong> three-digit even numbers that can be formed using these digits.</p>

<p><strong>Note</strong>: Each <em>copy</em> of a digit can only be used <strong>once per number</strong>, and there may <strong>not</strong> be leading zeros.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">digits = [1,2,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong> The 12 distinct 3-digit even numbers that can be formed are 124, 132, 134, 142, 214, 234, 312, 314, 324, 342, 412, and 432. Note that 222 cannot be formed because there is only 1 copy of the digit 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">digits = [0,2,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong> The only 3-digit even numbers that can be formed are 202 and 220. Note that the digit 2 can be used twice because it appears twice in the array.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">digits = [6,6,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong> Only 666 can be formed.</p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">digits = [1,3,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong> No even 3-digit numbers can be formed.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= digits.length &lt;= 10</code></li>
	<li><code>0 &lt;= digits[i] &lt;= 9</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Set + Enumeration

We use a hash set $\textit{s}$ to record all distinct three-digit even numbers, and then enumerate all possible three-digit even numbers to add them to the hash set.

Finally, we return the size of the hash set.

The time complexity is $O(n^3)$, and the space complexity is $O(n^3)$. Where $n$ is the length of the array $\textit{digits}$.

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

---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1502.Can%20Make%20Arithmetic%20Progression%20From%20Sequence/README.md
rating: 1154
source: 第 196 场周赛 Q1
tags:
    - 数组
    - 排序
---

<!-- problem:start -->

# [1502. 判断能否形成等差数列](https://leetcode.cn/problems/can-make-arithmetic-progression-from-sequence)

[English Version](/solution/1500-1599/1502.Can%20Make%20Arithmetic%20Progression%20From%20Sequence/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数字数组 <code>arr</code> 。</p>

<p>如果一个数列中，任意相邻两项的差总等于同一个常数，那么这个数列就称为 <strong>等差数列</strong> 。</p>

<p>如果可以重新排列数组形成等差数列，请返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [3,5,1]
<strong>输出：</strong>true
<strong>解释：</strong>对数组重新排序得到 [1,3,5] 或者 [5,3,1] ，任意相邻两项的差分别为 2 或 -2 ，可以形成等差数列。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [1,2,4]
<strong>输出：</strong>false
<strong>解释：</strong>无法通过重新排序得到等差数列。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>-10^6 &lt;= arr[i] &lt;= 10^6</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 遍历

我们可以先将数组 $\textit{arr}$ 排序，然后计算前两项的差值 $d$，接着遍历数组，判断相邻两项的差是否等于 $d$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 $\textit{arr}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canMakeArithmeticProgression(self, arr: List[int]) -> bool:
        arr.sort()
        d = arr[1] - arr[0]
        return all(b - a == d for a, b in pairwise(arr))
```

#### Java

```java
class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int d = arr[1] - arr[0];
        for (int i = 2; i < arr.length; ++i) {
            if (arr[i] - arr[i - 1] != d) {
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
    bool canMakeArithmeticProgression(vector<int>& arr) {
        sort(arr.begin(), arr.end());
        int d = arr[1] - arr[0];
        for (int i = 2; i < arr.size(); i++) {
            if (arr[i] - arr[i - 1] != d) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func canMakeArithmeticProgression(arr []int) bool {
	sort.Ints(arr)
	d := arr[1] - arr[0]
	for i := 2; i < len(arr); i++ {
		if arr[i]-arr[i-1] != d {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function canMakeArithmeticProgression(arr: number[]): boolean {
    arr.sort((a, b) => a - b);
    const n = arr.length;
    const d = arr[1] - arr[0];
    for (let i = 2; i < n; i++) {
        if (arr[i] - arr[i - 1] !== d) {
            return false;
        }
    }
    return true;
}
```

#### Rust

```rust
impl Solution {
    pub fn can_make_arithmetic_progression(mut arr: Vec<i32>) -> bool {
        arr.sort();
        let n = arr.len();
        let d = arr[1] - arr[0];
        for i in 2..n {
            if arr[i] - arr[i - 1] != d {
                return false;
            }
        }
        true
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} arr
 * @return {boolean}
 */
var canMakeArithmeticProgression = function (arr) {
    arr.sort((a, b) => a - b);
    const n = arr.length;
    const d = arr[1] - arr[0];
    for (let i = 2; i < n; i++) {
        if (arr[i] - arr[i - 1] !== d) {
            return false;
        }
    }
    return true;
};
```

#### C

```c
int cmp(const void* a, const void* b) {
    return *(int*) a - *(int*) b;
}

bool canMakeArithmeticProgression(int* arr, int arrSize) {
    qsort(arr, arrSize, sizeof(int), cmp);
    int d = arr[1] - arr[0];
    for (int i = 2; i < arrSize; i++) {
        if (arr[i] - arr[i - 1] != d) {
            return 0;
        }
    }
    return 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：哈希表 + 数学

我们先找出数组 $\textit{arr}$ 中的最小值 $a$ 和最大值 $b$，如果数组 $\textit{arr}$ 可以重排成等差数列，那么公差 $d = \frac{b - a}{n - 1}$ 必须为整数。

我们可以用哈希表来记录数组 $\textit{arr}$ 中的所有元素，然后遍历 $i \in [0, n)$，判断 $a + d \times i$ 是否在哈希表中，如果不在，说明数组 $\textit{arr}$ 不能重排成等差数列，返回 `false`。否则遍历完数组后，返回 `true`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{arr}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canMakeArithmeticProgression(self, arr: List[int]) -> bool:
        a = min(arr)
        b = max(arr)
        n = len(arr)
        if (b - a) % (n - 1):
            return False
        d = (b - a) // (n - 1)
        s = set(arr)
        return all(a + d * i in s for i in range(n))
```

#### Java

```java
class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        int n = arr.length;
        int a = arr[0], b = arr[0];
        Set<Integer> s = new HashSet<>();
        for (int x : arr) {
            a = Math.min(a, x);
            b = Math.max(b, x);
            s.add(x);
        }
        if ((b - a) % (n - 1) != 0) {
            return false;
        }
        int d = (b - a) / (n - 1);
        for (int i = 0; i < n; ++i) {
            if (!s.contains(a + d * i)) {
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
    bool canMakeArithmeticProgression(vector<int>& arr) {
        auto [a, b] = minmax_element(arr.begin(), arr.end());
        int n = arr.size();
        if ((*b - *a) % (n - 1) != 0) {
            return false;
        }
        int d = (*b - *a) / (n - 1);
        unordered_set<int> s(arr.begin(), arr.end());
        for (int i = 0; i < n; ++i) {
            if (!s.count(*a + d * i)) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func canMakeArithmeticProgression(arr []int) bool {
	a, b := slices.Min(arr), slices.Max(arr)
	n := len(arr)
	if (b-a)%(n-1) != 0 {
		return false
	}
	d := (b - a) / (n - 1)
	s := map[int]bool{}
	for _, x := range arr {
		s[x] = true
	}
	for i := 0; i < n; i++ {
		if !s[a+i*d] {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function canMakeArithmeticProgression(arr: number[]): boolean {
    const n = arr.length;
    const a = Math.min(...arr);
    const b = Math.max(...arr);

    if ((b - a) % (n - 1) !== 0) {
        return false;
    }

    const d = (b - a) / (n - 1);
    const s = new Set(arr);

    for (let i = 0; i < n; ++i) {
        if (!s.has(a + d * i)) {
            return false;
        }
    }
    return true;
}
```

#### Rust

```rust
impl Solution {
    pub fn can_make_arithmetic_progression(arr: Vec<i32>) -> bool {
        let n = arr.len();
        let a = *arr.iter().min().unwrap();
        let b = *arr.iter().max().unwrap();

        if (b - a) % (n as i32 - 1) != 0 {
            return false;
        }

        let d = (b - a) / (n as i32 - 1);
        let s: std::collections::HashSet<_> = arr.into_iter().collect();

        for i in 0..n {
            if !s.contains(&(a + d * i as i32)) {
                return false;
            }
        }
        true
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} arr
 * @return {boolean}
 */
var canMakeArithmeticProgression = function (arr) {
    const n = arr.length;
    const a = Math.min(...arr);
    const b = Math.max(...arr);

    if ((b - a) % (n - 1) !== 0) {
        return false;
    }

    const d = (b - a) / (n - 1);
    const s = new Set(arr);

    for (let i = 0; i < n; ++i) {
        if (!s.has(a + d * i)) {
            return false;
        }
    }
    return true;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

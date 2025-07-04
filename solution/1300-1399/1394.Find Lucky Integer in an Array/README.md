---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1394.Find%20Lucky%20Integer%20in%20an%20Array/README.md
rating: 1118
source: 第 182 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 计数
---

<!-- problem:start -->

# [1394. 找出数组中的幸运数](https://leetcode.cn/problems/find-lucky-integer-in-an-array)

[English Version](/solution/1300-1399/1394.Find%20Lucky%20Integer%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在整数数组中，如果一个整数的出现频次和它的数值大小相等，我们就称这个整数为「幸运数」。</p>

<p>给你一个整数数组 <code>arr</code>，请你从中找出并返回一个幸运数。</p>

<ul>
	<li>如果数组中存在多个幸运数，只需返回 <strong>最大</strong> 的那个。</li>
	<li>如果数组中不含幸运数，则返回 <strong>-1 </strong>。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [2,2,3,4]
<strong>输出：</strong>2
<strong>解释：</strong>数组中唯一的幸运数是 2 ，因为数值 2 的出现频次也是 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [1,2,2,3,3,3]
<strong>输出：</strong>3
<strong>解释：</strong>1、2 以及 3 都是幸运数，只需要返回其中最大的 3 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>arr = [2,2,2,3,3]
<strong>输出：</strong>-1
<strong>解释：</strong>数组中不存在幸运数。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>arr = [5]
<strong>输出：</strong>-1
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>arr = [7,7,7,7,7,7,7]
<strong>输出：</strong>7
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 500</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 500</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们可以用哈希表或数组 $\textit{cnt}$ 统计 $\textit{arr}$ 中每个数字出现的次数，然后遍历 $\textit{cnt}$，找到满足 $\textit{cnt}[x] = x$ 的最大的 $x$ 即可。如果没有这样的 $x$，则返回 $-1$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为 $\textit{arr}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findLucky(self, arr: List[int]) -> int:
        cnt = Counter(arr)
        return max((x for x, v in cnt.items() if x == v), default=-1)
```

#### Java

```java
class Solution {
    public int findLucky(int[] arr) {
        int[] cnt = new int[501];
        for (int x : arr) {
            ++cnt[x];
        }
        for (int x = cnt.length - 1; x > 0; --x) {
            if (x == cnt[x]) {
                return x;
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
    int findLucky(vector<int>& arr) {
        int cnt[501]{};
        for (int x : arr) {
            ++cnt[x];
        }
        for (int x = 500; x; --x) {
            if (x == cnt[x]) {
                return x;
            }
        }
        return -1;
    }
};
```

#### Go

```go
func findLucky(arr []int) int {
	cnt := [501]int{}
	for _, x := range arr {
		cnt[x]++
	}
	for x := len(cnt) - 1; x > 0; x-- {
		if x == cnt[x] {
			return x
		}
	}
	return -1
}
```

#### TypeScript

```ts
function findLucky(arr: number[]): number {
    const cnt: number[] = Array(501).fill(0);
    for (const x of arr) {
        ++cnt[x];
    }
    for (let x = cnt.length - 1; x; --x) {
        if (x === cnt[x]) {
            return x;
        }
    }
    return -1;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn find_lucky(arr: Vec<i32>) -> i32 {
        let mut cnt = HashMap::new();
        arr.iter().for_each(|&x| *cnt.entry(x).or_insert(0) += 1);
        cnt.iter()
            .filter(|(&x, &v)| x == v)
            .map(|(&x, _)| x)
            .max()
            .unwrap_or(-1)
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $arr
     * @return Integer
     */
    function findLucky($arr) {
        $cnt = array_fill(0, 501, 0);
        foreach ($arr as $x) {
            $cnt[$x]++;
        }
        for ($x = 500; $x > 0; $x--) {
            if ($cnt[$x] === $x) {
                return $x;
            }
        }
        return -1;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

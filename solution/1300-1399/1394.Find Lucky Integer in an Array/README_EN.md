---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1394.Find%20Lucky%20Integer%20in%20an%20Array/README_EN.md
rating: 1118
source: Weekly Contest 182 Q1
tags:
    - Array
    - Hash Table
    - Counting
---

<!-- problem:start -->

# [1394. Find Lucky Integer in an Array](https://leetcode.com/problems/find-lucky-integer-in-an-array)

[中文文档](/solution/1300-1399/1394.Find%20Lucky%20Integer%20in%20an%20Array/README.md)

## Description

<!-- description:start -->

<p>Given an array of integers <code>arr</code>, a <strong>lucky integer</strong> is an integer that has a frequency in the array equal to its value.</p>

<p>Return <em>the largest <strong>lucky integer</strong> in the array</em>. If there is no <strong>lucky integer</strong> return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,2,3,4]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The only lucky number in the array is 2 because frequency[2] == 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,2,3,3,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong> 1, 2 and 3 are all lucky numbers, return the largest of them.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,2,2,3,3]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There are no lucky numbers in the array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 500</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 500</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We can use a hash table or an array $\textit{cnt}$ to count the occurrences of each number in $\textit{arr}$. Then, we iterate through $\textit{cnt}$ to find the largest $x$ such that $\textit{cnt}[x] = x$. If there is no such $x$, return $-1$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the $\textit{arr}$.

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

---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1287.Element%20Appearing%20More%20Than%2025%25%20In%20Sorted%20Array/README_EN.md
rating: 1179
source: Biweekly Contest 15 Q1
tags:
    - Array
---

<!-- problem:start -->

# [1287. Element Appearing More Than 25% In Sorted Array](https://leetcode.com/problems/element-appearing-more-than-25-in-sorted-array)

[中文文档](/solution/1200-1299/1287.Element%20Appearing%20More%20Than%2025%25%20In%20Sorted%20Array/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <strong>sorted</strong> in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time, return that integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,2,6,6,6,6,7,10]
<strong>Output:</strong> 6
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,1]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Traversal

We traverse the array $\textit{arr}$ from the beginning. For each element $\textit{arr}[i]$, we check if $\textit{arr}[i]$ is equal to $\textit{arr}[i + \left\lfloor \frac{n}{4} \right\rfloor]$, where $n$ is the length of the array. If they are equal, then $\textit{arr}[i]$ is the element we are looking for, and we return it directly.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{arr}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findSpecialInteger(self, arr: List[int]) -> int:
        n = len(arr)
        for i, x in enumerate(arr):
            if x == arr[(i + (n >> 2))]:
                return x
```

#### Java

```java
class Solution {
    public int findSpecialInteger(int[] arr) {
        for (int i = 0;; ++i) {
            if (arr[i] == (arr[i + (arr.length >> 2)])) {
                return arr[i];
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findSpecialInteger(vector<int>& arr) {
        for (int i = 0;; ++i) {
            if (arr[i] == (arr[i + (arr.size() >> 2)])) {
                return arr[i];
            }
        }
    }
};
```

#### Go

```go
func findSpecialInteger(arr []int) int {
	for i := 0; ; i++ {
		if arr[i] == arr[i+len(arr)/4] {
			return arr[i]
		}
	}
}
```

#### TypeScript

```ts
function findSpecialInteger(arr: number[]): number {
    const n = arr.length;
    for (let i = 0; ; ++i) {
        if (arr[i] === arr[i + (n >> 2)]) {
            return arr[i];
        }
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} arr
 * @return {number}
 */
var findSpecialInteger = function (arr) {
    const n = arr.length;
    for (let i = 0; ; ++i) {
        if (arr[i] === arr[i + (n >> 2)]) {
            return arr[i];
        }
    }
};
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $arr
     * @return Integer
     */
    function findSpecialInteger($arr) {
        $n = count($arr);
        for ($i = 0; ; ++$i) {
            if ($arr[$i] == $arr[$i + ($n >> 2)]) {
                return $arr[$i];
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

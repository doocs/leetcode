---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1356.Sort%20Integers%20by%20The%20Number%20of%201%20Bits/README_EN.md
rating: 1257
source: Biweekly Contest 20 Q1
tags:
    - Bit Manipulation
    - Array
    - Counting
    - Sorting
---

<!-- problem:start -->

# [1356. Sort Integers by The Number of 1 Bits](https://leetcode.com/problems/sort-integers-by-the-number-of-1-bits)

[中文文档](/solution/1300-1399/1356.Sort%20Integers%20by%20The%20Number%20of%201%20Bits/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>arr</code>. Sort the integers in the array&nbsp;in ascending order by the number of <code>1</code>&#39;s&nbsp;in their binary representation and in case of two or more integers have the same number of <code>1</code>&#39;s you have to sort them in ascending order.</p>

<p>Return <em>the array after sorting it</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [0,1,2,3,4,5,6,7,8]
<strong>Output:</strong> [0,1,2,4,8,3,5,6,7]
<strong>Explantion:</strong> [0] is the only integer with 0 bits.
[1,2,4,8] all have 1 bit.
[3,5,6] have 2 bits.
[7] has 3 bits.
The sorted array by bits is [0,1,2,4,8,3,5,6,7]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1024,512,256,128,64,32,16,8,4,2,1]
<strong>Output:</strong> [1,2,4,8,16,32,64,128,256,512,1024]
<strong>Explantion:</strong> All integers have 1 bit in the binary representation, you should just sort them in ascending order.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 500</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Custom Sorting

We sort the array $arr$ according to the requirements of the problem, that is, sort in ascending order according to the number of $1$s in the binary representation. If there are multiple numbers with the same number of $1$s in the binary representation, they must be sorted in ascending order by numerical value.

The time complexity is $O(n \log n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $arr$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sortByBits(self, arr: List[int]) -> List[int]:
        return sorted(arr, key=lambda x: (x.bit_count(), x))
```

#### Java

```java
class Solution {
    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            arr[i] += Integer.bitCount(arr[i]) * 100000;
        }
        Arrays.sort(arr);
        for (int i = 0; i < n; ++i) {
            arr[i] %= 100000;
        }
        return arr;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> sortByBits(vector<int>& arr) {
        for (int& x : arr) {
            x += __builtin_popcount(x) * 100000;
        }
        ranges::sort(arr);
        for (int& x : arr) {
            x %= 100000;
        }
        return arr;
    }
};
```

#### Go

```go
func sortByBits(arr []int) []int {
	for i, v := range arr {
		arr[i] += bits.OnesCount(uint(v)) * 100000
	}
	sort.Ints(arr)
	for i := range arr {
		arr[i] %= 100000
	}
	return arr
}
```

#### TypeScript

```ts
function sortByBits(arr: number[]): number[] {
    const n = arr.length;

    for (let i = 0; i < n; ++i) {
        arr[i] += bitCount(arr[i]) * 100000;
    }

    arr.sort((a, b) => a - b);

    for (let i = 0; i < n; ++i) {
        arr[i] %= 100000;
    }

    return arr;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

#### Rust

```rust
impl Solution {
    pub fn sort_by_bits(mut arr: Vec<i32>) -> Vec<i32> {
        let n = arr.len();

        for i in 0..n {
            arr[i] += arr[i].count_ones() as i32 * 100000;
        }

        arr.sort();

        for i in 0..n {
            arr[i] %= 100000;
        }

        arr
    }
}
```

#### C

```c
static int bitCount(int x) {
    int cnt = 0;
    while (x) {
        x &= (x - 1);
        ++cnt;
    }
    return cnt;
}

static int cmp(const void* a, const void* b) {
    int x = *(const int*) a;
    int y = *(const int*) b;

    int cx = bitCount(x);
    int cy = bitCount(y);

    if (cx != cy) {
        return cx - cy;
    }
    return x - y;
}

/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* sortByBits(int* arr, int arrSize, int* returnSize) {
    *returnSize = arrSize;

    int* res = (int*) malloc(sizeof(int) * arrSize);
    if (!res) {
        return NULL;
    }

    for (int i = 0; i < arrSize; ++i) {
        res[i] = arr[i];
    }

    qsort(res, arrSize, sizeof(int), cmp);

    return res;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

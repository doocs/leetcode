---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1356.Sort%20Integers%20by%20The%20Number%20of%201%20Bits/README.md
rating: 1257
source: 第 20 场双周赛 Q1
tags:
    - 位运算
    - 数组
    - 计数
    - 排序
---

<!-- problem:start -->

# [1356. 根据数字二进制下 1 的数目排序](https://leetcode.cn/problems/sort-integers-by-the-number-of-1-bits)

[English Version](/solution/1300-1399/1356.Sort%20Integers%20by%20The%20Number%20of%201%20Bits/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>arr</code>&nbsp;。请你将数组中的元素按照其二进制表示中数字 <strong>1</strong> 的数目升序排序。</p>

<p>如果存在多个数字二进制中&nbsp;<strong>1</strong>&nbsp;的数目相同，则必须将它们按照数值大小升序排列。</p>

<p>请你返回排序后的数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [0,1,2,3,4,5,6,7,8]
<strong>输出：</strong>[0,1,2,4,8,3,5,6,7]
<strong>解释：</strong>[0] 是唯一一个有 0 个 1 的数。
[1,2,4,8] 都有 1 个 1 。
[3,5,6] 有 2 个 1 。
[7] 有 3 个 1 。
按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [1024,512,256,128,64,32,16,8,4,2,1]
<strong>输出：</strong>[1,2,4,8,16,32,64,128,256,512,1024]
<strong>解释：</strong>数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>arr = [10000,10000]
<strong>输出：</strong>[10000,10000]
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>arr = [2,3,5,7,11,13,17,19]
<strong>输出：</strong>[2,3,5,17,7,11,13,19]
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>arr = [10,100,1000,10000]
<strong>输出：</strong>[10,100,10000,1000]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 500</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10^4</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：自定义排序

我们将数组 $arr$ 按照题目要求排序，即按照二进制表示中数字 $1$ 的数目升序排序，如果存在多个数字二进制中 $1$ 的数目相同，则必须将它们按照数值大小升序排列。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $arr$ 的长度。

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

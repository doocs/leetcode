# [1356. Sort Integers by The Number of 1 Bits](https://leetcode.com/problems/sort-integers-by-the-number-of-1-bits)

[中文文档](/solution/1300-1399/1356.Sort%20Integers%20by%20The%20Number%20of%201%20Bits/README.md)

## Description

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

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sortByBits(self, arr: List[int]) -> List[int]:
        return sorted(arr, key=lambda x: (x.bit_count(), x))
```

### **Java**

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

```java
class Solution {
    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        Integer[] t = new Integer[n];
        for (int i = 0; i < n; ++i) {
            t[i] = arr[i];
        }
        Arrays.sort(t, (a, b) -> {
            int x = Integer.bitCount(a), y = Integer.bitCount(b);
            return x == y ? a - b : x - y;
        });
        for (int i = 0; i < n; ++i) {
            arr[i] = t[i];
        }
        return arr;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> sortByBits(vector<int>& arr) {
        for (int& v : arr) {
            v += __builtin_popcount(v) * 100000;
        }
        sort(arr.begin(), arr.end());
        for (int& v : arr) {
            v %= 100000;
        }
        return arr;
    }
};
```

```cpp
class Solution {
public:
    vector<int> sortByBits(vector<int>& arr) {
        sort(arr.begin(), arr.end(), [&](auto& a, auto& b) -> bool {
            int x = __builtin_popcount(a), y = __builtin_popcount(b);
            return x < y || (x == y && a < b);
        });
        return arr;
    }
};
```

### **Go**

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

```go
func sortByBits(arr []int) []int {
	sort.Slice(arr, func(i, j int) bool {
		a, b := bits.OnesCount(uint(arr[i])), bits.OnesCount(uint(arr[j]))
		return a < b || (a == b && arr[i] < arr[j])
	})
	return arr
}
```

### **TypeScript**

```ts
function sortByBits(arr: number[]): number[] {
    const countOnes = (n: number) => {
        let res = 0;
        while (n) {
            n &= n - 1;
            res++;
        }
        return res;
    };
    return arr.sort((a, b) => countOnes(a) - countOnes(b) || a - b);
}
```

### **Rust**

```rust
impl Solution {
    pub fn sort_by_bits(mut arr: Vec<i32>) -> Vec<i32> {
        arr.sort_by(|a, b| {
            let res = a.count_ones().cmp(&b.count_ones());
            if res == std::cmp::Ordering::Equal {
                return a.cmp(&b);
            }
            res
        });
        arr
    }
}
```

### **C**

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int countOnes(int n) {
    int res = 0;
    while (n) {
        n &= n - 1;
        res++;
    }
    return res;
}

int cmp(const void *_a, const void *_b) {
    int a = *(int *) _a;
    int b = *(int *) _b;
    int res = countOnes(a) - countOnes(b);
    if (res == 0) {
        return a - b;
    }
    return res;
}

int *sortByBits(int *arr, int arrSize, int *returnSize) {
    qsort(arr, arrSize, sizeof(int), cmp);
    *returnSize = arrSize;
    return arr;
}
```

### **...**

```

```

<!-- tabs:end -->

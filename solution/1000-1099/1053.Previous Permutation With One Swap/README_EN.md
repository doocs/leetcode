# [1053. Previous Permutation With One Swap](https://leetcode.com/problems/previous-permutation-with-one-swap)

[中文文档](/solution/1000-1099/1053.Previous%20Permutation%20With%20One%20Swap/README.md)

## Description

<p>Given an array of positive integers <code>arr</code> (not necessarily distinct), return <em>the </em><span data-keyword="lexicographically-smaller-array"><em>lexicographically</em></span><em> largest permutation that is smaller than</em> <code>arr</code>, that can be <strong>made with exactly one swap</strong>. If it cannot be done, then return the same array.</p>

<p><strong>Note</strong> that a <em>swap</em> exchanges the positions of two numbers <code>arr[i]</code> and <code>arr[j]</code></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [3,2,1]
<strong>Output:</strong> [3,1,2]
<strong>Explanation:</strong> Swapping 2 and 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,1,5]
<strong>Output:</strong> [1,1,5]
<strong>Explanation:</strong> This is already the smallest permutation.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,9,4,6,7]
<strong>Output:</strong> [1,7,4,6,9]
<strong>Explanation:</strong> Swapping 9 and 7.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def prevPermOpt1(self, arr: List[int]) -> List[int]:
        n = len(arr)
        for i in range(n - 1, 0, -1):
            if arr[i - 1] > arr[i]:
                for j in range(n - 1, i - 1, -1):
                    if arr[j] < arr[i - 1] and arr[j] != arr[j - 1]:
                        arr[i - 1], arr[j] = arr[j], arr[i - 1]
                        return arr
        return arr
```

### **Java**

```java
class Solution {
    public int[] prevPermOpt1(int[] arr) {
        int n = arr.length;
        for (int i = n - 1; i > 0; --i) {
            if (arr[i - 1] > arr[i]) {
                for (int j = n - 1; j > i - 1; --j) {
                    if (arr[j] < arr[i - 1] && arr[j] != arr[j - 1]) {
                        int t = arr[i - 1];
                        arr[i - 1] = arr[j];
                        arr[j] = t;
                        return arr;
                    }
                }
            }
        }
        return arr;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> prevPermOpt1(vector<int>& arr) {
        int n = arr.size();
        for (int i = n - 1; i > 0; --i) {
            if (arr[i - 1] > arr[i]) {
                for (int j = n - 1; j > i - 1; --j) {
                    if (arr[j] < arr[i - 1] && arr[j] != arr[j - 1]) {
                        swap(arr[i - 1], arr[j]);
                        return arr;
                    }
                }
            }
        }
        return arr;
    }
};
```

### **Go**

```go
func prevPermOpt1(arr []int) []int {
	n := len(arr)
	for i := n - 1; i > 0; i-- {
		if arr[i-1] > arr[i] {
			for j := n - 1; j > i-1; j-- {
				if arr[j] < arr[i-1] && arr[j] != arr[j-1] {
					arr[i-1], arr[j] = arr[j], arr[i-1]
					return arr
				}
			}
		}
	}
	return arr
}
```

### **TypeScript**

```ts
function prevPermOpt1(arr: number[]): number[] {
    const n = arr.length;
    for (let i = n - 1; i > 0; --i) {
        if (arr[i - 1] > arr[i]) {
            for (let j = n - 1; j > i - 1; --j) {
                if (arr[j] < arr[i - 1] && arr[j] !== arr[j - 1]) {
                    const t = arr[i - 1];
                    arr[i - 1] = arr[j];
                    arr[j] = t;
                    return arr;
                }
            }
        }
    }
    return arr;
}
```

### **...**

```

```

<!-- tabs:end -->

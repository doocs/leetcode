# [1588. Sum of All Odd Length Subarrays](https://leetcode.com/problems/sum-of-all-odd-length-subarrays)

[中文文档](/solution/1500-1599/1588.Sum%20of%20All%20Odd%20Length%20Subarrays/README.md)

## Description

<p>Given an array of positive integers <code>arr</code>, return <em>the sum of all possible <strong>odd-length subarrays</strong> of </em><code>arr</code>.</p>

<p>A <strong>subarray</strong> is a contiguous subsequence of the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,4,2,5,3]
<strong>Output:</strong> 58
<strong>Explanation: </strong>The odd-length subarrays of arr and their sums are:
[1] = 1
[4] = 4
[2] = 2
[5] = 5
[3] = 3
[1,4,2] = 7
[4,2,5] = 11
[2,5,3] = 10
[1,4,2,5,3] = 15
If we add all these together we get 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2]
<strong>Output:</strong> 3
<b>Explanation: </b>There are only 2 subarrays of odd length, [1] and [2]. Their sum is 3.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [10,11,12]
<strong>Output:</strong> 66
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 100</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>

<p>Could you solve this problem in O(n) time complexity?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sumOddLengthSubarrays(self, arr: List[int]) -> int:
        ans, n = 0, len(arr)
        for i in range(n):
            s = 0
            for j in range(i, n):
                s += arr[j]
                if (j - i + 1) & 1:
                    ans += s
        return ans
```

### **Java**

```java
class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int s = 0;
            for (int j = i; j < n; ++j) {
                s += arr[j];
                if ((j - i + 1) % 2 == 1) {
                    ans += s;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int sumOddLengthSubarrays(vector<int>& arr) {
        int n = arr.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int s = 0;
            for (int j = i; j < n; ++j) {
                s += arr[j];
                if ((j - i + 1) & 1) {
                    ans += s;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func sumOddLengthSubarrays(arr []int) (ans int) {
	n := len(arr)
	for i := range arr {
		s := 0
		for j := i; j < n; j++ {
			s += arr[j]
			if (j-i+1)%2 == 1 {
				ans += s
			}
		}
	}
	return
}
```

### **TypeScript**

```ts
function sumOddLengthSubarrays(arr: number[]): number {
    const n = arr.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        let s = 0;
        for (let j = i; j < n; ++j) {
            s += arr[j];
            if ((j - i + 1) % 2 === 1) {
                ans += s;
            }
        }
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn sum_odd_length_subarrays(arr: Vec<i32>) -> i32 {
        let n = arr.len();
        let mut ans = 0;
        for i in 0..n {
            let mut s = 0;
            for j in i..n {
                s += arr[j];
                if (j - i + 1) % 2 == 1 {
                    ans += s;
                }
            }
        }
        ans
    }
}
```

### **C**

```c
int sumOddLengthSubarrays(int* arr, int arrSize){
    int ans = 0;
    for (int i = 0; i < arrSize; ++i) {
        int s = 0;
        for (int j = i; j < arrSize; ++j) {
            s += arr[j];
            if ((j - i + 1) % 2 == 1) {
                ans += s;
            }
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->

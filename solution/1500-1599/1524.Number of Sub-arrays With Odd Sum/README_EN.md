# [1524. Number of Sub-arrays With Odd Sum](https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum)

[中文文档](/solution/1500-1599/1524.Number%20of%20Sub-arrays%20With%20Odd%20Sum/README.md)

## Description

<p>Given an array of integers <code>arr</code>, return <em>the number of subarrays with an <strong>odd</strong> sum</em>.</p>

<p>Since the answer can be very large, return it modulo <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,3,5]
<strong>Output:</strong> 4
<strong>Explanation:</strong> All subarrays are [[1],[1,3],[1,3,5],[3],[3,5],[5]]
All sub-arrays sum are [1,4,9,3,8,5].
Odd sums are [1,9,3,5] so the answer is 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,4,6]
<strong>Output:</strong> 0
<strong>Explanation:</strong> All subarrays are [[2],[2,4],[2,4,6],[4],[4,6],[6]]
All sub-arrays sum are [2,6,12,4,10,6].
All sub-arrays have even sum and the answer is 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,3,4,5,6,7]
<strong>Output:</strong> 16
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numOfSubarrays(self, arr: List[int]) -> int:
        mod = 10**9 + 7
        cnt = [1, 0]
        ans = s = 0
        for x in arr:
            s += x
            ans = (ans + cnt[s & 1 ^ 1]) % mod
            cnt[s & 1] += 1
        return ans
```

### **Java**

```java
class Solution {
    public int numOfSubarrays(int[] arr) {
        final int mod = (int) 1e9 + 7;
        int[] cnt = {1, 0};
        int ans = 0, s = 0;
        for (int x : arr) {
            s += x;
            ans = (ans + cnt[s & 1 ^ 1]) % mod;
            ++cnt[s & 1];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numOfSubarrays(vector<int>& arr) {
        const int mod = 1e9 + 7;
        int cnt[2] = {1, 0};
        int ans = 0, s = 0;
        for (int x : arr) {
            s += x;
            ans = (ans + cnt[s & 1 ^ 1]) % mod;
            ++cnt[s & 1];
        }
        return ans;
    }
};
```

### **Go**

```go
func numOfSubarrays(arr []int) (ans int) {
	const mod int = 1e9 + 7
	cnt := [2]int{1, 0}
	s := 0
	for _, x := range arr {
		s += x
		ans = (ans + cnt[s&1^1]) % mod
		cnt[s&1]++
	}
	return
}
```

### **TypeScript**

```ts
function numOfSubarrays(arr: number[]): number {
    let ans = 0;
    let s = 0;
    const cnt: number[] = [1, 0];
    const mod = 1e9 + 7;
    for (const x of arr) {
        s += x;
        ans = (ans + cnt[(s & 1) ^ 1]) % mod;
        cnt[s & 1]++;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->

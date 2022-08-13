# [1394. Find Lucky Integer in an Array](https://leetcode.com/problems/find-lucky-integer-in-an-array)

[中文文档](/solution/1300-1399/1394.Find%20Lucky%20Integer%20in%20an%20Array/README.md)

## Description

<p>Given an array of integers <code>arr</code>, a <strong>lucky integer</strong> is an integer that has a frequency in the array equal to its value.</p>

<p>Return <em>the largest <strong>lucky integer</strong> in the array</em>. If there is no <strong>lucky integer</strong> return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,2,3,4]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The only lucky number in the array is 2 because frequency[2] == 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,2,3,3,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong> 1, 2 and 3 are all lucky numbers, return the largest of them.
</pre>

<p><strong>Example 3:</strong></p>

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

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findLucky(self, arr: List[int]) -> int:
        counter = Counter(arr)
        ans = -1
        for num, n in counter.items():
            if num == n and ans < num:
                ans = num
        return ans
```

### **Java**

```java
class Solution {
    public int findLucky(int[] arr) {
        Map<Integer, Integer> mp = new HashMap<>();
        for (int num : arr) {
            mp.put(num, mp.getOrDefault(num, 0) + 1);
        }
        int ans = -1;
        for (int num : arr) {
            if (num == mp.get(num) && ans < num) {
                ans = num;
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
    int findLucky(vector<int>& arr) {
        int n = 510;
        vector<int> counter(n);
        for (int e : arr) ++counter[e];
        int ans = -1;
        for (int i = 1; i < n; ++i) {
            if (i == counter[i] && ans < i) ans = i;
        }
        return ans;
    }
};
```

### **Go**

```go
func findLucky(arr []int) int {
    n := 510
    counter := make([]int, n)
    for _, e := range arr {
        counter[e]++
    }
    ans := -1
    for i := 1; i < n; i++ {
        if i == counter[i] && ans < i {
            ans = i
        }
    }
    return ans
}
```

### **...**

```

```

<!-- tabs:end -->

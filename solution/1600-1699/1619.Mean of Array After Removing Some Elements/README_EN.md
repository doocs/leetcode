# [1619. Mean of Array After Removing Some Elements](https://leetcode.com/problems/mean-of-array-after-removing-some-elements)

[中文文档](/solution/1600-1699/1619.Mean%20of%20Array%20After%20Removing%20Some%20Elements/README.md)

## Description

<p>Given an integer array <code>arr</code>, return <em>the mean of the remaining integers after removing the smallest <code>5%</code> and the largest <code>5%</code> of the elements.</em></p>

<p>Answers within <code>10<sup>-5</sup></code> of the <strong>actual answer</strong> will be considered accepted.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3]
<strong>Output:</strong> 2.00000
<strong>Explanation:</strong> After erasing the minimum and the maximum values of this array, all elements are equal to 2, so the mean is 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [6,2,7,5,1,2,0,3,10,2,5,0,5,5,0,8,7,6,8,0]
<strong>Output:</strong> 4.00000
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [6,0,7,0,7,5,7,8,3,4,0,7,8,1,6,8,1,1,2,4,8,1,9,5,4,3,8,5,10,8,6,6,1,0,6,10,8,2,3,4]
<strong>Output:</strong> 4.77778
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> arr = [9,7,8,7,7,8,4,4,6,8,8,7,6,8,8,9,2,6,0,0,1,10,8,6,3,3,5,1,10,9,0,7,10,0,10,4,1,10,6,9,3,6,0,0,2,7,0,6,7,2,9,7,7,3,0,1,6,1,10,3]
<strong>Output:</strong> 5.27778
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> arr = [4,8,4,10,0,7,1,3,7,8,8,3,4,1,6,2,1,1,8,0,9,8,0,3,9,10,3,10,1,10,7,3,2,1,4,9,10,7,6,4,0,8,5,1,2,1,6,2,5,0,7,10,9,10,3,7,10,5,8,5,7,6,7,6,10,9,5,10,5,5,7,2,10,7,7,8,2,0,1,1]
<strong>Output:</strong> 5.29167
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>20 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>arr.length</code><b> </b><strong>is a multiple</strong> of <code>20</code>.</li>
	<li><code><font face="monospace">0 &lt;= arr[i] &lt;= 10<sup>5</sup></font></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def trimMean(self, arr: List[int]) -> float:
        n = len(arr)
        start, end = int(n * 0.05), int(n * 0.95)
        arr.sort()
        t = arr[start: end]
        return round(sum(t) / len(t), 5)
```

### **Java**

```java
class Solution {
    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        double s = 0;
        for (int start = (int) (n * 0.05), i = start; i < n - start; ++i) {
            s += arr[i];
        }
        return s / (n * 0.9);
    }
}
```

### **TypeScript**

```ts
function trimMean(arr: number[]): number {
    arr.sort((a, b) => a - b);
    let n = arr.length,
        rmLen = n * 0.05;
    let sum = 0;
    for (let i = rmLen; i < n - rmLen; i++) {
        sum += arr[i];
    }
    return sum / (n * 0.9);
}
```

### **C++**

```cpp
class Solution {
public:
    double trimMean(vector<int>& arr) {
        sort(arr.begin(), arr.end());
        int n = arr.size();
        double s = 0;
        for (int start = (int) (n * 0.05), i = start; i < n - start; ++i)
            s += arr[i];
        return s / (n * 0.9);
    }
};
```

### **Go**

```go
func trimMean(arr []int) float64 {
	sort.Ints(arr)
	n := len(arr)
	sum := 0.0
	for i := n / 20; i < n-n/20; i++ {
		sum += float64(arr[i])
	}
	return sum / (float64(n) * 0.9)
}
```

### **...**

```

```

<!-- tabs:end -->

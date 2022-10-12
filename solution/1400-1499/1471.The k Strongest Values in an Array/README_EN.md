# [1471. The k Strongest Values in an Array](https://leetcode.com/problems/the-k-strongest-values-in-an-array)

[中文文档](/solution/1400-1499/1471.The%20k%20Strongest%20Values%20in%20an%20Array/README.md)

## Description

<p>Given an array of integers <code>arr</code> and an integer <code>k</code>.</p>

<p>A value <code>arr[i]</code> is said to be stronger than a value <code>arr[j]</code> if <code>|arr[i] - m| &gt; |arr[j] - m|</code> where <code>m</code> is the <strong>median</strong> of the array.<br />
If <code>|arr[i] - m| == |arr[j] - m|</code>, then <code>arr[i]</code> is said to be stronger than <code>arr[j]</code> if <code>arr[i] &gt; arr[j]</code>.</p>

<p>Return <em>a list of the strongest <code>k</code></em> values in the array. return the answer <strong>in any arbitrary order</strong>.</p>

<p><strong>Median</strong> is the middle value in an ordered integer list. More formally, if the length of the list is n, the median is the element in position <code>((n - 1) / 2)</code> in the sorted list <strong>(0-indexed)</strong>.</p>

<ul>
	<li>For <code>arr = [6, -3, 7, 2, 11]</code>, <code>n = 5</code> and the median is obtained by sorting the array <code>arr = [-3, 2, 6, 7, 11]</code> and the median is <code>arr[m]</code> where <code>m = ((5 - 1) / 2) = 2</code>. The median is <code>6</code>.</li>
	<li>For <code>arr = [-7, 22, 17,&thinsp;3]</code>, <code>n = 4</code> and the median is obtained by sorting the array <code>arr = [-7, 3, 17, 22]</code> and the median is <code>arr[m]</code> where <code>m = ((4 - 1) / 2) = 1</code>. The median is <code>3</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,3,4,5], k = 2
<strong>Output:</strong> [5,1]
<strong>Explanation:</strong> Median is 3, the elements of the array sorted by the strongest are [5,1,4,2,3]. The strongest 2 elements are [5, 1]. [1, 5] is also <strong>accepted</strong> answer.
Please note that although |5 - 3| == |1 - 3| but 5 is stronger than 1 because 5 &gt; 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,1,3,5,5], k = 2
<strong>Output:</strong> [5,5]
<strong>Explanation:</strong> Median is 3, the elements of the array sorted by the strongest are [5,5,1,1,3]. The strongest 2 elements are [5, 5].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [6,7,11,7,6,8], k = 5
<strong>Output:</strong> [11,8,6,6,7]
<strong>Explanation:</strong> Median is 7, the elements of the array sorted by the strongest are [11,8,6,6,7,7].
Any permutation of [11,8,6,6,7] is <strong>accepted</strong>.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= arr[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= arr.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getStrongest(self, arr: List[int], k: int) -> List[int]:
        arr.sort()
        m = arr[(len(arr) - 1) >> 1]
        arr.sort(key=lambda x: (-abs(x - m), -x))
        return arr[:k]
```

### **Java**

```java
class Solution {
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int m = arr[(arr.length - 1) >> 1];
        List<Integer> nums = new ArrayList<>();
        for (int v : arr) {
            nums.add(v);
        }
        nums.sort((a, b) -> {
            int x = Math.abs(a - m);
            int y = Math.abs(b - m);
            return x == y ? b - a : y - x;
        });
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = nums.get(i);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> getStrongest(vector<int>& arr, int k) {
        sort(arr.begin(), arr.end());
        int m = arr[(arr.size() - 1) >> 1];
        sort(arr.begin(), arr.end(), [&](int a, int b) {
            int x = abs(a - m), y = abs(b - m);
            return x == y ? a > b : x > y;
        });
        vector<int> ans(arr.begin(), arr.begin() + k);
        return ans;
    }
};
```

### **Go**

```go
func getStrongest(arr []int, k int) []int {
	sort.Ints(arr)
	m := arr[(len(arr)-1)>>1]
	sort.Slice(arr, func(i, j int) bool {
		x, y := abs(arr[i]-m), abs(arr[j]-m)
		if x == y {
			return arr[i] > arr[j]
		}
		return x > y
	})
	return arr[:k]
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **...**

```

```

<!-- tabs:end -->

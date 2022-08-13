# [2111. Minimum Operations to Make the Array K-Increasing](https://leetcode.com/problems/minimum-operations-to-make-the-array-k-increasing)

[中文文档](/solution/2100-2199/2111.Minimum%20Operations%20to%20Make%20the%20Array%20K-Increasing/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array <code>arr</code> consisting of <code>n</code> positive integers, and a positive integer <code>k</code>.</p>

<p>The array <code>arr</code> is called <strong>K-increasing</strong> if <code>arr[i-k] &lt;= arr[i]</code> holds for every index <code>i</code>, where <code>k &lt;= i &lt;= n-1</code>.</p>

<ul>
	<li>For example, <code>arr = [4, 1, 5, 2, 6, 2]</code> is K-increasing for <code>k = 2</code> because:
    <ul>
    	<li><code>arr[0] &lt;= arr[2] (4 &lt;= 5)</code></li>
    	<li><code>arr[1] &lt;= arr[3] (1 &lt;= 2)</code></li>
    	<li><code>arr[2] &lt;= arr[4] (5 &lt;= 6)</code></li>
    	<li><code>arr[3] &lt;= arr[5] (2 &lt;= 2)</code></li>
    </ul>
    </li>
    <li>However, the same <code>arr</code> is not K-increasing for <code>k = 1</code> (because <code>arr[0] &gt; arr[1]</code>) or <code>k = 3</code> (because <code>arr[0] &gt; arr[3]</code>).</li>
</ul>

<p>In one <strong>operation</strong>, you can choose an index <code>i</code> and <strong>change</strong> <code>arr[i]</code> into <strong>any</strong> positive integer.</p>

<p>Return <em>the <strong>minimum number of operations</strong> required to make the array K-increasing for the given </em><code>k</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [5,4,3,2,1], k = 1
<strong>Output:</strong> 4
<strong>Explanation:
</strong>For k = 1, the resultant array has to be non-decreasing.
Some of the K-increasing arrays that can be formed are [5,<u><strong>6</strong></u>,<u><strong>7</strong></u>,<u><strong>8</strong></u>,<u><strong>9</strong></u>], [<u><strong>1</strong></u>,<u><strong>1</strong></u>,<u><strong>1</strong></u>,<u><strong>1</strong></u>,1], [<u><strong>2</strong></u>,<u><strong>2</strong></u>,3,<u><strong>4</strong></u>,<u><strong>4</strong></u>]. All of them require 4 operations.
It is suboptimal to change the array to, for example, [<u><strong>6</strong></u>,<u><strong>7</strong></u>,<u><strong>8</strong></u>,<u><strong>9</strong></u>,<u><strong>10</strong></u>] because it would take 5 operations.
It can be shown that we cannot make the array K-increasing in less than 4 operations.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [4,1,5,2,6,2], k = 2
<strong>Output:</strong> 0
<strong>Explanation:</strong>
This is the same example as the one in the problem description.
Here, for every index i where 2 &lt;= i &lt;= 5, arr[i-2] &lt;=<b> </b>arr[i].
Since the given array is already K-increasing, we do not need to perform any operations.</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [4,1,5,2,6,2], k = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong>
Indices 3 and 5 are the only ones not satisfying arr[i-3] &lt;= arr[i] for 3 &lt;= i &lt;= 5.
One of the ways we can make the array K-increasing is by changing arr[3] to 4 and arr[5] to 5.
The array will now be [4,1,5,<u><strong>4</strong></u>,6,<u><strong>5</strong></u>].
Note that there can be other ways to make the array K-increasing, but none of them require less than 2 operations.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arr[i], k &lt;= arr.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def kIncreasing(self, arr: List[int], k: int) -> int:
        def lis(arr):
            t = []
            for x in arr:
                idx = bisect_right(t, x)
                if idx == len(t):
                    t.append(x)
                else:
                    t[idx] = x
            return len(arr) - len(t)

        return sum(lis(arr[i::k]) for i in range(k))
```

### **Java**

```java
class Solution {
    public int kIncreasing(int[] arr, int k) {
        int n = arr.length;
        int ans = 0;
        for (int i = 0; i < k; ++i) {
            List<Integer> t = new ArrayList<>();
            for (int j = i; j < n; j += k) {
                t.add(arr[j]);
            }
            ans += lis(t);
        }
        return ans;
    }

    private int lis(List<Integer> arr) {
        List<Integer> t = new ArrayList<>();
        for (int x : arr) {
            int idx = searchRight(t, x);
            if (idx == t.size()) {
                t.add(x);
            } else {
                t.set(idx, x);
            }
        }
        return arr.size() - t.size();
    }

    private int searchRight(List<Integer> arr, int x) {
        int left = 0, right = arr.size();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr.get(mid) > x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int kIncreasing(vector<int>& arr, int k) {
        int ans = 0, n = arr.size();
        for (int i = 0; i < k; ++i) {
            vector<int> t;
            for (int j = i; j < n; j += k) t.push_back(arr[j]);
            ans += lis(t);
        }
        return ans;
    }

    int lis(vector<int>& arr) {
        vector<int> t;
        for (int x : arr) {
            auto it = upper_bound(t.begin(), t.end(), x);
            if (it == t.end())
                t.push_back(x);
            else
                *it = x;
        }
        return arr.size() - t.size();
    }
};
```

### **Go**

```go
func kIncreasing(arr []int, k int) int {
	searchRight := func(arr []int, x int) int {
		left, right := 0, len(arr)
		for left < right {
			mid := (left + right) >> 1
			if arr[mid] > x {
				right = mid
			} else {
				left = mid + 1
			}
		}
		return left
	}

	lis := func(arr []int) int {
		var t []int
		for _, x := range arr {
			idx := searchRight(t, x)
			if idx == len(t) {
				t = append(t, x)
			} else {
				t[idx] = x
			}
		}
		return len(arr) - len(t)
	}

	n := len(arr)
	ans := 0
	for i := 0; i < k; i++ {
		var t []int
		for j := i; j < n; j += k {
			t = append(t, arr[j])
		}
		ans += lis(t)
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->

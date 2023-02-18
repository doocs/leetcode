# [1213. Intersection of Three Sorted Arrays](https://leetcode.com/problems/intersection-of-three-sorted-arrays)

[中文文档](/solution/1200-1299/1213.Intersection%20of%20Three%20Sorted%20Arrays/README.md)

## Description

<p>Given three integer arrays <code>arr1</code>, <code>arr2</code> and <code>arr3</code>&nbsp;<strong>sorted</strong> in <strong>strictly increasing</strong> order, return a sorted array of <strong>only</strong>&nbsp;the&nbsp;integers that appeared in <strong>all</strong> three arrays.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
<strong>Output:</strong> [1,5]
<strong>Explanation: </strong>Only 1 and 5 appeared in the three arrays.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr1 = [197,418,523,876,1356], arr2 = [501,880,1593,1710,1870], arr3 = [521,682,1337,1395,1764]
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr1.length, arr2.length, arr3.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr1[i], arr2[i], arr3[i] &lt;= 2000</code></li>
</ul>

## Solutions

Binary search.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def arraysIntersection(
        self, arr1: List[int], arr2: List[int], arr3: List[int]
    ) -> List[int]:
        cnt = Counter(arr1 + arr2 + arr3)
        return [x for x in arr1 if cnt[x] == 3]
```

```python
class Solution:
    def arraysIntersection(self, arr1: List[int], arr2: List[int], arr3: List[int]) -> List[int]:
        ans = []
        for x in arr1:
            i = bisect_left(arr2, x)
            j = bisect_left(arr3, x)
            if i < len(arr2) and j < len(arr3) and arr2[i] == x and arr3[j] == x:
                ans.append(x)
        return ans
```

### **Java**

```java
class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> ans = new ArrayList<>();
        int[] cnt = new int[2001];
        for (int x : arr1) {
            ++cnt[x];
        }
        for (int x : arr2) {
            ++cnt[x];
        }
        for (int x : arr3) {
            if (++cnt[x] == 3) {
                ans.add(x);
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> ans = new ArrayList<>();
        for (int x : arr1) {
            int i = Arrays.binarySearch(arr2, x);
            int j = Arrays.binarySearch(arr3, x);
            if (i >= 0 && j >= 0) {
                ans.add(x);
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
    vector<int> arraysIntersection(vector<int>& arr1, vector<int>& arr2, vector<int>& arr3) {
        vector<int> ans;
        int cnt[2001]{};
        for (int x : arr1) {
            ++cnt[x];
        }
        for (int x : arr2) {
            ++cnt[x];
        }
        for (int x : arr3) {
            if (++cnt[x] == 3) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> arraysIntersection(vector<int>& arr1, vector<int>& arr2, vector<int>& arr3) {
        vector<int> ans;
        for (int x : arr1) {
            auto i = lower_bound(arr2.begin(), arr2.end(), x);
            auto j = lower_bound(arr3.begin(), arr3.end(), x);
            if (*i == x && *j == x) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func arraysIntersection(arr1 []int, arr2 []int, arr3 []int) (ans []int) {
	cnt := [2001]int{}
	for _, x := range arr1 {
		cnt[x]++
	}
	for _, x := range arr2 {
		cnt[x]++
	}
	for _, x := range arr3 {
		cnt[x]++
		if cnt[x] == 3 {
			ans = append(ans, x)
		}
	}
	return
}
```

```go
func arraysIntersection(arr1 []int, arr2 []int, arr3 []int) (ans []int) {
	for _, x := range arr1 {
		i := sort.SearchInts(arr2, x)
		j := sort.SearchInts(arr3, x)
		if i < len(arr2) && j < len(arr3) && arr2[i] == x && arr3[j] == x {
			ans = append(ans, x)
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->

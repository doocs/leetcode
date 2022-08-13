# [1213. Intersection of Three Sorted Arrays](https://leetcode.com/problems/intersection-of-three-sorted-arrays)

[中文文档](/solution/1200-1299/1213.Intersection%20of%20Three%20Sorted%20Arrays/README.md)

## Description

<p>Given three integer arrays <code>arr1</code>, <code>arr2</code> and <code>arr3</code>&nbsp;<strong>sorted</strong> in <strong>strictly increasing</strong> order, return a sorted array of <strong>only</strong>&nbsp;the&nbsp;integers that appeared in <strong>all</strong> three arrays.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
<strong>Output:</strong> [1,5]
<strong>Explanation: </strong>Only 1 and 5 appeared in the three arrays.
</pre>

<p><strong>Example 2:</strong></p>

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
        def find(arr, val):
            left, right = 0, len(arr) - 1
            while left < right:
                mid = (left + right) >> 1
                if arr[mid] >= val:
                    right = mid
                else:
                    left = mid + 1
            return arr[left] == val

        res = []
        for num in arr1:
            if find(arr2, num) and find(arr3, num):
                res.append(num)
        return res
```

### **Java**

```java
class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> res = new ArrayList<>();
        for (int num : arr1) {
            if (find(arr2, num) && find(arr3, num)) {
                res.add(num);
            }
        }
        return res;
    }

    private boolean find(int[] arr, int val) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr[mid] >= val) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return arr[left] == val;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> arraysIntersection(vector<int>& arr1, vector<int>& arr2, vector<int>& arr3) {
        vector<int> res;
        for (int num : arr1) {
            if (find(arr2, num) && find(arr3, num)) {
                res.push_back(num);
            }
        }
        return res;
    }

private:
    bool find(vector<int>& arr, int val) {
        int left = 0, right = arr.size() - 1;
        while (left < right) {
            int mid = left + right >> 1;
            if (arr[mid] >= val) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return arr[left] == val;
    }
};
```

### **Go**

```go
func arraysIntersection(arr1 []int, arr2 []int, arr3 []int) []int {
	var res []int
	for _, num := range arr1 {
		if find(arr2, num) && find(arr3, num) {
			res = append(res, num)
		}
	}
	return res
}

func find(arr []int, val int) bool {
	left, right := 0, len(arr)-1
	for left < right {
		mid := (left + right) >> 1
		if arr[mid] >= val {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return arr[left] == val
}
```

### **...**

```

```

<!-- tabs:end -->

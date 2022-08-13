# [1213. 三个有序数组的交集](https://leetcode.cn/problems/intersection-of-three-sorted-arrays)

[English Version](/solution/1200-1299/1213.Intersection%20of%20Three%20Sorted%20Arrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出三个均为 <strong>严格递增排列 </strong>的整数数组&nbsp;<code>arr1</code>，<code>arr2</code> 和&nbsp;<code>arr3</code>。返回一个由&nbsp;<strong>仅 </strong>在这三个数组中&nbsp;<strong>同时出现&nbsp;</strong>的整数所构成的有序数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong>arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
<strong>输出: </strong>[1,5]
<strong>解释: </strong>只有 1 和 5 同时在这三个数组中出现.
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>arr1 = [197,418,523,876,1356], arr2 = [501,880,1593,1710,1870], arr3 = [521,682,1337,1395,1764]
<strong>输出: </strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr1.length, arr2.length, arr3.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr1[i], arr2[i], arr3[i] &lt;= 2000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

二分查找。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

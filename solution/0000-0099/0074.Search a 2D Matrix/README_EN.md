# [74. Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix)

[中文文档](/solution/0000-0099/0074.Search%20a%202D%20Matrix/README.md)

## Description

<p>Write an efficient algorithm that searches for a value in an <em>m</em> x <em>n</em> matrix. This matrix has the following properties:</p>

<ul>
    <li>Integers in each row are sorted from left to right.</li>
    <li>The first integer of each row is greater than the last integer of the previous row.</li>
</ul>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong>

matrix = [

  [1,   3,  5,  7],

  [10, 11, 16, 20],

  [23, 30, 34, 50]

]

target = 3

<strong>Output:</strong> true

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong>

matrix = [

  [1,   3,  5,  7],

  [10, 11, 16, 20],

  [23, 30, 34, 50]

]

target = 13

<strong>Output:</strong> false</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        m, n = len(matrix), len(matrix[0])
        l, h = 0, m * n - 1
        while l <= h:
            mid = l + ((h - l) >> 1)
            x, y = divmod(mid, n)
            if matrix[x][y] == target:
                return True
            if matrix[x][y] < target:
                l = mid + 1
            else:
                h = mid - 1
        return False
```

### **Java**

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int l = 0, h = m * n - 1;
        while (l <= h) {
            int mid = l + ((h - l) >> 1);
            int x = mid / n, y = mid % n;
            if (matrix[x][y] == target) return true;
            if (matrix[x][y] < target) l = mid + 1;
            else h = mid - 1;
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        int m = matrix.size(), n = matrix[0].size();
        int l = 0, h = m * n - 1;
        while (l <= h) {
            int mid = l + ((h - l) >> 1);
            int x = mid / n, y = mid % n;
            if (matrix[x][y] == target) return true;
            if (matrix[x][y] < target) l = mid + 1;
            else h = mid - 1;
        }
        return false;
    }
};
```

### **...**

```

```

<!-- tabs:end -->

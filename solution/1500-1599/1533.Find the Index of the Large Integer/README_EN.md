# [1533. Find the Index of the Large Integer](https://leetcode.com/problems/find-the-index-of-the-large-integer)

[中文文档](/solution/1500-1599/1533.Find%20the%20Index%20of%20the%20Large%20Integer/README.md)

## Description

<p>We have an integer array <code>arr</code>, where all the integers in <code>arr</code> are equal except for one integer which is <strong>larger</strong> than the rest of the integers. You will not be given direct access to the array, instead, you will have an <strong>API</strong> <code>ArrayReader</code> which have the following functions:</p>

<ul>
	<li><code>int compareSub(int l, int r, int x, int y)</code>: where <code>0 &lt;= l, r, x, y &lt; ArrayReader.length()</code>, <code>l &lt;= r and</code> <code>x &lt;= y</code>. The function compares the sum of sub-array <code>arr[l..r]</code> with the sum of the sub-array <code>arr[x..y]</code> and returns:

    <ul>
    	<li><strong>1</strong> if <code>arr[l]+arr[l+1]+...+arr[r] &gt; arr[x]+arr[x+1]+...+arr[y]</code>.</li>
    	<li><strong>0</strong> if <code>arr[l]+arr[l+1]+...+arr[r] == arr[x]+arr[x+1]+...+arr[y]</code>.</li>
    	<li><strong>-1</strong> if <code>arr[l]+arr[l+1]+...+arr[r] &lt; arr[x]+arr[x+1]+...+arr[y]</code>.</li>
    </ul>
    </li>
    <li><code>int length()</code>: Returns the size of the array.</li>

</ul>

<p>You are allowed to call <code>compareSub()</code> <b>20 times</b> at most. You can assume both functions work in <code>O(1)</code> time.</p>

<p>Return <em>the index of the array <code>arr</code> which has the largest integer</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [7,7,7,7,10,7,7,7]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The following calls to the API
reader.compareSub(0, 0, 1, 1) // returns 0 this is a query comparing the sub-array (0, 0) with the sub array (1, 1), (i.e. compares arr[0] with arr[1]).
Thus we know that arr[0] and arr[1] doesn&#39;t contain the largest element.
reader.compareSub(2, 2, 3, 3) // returns 0, we can exclude arr[2] and arr[3].
reader.compareSub(4, 4, 5, 5) // returns 1, thus for sure arr[4] is the largest element in the array.
Notice that we made only 3 calls, so the answer is valid.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,6,12]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 100</code></li>
	<li>All elements of <code>arr</code> are equal except for one element which is larger than all other elements.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>

<ul>
	<li>What if there are two numbers in <code>arr</code> that are bigger than all other numbers?</li>
	<li>What if there is one number that is bigger than other numbers and one number that is smaller than other numbers?</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# """
# This is ArrayReader's API interface.
# You should not implement it, or speculate about its implementation
# """
# class ArrayReader(object):
# 	 # Compares the sum of arr[l..r] with the sum of arr[x..y]
# 	 # return 1 if sum(arr[l..r]) > sum(arr[x..y])
# 	 # return 0 if sum(arr[l..r]) == sum(arr[x..y])
# 	 # return -1 if sum(arr[l..r]) < sum(arr[x..y])
#    def compareSub(self, l: int, r: int, x: int, y: int) -> int:
#
# 	 # Returns the length of the array
#    def length(self) -> int:
#


class Solution:
    def getIndex(self, reader: 'ArrayReader') -> int:
        left, right = 0, reader.length() - 1
        while left < right:
            t1, t2, t3 = (
                left,
                left + (right - left) // 3,
                left + ((right - left) // 3) * 2 + 1,
            )
            cmp = reader.compareSub(t1, t2, t2 + 1, t3)
            if cmp == 0:
                left = t3 + 1
            elif cmp == 1:
                right = t2
            else:
                left, right = t2 + 1, t3
        return left
```

### **Java**

```java
/**
 * // This is ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *     // Compares the sum of arr[l..r] with the sum of arr[x..y]
 *     // return 1 if sum(arr[l..r]) > sum(arr[x..y])
 *     // return 0 if sum(arr[l..r]) == sum(arr[x..y])
 *     // return -1 if sum(arr[l..r]) < sum(arr[x..y])
 *     public int compareSub(int l, int r, int x, int y) {}
 *
 *     // Returns the length of the array
 *     public int length() {}
 * }
 */

class Solution {
    public int getIndex(ArrayReader reader) {
        int left = 0, right = reader.length() - 1;
        while (left < right) {
            int t1 = left, t2 = left + (right - left) / 3, t3 = left + (right - left) / 3 * 2 + 1;
            int cmp = reader.compareSub(t1, t2, t2 + 1, t3);
            if (cmp == 0) {
                left = t3 + 1;
            } else if (cmp == 1) {
                right = t2;
            } else {
                left = t2 + 1;
                right = t3;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * class ArrayReader {
 *   public:
 *     // Compares the sum of arr[l..r] with the sum of arr[x..y]
 *     // return 1 if sum(arr[l..r]) > sum(arr[x..y])
 *     // return 0 if sum(arr[l..r]) == sum(arr[x..y])
 *     // return -1 if sum(arr[l..r]) < sum(arr[x..y])
 *     int compareSub(int l, int r, int x, int y);
 *
 *     // Returns the length of the array
 *     int length();
 * };
 */

class Solution {
public:
    int getIndex(ArrayReader& reader) {
        int left = 0, right = reader.length() - 1;
        while (left < right) {
            int t1 = left, t2 = left + (right - left) / 3, t3 = left + (right - left) / 3 * 2 + 1;
            int cmp = reader.compareSub(t1, t2, t2 + 1, t3);
            if (cmp == 0) {
                left = t3 + 1;
            } else if (cmp == 1) {
                right = t2;
            } else {
                left = t2 + 1;
                right = t3;
            }
        }
        return left;
    }
};
```

### **Go**

```go
/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * type ArrayReader struct {
 * }
 * // Compares the sum of arr[l..r] with the sum of arr[x..y]
 * // return 1 if sum(arr[l..r]) > sum(arr[x..y])
 * // return 0 if sum(arr[l..r]) == sum(arr[x..y])
 * // return -1 if sum(arr[l..r]) < sum(arr[x..y])
 * func (this *ArrayReader) compareSub(l, r, x, y int) int {}
 *
 * // Returns the length of the array
 * func (this *ArrayReader) length() int {}
 */

func getIndex(reader *ArrayReader) int {
	left, right := 0, reader.length()-1
	for left < right {
		t1, t2, t3 := left, left+(right-left)/3, left+(right-left)/3*2+1
		cmp := reader.compareSub(t1, t2, t2+1, t3)
		if cmp == 0 {
			left = t3 + 1
		} else if cmp == 1 {
			right = t2
		} else {
			left, right = t2+1, t3
		}
	}
	return left
}
```

### **...**

```

```

<!-- tabs:end -->

# [1640. Check Array Formation Through Concatenation](https://leetcode.com/problems/check-array-formation-through-concatenation)

[中文文档](/solution/1600-1699/1640.Check%20Array%20Formation%20Through%20Concatenation/README.md)

## Description

<p>You are given an array of <strong>distinct</strong> integers <code>arr</code> and an array of integer arrays <code>pieces</code>, where the integers in <code>pieces</code> are <strong>distinct</strong>. Your goal is to form <code>arr</code> by concatenating the arrays in <code>pieces</code> <strong>in any order</strong>. However, you are <strong>not</strong> allowed to reorder the integers in each array <code>pieces[i]</code>.</p>

<p>Return <code>true</code> <em>if it is possible </em><em>to form the array </em><code>arr</code><em> from </em><code>pieces</code>. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [15,88], pieces = [[88],[15]]
<strong>Output:</strong> true
<strong>Explanation:</strong> Concatenate [15] then [88]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [49,18,16], pieces = [[16,18,49]]
<strong>Output:</strong> false
<strong>Explanation:</strong> Even though the numbers match, we cannot reorder pieces[0].
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [91,4,64,78], pieces = [[78],[4,64],[91]]
<strong>Output:</strong> true
<strong>Explanation:</strong> Concatenate [91] then [4,64] then [78]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= pieces.length &lt;= arr.length &lt;= 100</code></li>
	<li><code>sum(pieces[i].length) == arr.length</code></li>
	<li><code>1 &lt;= pieces[i].length &lt;= arr.length</code></li>
	<li><code>1 &lt;= arr[i], pieces[i][j] &lt;= 100</code></li>
	<li>The integers in <code>arr</code> are <strong>distinct</strong>.</li>
	<li>The integers in <code>pieces</code> are <strong>distinct</strong> (i.e., If we flatten pieces in a 1D array, all the integers in this array are distinct).</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canFormArray(self, arr: List[int], pieces: List[List[int]]) -> bool:
        mapper = {piece[0]: piece for piece in pieces}
        i, n = 0, len(arr)
        while i < n:
            if arr[i] not in mapper:
                return False
            vals = mapper[arr[i]]
            for val in vals:
                if arr[i] != val:
                    return False
                i += 1
        return True
```

### **Java**

```java
class Solution {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int[] piece : pieces) {
            map.put(piece[0], piece);
        }
        for (int i = 0; i < arr.length;) {
            int[] vals = map.get(arr[i]);
            if (vals == null) {
                return false;
            }
            for (int val : vals) {
                if (arr[i] != val) {
                    return false;
                }
                ++i;
            }
        }
        return true;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} arr
 * @param {number[][]} pieces
 * @return {boolean}
 */
var canFormArray = function (arr, pieces) {
    let mapper = new Map();
    for (let i = 0; i < pieces.length; i++) {
        mapper.set(pieces[i][0], pieces[i]);
    }
    let i = 0,
        n = arr.length;
    while (i < n) {
        let cur = arr[i];
        let nums = mapper.get(cur);
        if (nums == undefined) return false;
        for (let num of nums) {
            if (arr[i] != num) return false;
            i++;
        }
    }
    return true;
};
```

### **...**

```

```

<!-- tabs:end -->

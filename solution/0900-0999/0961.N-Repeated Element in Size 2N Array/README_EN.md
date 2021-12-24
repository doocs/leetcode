# [961. N-Repeated Element in Size 2N Array](https://leetcode.com/problems/n-repeated-element-in-size-2n-array)

[中文文档](/solution/0900-0999/0961.N-Repeated%20Element%20in%20Size%202N%20Array/README.md)

## Description

<p>In a array <code>A</code> of size <code>2N</code>, there are <code>N+1</code> unique elements, and exactly one of these elements is repeated <code>N</code> times.</p>

<p>Return the element repeated <code>N</code> times.</p>

<p>&nbsp;</p>

<ol>
</ol>

<div>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input: </strong><span id="example-input-1-1">[1,2,3,3]</span>
<strong>Output: </strong><span id="example-output-1">3</span>
</pre>

<div>
<p><strong>Example 2:</strong></p>

<pre>
<strong>Input: </strong><span id="example-input-2-1">[2,1,2,5,3,2]</span>
<strong>Output: </strong><span id="example-output-2">2</span>
</pre>

<div>
<p><strong>Example 3:</strong></p>

<pre>
<strong>Input: </strong><span id="example-input-3-1">[5,1,5,2,5,3,5,4]</span>
<strong>Output: </strong><span id="example-output-3">5</span>
</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ul>
	<li><code>4 &lt;= A.length &lt;= 10000</code></li>
	<li><code>0 &lt;= A[i] &lt; 10000</code></li>
	<li><code>A.length</code> is even</li>
</ul>
</div>
</div>
</div>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def repeatedNTimes(self, nums: List[int]) -> int:
        s = set()
        for num in nums:
            if num in s:
                return num
            s.add(num)
```

### **Java**

```java
class Solution {
    public int repeatedNTimes(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int num : nums) {
            if (s.contains(num)) {
                return num;
            }
            s.add(num);
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int repeatedNTimes(vector<int>& nums) {
        unordered_set<int> s;
        for (auto &num : nums) {
            if (s.find(num) != s.end()) {
                return num;
            }
            s.insert(num);
        }
        return -1;
    }
};
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var repeatedNTimes = function (nums) {
    const s = new Set();
    for (const num of nums) {
        if (s.has(num)) {
            return num;
        }
        s.add(num);
    }
    return -1;
};
```

### **...**

```

```

<!-- tabs:end -->

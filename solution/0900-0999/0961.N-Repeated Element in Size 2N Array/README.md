# [961. 重复 N 次的元素](https://leetcode-cn.com/problems/n-repeated-element-in-size-2n-array)

[English Version](/solution/0900-0999/0961.N-Repeated%20Element%20in%20Size%202N%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在大小为 <code>2N</code> 的数组 <code>A</code> 中有 <code>N+1</code> 个不同的元素，其中有一个元素重复了 <code>N</code> 次。</p>

<p>返回重复了 <code>N</code> 次的那个元素。</p>

<p> </p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>[1,2,3,3]
<strong>输出：</strong>3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>[2,1,2,5,3,2]
<strong>输出：</strong>2
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>[5,1,5,2,5,3,5,4]
<strong>输出：</strong>5
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>4 <= A.length <= 10000</code></li>
	<li><code>0 <= A[i] < 10000</code></li>
	<li><code>A.length</code> 为偶数</li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

长度为 `2N`，共 `N+1` 个不同元素，其中一个元素出现 `N` 次，说明其它元素各不相同。

遍历数组，只要出现重复元素，它就是我们要找的重复 `N` 次的元素。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
var repeatedNTimes = function(nums) {
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

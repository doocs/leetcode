# [1860. Incremental Memory Leak](https://leetcode.com/problems/incremental-memory-leak)

[中文文档](/solution/1800-1899/1860.Incremental%20Memory%20Leak/README.md)

## Description

<p>You are given two integers <code>memory1</code> and <code>memory2</code> representing the available memory in bits on two memory sticks. There is currently a faulty program running that consumes an increasing amount of memory every second.</p>

<p>At the <code>i<sup>th</sup></code> second (starting from 1), <code>i</code> bits of memory are allocated to the stick with <strong>more available memory</strong> (or from the first memory stick if both have the same available memory). If neither stick has at least <code>i</code> bits of available memory, the program <strong>crashes</strong>.</p>

<p>Return <em>an array containing </em><code>[crashTime, memory1<sub>crash</sub>, memory2<sub>crash</sub>]</code><em>, where </em><code>crashTime</code><em> is the time (in seconds) when the program crashed and </em><code>memory1<sub>crash</sub></code><em> and </em><code>memory2<sub>crash</sub></code><em> are the available bits of memory in the first and second sticks respectively</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> memory1 = 2, memory2 = 2
<strong>Output:</strong> [3,1,0]
<strong>Explanation:</strong> The memory is allocated as follows:
- At the 1<sup>st</sup> second, 1 bit of memory is allocated to stick 1. The first stick now has 1 bit of available memory.
- At the 2<sup>nd</sup> second, 2 bits of memory are allocated to stick 2. The second stick now has 0 bits of available memory.
- At the 3<sup>rd</sup> second, the program crashes. The sticks have 1 and 0 bits available respectively.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> memory1 = 8, memory2 = 11
<strong>Output:</strong> [6,0,4]
<strong>Explanation:</strong> The memory is allocated as follows:
- At the 1<sup>st</sup> second, 1 bit of memory is allocated to stick 2. The second stick now has 10 bit of available memory.
- At the 2<sup>nd</sup> second, 2 bits of memory are allocated to stick 2. The second stick now has 8 bits of available memory.
- At the 3<sup>rd</sup> second, 3 bits of memory are allocated to stick 1. The first stick now has 5 bits of available memory.
- At the 4<sup>th</sup> second, 4 bits of memory are allocated to stick 2. The second stick now has 4 bits of available memory.
- At the 5<sup>th</sup> second, 5 bits of memory are allocated to stick 1. The first stick now has 0 bits of available memory.
- At the 6<sup>th</sup> second, the program crashes. The sticks have 0 and 4 bits available respectively.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= memory1, memory2 &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def memLeak(self, memory1: int, memory2: int) -> List[int]:
        i = 1
        while memory1 >= i or memory2 >= i:
            if memory1 >= memory2:
                memory1 -= i
            else:
                memory2 -= i
            i += 1
        return [i, memory1, memory2]
```

### **Java**

```java
class Solution {
    public int[] memLeak(int memory1, int memory2) {
        int i = 1;
        while (memory1 >= i || memory2 >= i) {
            if (memory1 >= memory2) {
                memory1 -= i;
            } else {
                memory2 -= i;
            }
            ++i;
        }
        return new int[]{i, memory1, memory2};
    }
}
```

### **JavaScript**

```js
/**
 * @param {number} memory1
 * @param {number} memory2
 * @return {number[]}
 */
var memLeak = function (memory1, memory2) {
    let i = 1;
    while (memory1 >= i || memory2 >= i) {
        if (memory1 >= memory2) {
            memory1 -= i;
        } else {
            memory2 -= i;
        }
        i++;
    }
    return [i, memory1, memory2];
};
```

### **C++**

```cpp
class Solution {
public:
    vector<int> memLeak(int memory1, int memory2) {
        int i = 1;
        while (memory1 >= i || memory2 >= i) {
            if (memory1 >= memory2) {
                memory1 -= i;
            } else {
                memory2 -= i;
            }
            ++i;
        }
        return {i, memory1, memory2};
    }
};
```

### **...**

```

```

<!-- tabs:end -->

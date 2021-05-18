# [08.01. Three Steps Problem](https://leetcode-cn.com/problems/three-steps-problem-lcci)

[中文文档](/lcci/08.01.Three%20Steps%20Problem/README.md)

## Description

<p>A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3 steps at a time. Implement a method to count how many possible ways the child can run up the stairs.&nbsp;The result may be large, so return it modulo 1000000007.</p>

<p><strong>Example1:</strong></p>

<pre>

<strong> Input</strong>: n = 3 

<strong> Output</strong>: 4

</pre>

<p><strong>Example2:</strong></p>

<pre>

<strong> Input</strong>: n = 5

<strong> Output</strong>: 13

</pre>

<p><strong>Note:</strong></p>

1. `1 <= n <= 1000000`

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def waysToStep(self, n: int) -> int:
        if n < 3:
            return n
        a, b, c = 1, 2, 4
        for _ in range(4, n + 1):
            a, b, c = b, c, (a + b + c) % 1000000007
        return c
```

### **Java**

```java
class Solution {
    public int waysToStep(int n) {
        if (n < 3) {
            return n;
        }
        int a = 1, b = 2, c = 4;
        for (int i = 4; i <= n; ++i) {
            int t = a;
            a = b;
            b = c;
            c = ((a + b) % 1000000007 + t) % 1000000007;
        }
        return c;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number}
 */
 var waysToStep = function(n) {
    if (n < 3) return n;
    let a = 1, b = 2, c = 4;
    for (let i = 3; i < n; i++) {
        [a, b, c] = [b, c, (a + b + c) % 1000000007];
    }
    return c;
};
```

### **C++**

```cpp
class Solution {
public:
    int waysToStep(int n) {
        if (n < 3) {
            return n;
        }
        int a = 1, b = 2, c = 4, i = 4;
        while (i++ <= n) {
            int t = ((a + b) % 1000000007 + c) % 1000000007;
            a = b;
            b = c;
            c = t;
        }
        return c;
    }
};
```

<!-- tabs:end -->

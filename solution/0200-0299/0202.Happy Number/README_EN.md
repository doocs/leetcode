# [202. Happy Number](https://leetcode.com/problems/happy-number)

[中文文档](/solution/0200-0299/0202.Happy%20Number/README.md)

## Description

<p>Write an algorithm to determine if a number is &quot;happy&quot;.</p>

<p>A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.</p>

<p><strong>Example:&nbsp;</strong></p>

<pre>

<strong>Input:</strong> 19

<strong>Output:</strong> true

<strong>Explanation: 

</strong>1<sup>2</sup> + 9<sup>2</sup> = 82

8<sup>2</sup> + 2<sup>2</sup> = 68

6<sup>2</sup> + 8<sup>2</sup> = 100

1<sup>2</sup> + 0<sup>2</sup> + 0<sup>2</sup> = 1

</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isHappy(self, n: int) -> bool:
        def get_next(n):
            s = 0
            while n > 0:
                n, digit = divmod(n, 10)
                s += digit ** 2
            return s

        visited = set()
        while n != 1 and n not in visited:
            visited.add(n)
            n = get_next(n)
        return n == 1
```

### **Java**

```java
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> visited = new HashSet<>();
        while (n != 1 && !visited.contains(n)) {
            visited.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    private int getNext(int n) {
        int s = 0;
        while (n > 0) {
            int digit = n % 10;
            s += digit * digit;
            n /= 10;
        }
        return s;
    }
}
```

### **...**

```

```

<!-- tabs:end -->

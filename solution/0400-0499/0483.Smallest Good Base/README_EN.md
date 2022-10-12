# [483. Smallest Good Base](https://leetcode.com/problems/smallest-good-base)

[中文文档](/solution/0400-0499/0483.Smallest%20Good%20Base/README.md)

## Description

<p>Given an integer <code>n</code> represented as a string, return <em>the smallest <strong>good base</strong> of</em> <code>n</code>.</p>

<p>We call <code>k &gt;= 2</code> a <strong>good base</strong> of <code>n</code>, if all digits of <code>n</code> base <code>k</code> are <code>1</code>&#39;s.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = &quot;13&quot;
<strong>Output:</strong> &quot;3&quot;
<strong>Explanation:</strong> 13 base 3 is 111.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = &quot;4681&quot;
<strong>Output:</strong> &quot;8&quot;
<strong>Explanation:</strong> 4681 base 8 is 11111.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = &quot;1000000000000000000&quot;
<strong>Output:</strong> &quot;999999999999999999&quot;
<strong>Explanation:</strong> 1000000000000000000 base 999999999999999999 is 11.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n</code> is an integer in the range <code>[3, 10<sup>18</sup>]</code>.</li>
	<li><code>n</code> does not contain any leading zeros.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def smallestGoodBase(self, n: str) -> str:
        def cal(k, m):
            p = s = 1
            for i in range(m):
                p *= k
                s += p
            return s

        num = int(n)
        for m in range(63, 1, -1):
            l, r = 2, num - 1
            while l < r:
                mid = (l + r) >> 1
                if cal(mid, m) >= num:
                    r = mid
                else:
                    l = mid + 1
            if cal(l, m) == num:
                return str(l)
        return str(num - 1)
```

### **Java**

```java
class Solution {
    public String smallestGoodBase(String n) {
        long num = Long.parseLong(n);
        for (int len = 63; len >= 2; --len) {
            long radix = getRadix(len, num);
            if (radix != -1) {
                return String.valueOf(radix);
            }
        }
        return String.valueOf(num - 1);
    }

    private long getRadix(int len, long num) {
        long l = 2, r = num - 1;
        while (l < r) {
            long mid = l + r >>> 1;
            if (calc(mid, len) >= num)
                r = mid;
            else
                l = mid + 1;
        }
        return calc(r, len) == num ? r : -1;
    }

    private long calc(long radix, int len) {
        long p = 1;
        long sum = 0;
        for (int i = 0; i < len; ++i) {
            if (Long.MAX_VALUE - sum < p) {
                return Long.MAX_VALUE;
            }
            sum += p;
            if (Long.MAX_VALUE / p < radix) {
                p = Long.MAX_VALUE;
            } else {
                p *= radix;
            }
        }
        return sum;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string smallestGoodBase(string n) {
        long v = stol(n);
        int mx = floor(log(v) / log(2));
        for (int m = mx; m > 1; --m) {
            int k = pow(v, 1.0 / m);
            long mul = 1, s = 1;
            for (int i = 0; i < m; ++i) {
                mul *= k;
                s += mul;
            }
            if (s == v) {
                return to_string(k);
            }
        }
        return to_string(v - 1);
    }
};
```

### **...**

```

```

<!-- tabs:end -->

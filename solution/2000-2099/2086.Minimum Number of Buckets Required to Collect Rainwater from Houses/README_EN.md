# [2086. Minimum Number of Buckets Required to Collect Rainwater from Houses](https://leetcode.com/problems/minimum-number-of-buckets-required-to-collect-rainwater-from-houses)

[中文文档](/solution/2000-2099/2086.Minimum%20Number%20of%20Buckets%20Required%20to%20Collect%20Rainwater%20from%20Houses/README.md)

## Description

<p>You are given a <b>0-index</b><strong>ed</strong> string <code>street</code>. Each character in <code>street</code> is either <code>&#39;H&#39;</code> representing a house or <code>&#39;.&#39;</code> representing an empty space.</p>

<p>You can place buckets on the <strong>empty spaces</strong> to collect rainwater that falls from the adjacent houses. The rainwater from a house at index <code>i</code> is collected if a bucket is placed at index <code>i - 1</code> <strong>and/or</strong> index <code>i + 1</code>. A single bucket, if placed adjacent to two houses, can collect the rainwater from <strong>both</strong> houses.</p>

<p>Return <em>the <strong>minimum </strong>number of buckets needed so that for <strong>every</strong> house, there is <strong>at least</strong> one bucket collecting rainwater from it, or </em><code>-1</code><em> if it is impossible.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> street = &quot;H..H&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong>
We can put buckets at index 1 and index 2.
&quot;H..H&quot; -&gt; &quot;HBBH&quot; (&#39;B&#39; denotes where a bucket is placed).
The house at index 0 has a bucket to its right, and the house at index 3 has a bucket to its left.
Thus, for every house, there is at least one bucket collecting rainwater from it.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> street = &quot;.H.H.&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong>
We can put a bucket at index 2.
&quot;.H.H.&quot; -&gt; &quot;.HBH.&quot; (&#39;B&#39; denotes where a bucket is placed).
The house at index 1 has a bucket to its right, and the house at index 3 has a bucket to its left.
Thus, for every house, there is at least one bucket collecting rainwater from it.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> street = &quot;.HHH.&quot;
<strong>Output:</strong> -1
<strong>Explanation:</strong>
There is no empty space to place a bucket to collect the rainwater from the house at index 2.
Thus, it is impossible to collect the rainwater from all the houses.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= street.length &lt;= 10<sup>5</sup></code></li>
	<li><code>street[i]</code> is either<code>&#39;H&#39;</code> or <code>&#39;.&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumBuckets(self, street: str) -> int:
        ans = 0
        i, n = 0, len(street)
        while i < n:
            if street[i] == 'H':
                if i + 1 < n and street[i + 1] == '.':
                    i += 2
                    ans += 1
                elif i and street[i - 1] == '.':
                    ans += 1
                else:
                    return -1
            i += 1
        return ans
```

### **Java**

```java
class Solution {
    public int minimumBuckets(String street) {
        int n = street.length();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (street.charAt(i) == 'H') {
                if (i + 1 < n && street.charAt(i + 1) == '.') {
                    ++ans;
                    i += 2;
                } else if (i > 0 && street.charAt(i - 1) == '.') {
                    ++ans;
                } else {
                    return -1;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumBuckets(string street) {
        int n = street.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (street[i] == 'H') {
                if (i + 1 < n && street[i + 1] == '.') {
                    ++ans;
                    i += 2;
                } else if (i && street[i - 1] == '.') {
                    ++ans;
                } else {
                    return -1;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumBuckets(street string) int {
	ans, n := 0, len(street)
	for i := 0; i < n; i++ {
		if street[i] == 'H' {
			if i+1 < n && street[i+1] == '.' {
				ans++
				i += 2
			} else if i > 0 && street[i-1] == '.' {
				ans++
			} else {
				return -1
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->

# [1702. Maximum Binary String After Change](https://leetcode.com/problems/maximum-binary-string-after-change)

[中文文档](/solution/1700-1799/1702.Maximum%20Binary%20String%20After%20Change/README.md)

## Description

<p>You are given a binary string <code>binary</code> consisting of only <code>0</code>&#39;s or <code>1</code>&#39;s. You can apply each of the following operations any number of times:</p>

<ul>
	<li>Operation 1: If the number contains the substring <code>&quot;00&quot;</code>, you can replace it with <code>&quot;10&quot;</code>.

    <ul>
    	<li>For example, <code>&quot;<u>00</u>010&quot; -&gt; &quot;<u>10</u>010</code>&quot;</li>
    </ul>
    </li>
    <li>Operation 2: If the number contains the substring <code>&quot;10&quot;</code>, you can replace it with <code>&quot;01&quot;</code>.
    <ul>
    	<li>For example, <code>&quot;000<u>10</u>&quot; -&gt; &quot;000<u>01</u>&quot;</code></li>
    </ul>
    </li>

</ul>

<p><em>Return the <strong>maximum binary string</strong> you can obtain after any number of operations. Binary string <code>x</code> is greater than binary string <code>y</code> if <code>x</code>&#39;s decimal representation is greater than <code>y</code>&#39;s decimal representation.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> binary = &quot;000110&quot;
<strong>Output:</strong> &quot;111011&quot;
<strong>Explanation:</strong> A valid transformation sequence can be:
&quot;0001<u>10</u>&quot; -&gt; &quot;0001<u>01</u>&quot; 
&quot;<u>00</u>0101&quot; -&gt; &quot;<u>10</u>0101&quot; 
&quot;1<u>00</u>101&quot; -&gt; &quot;1<u>10</u>101&quot; 
&quot;110<u>10</u>1&quot; -&gt; &quot;110<u>01</u>1&quot; 
&quot;11<u>00</u>11&quot; -&gt; &quot;11<u>10</u>11&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> binary = &quot;01&quot;
<strong>Output:</strong> &quot;01&quot;
<strong>Explanation:</strong>&nbsp;&quot;01&quot; cannot be transformed any further.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= binary.length &lt;= 10<sup>5</sup></code></li>
	<li><code>binary</code> consist of <code>&#39;0&#39;</code> and <code>&#39;1&#39;</code>.</li>
</ul>

## Solutions

### Solution 1: Quick Thinking

We observe that operation 2 can move all $1$s to the end of the string, and operation 1 can change all `0000..000` strings to `111..110`.

Therefore, to get the maximum binary string, we should move all $1$s that are not at the beginning to the end of the string, making the string in the form of `111..11...00..11`, and then use operation 1 to change the middle `000..00` to `111..10`. In this way, we can finally get a binary string that contains at most one $0$, which is the maximum binary string we are looking for.

The time complexity is $O(n)$, where $n$ is the length of the string `binary`. Ignoring the space consumption of the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def maximumBinaryString(self, binary: str) -> str:
        k = binary.find('0')
        if k == -1:
            return binary
        k += binary[k + 1 :].count('0')
        return '1' * k + '0' + '1' * (len(binary) - k - 1)
```

```java
class Solution {
    public String maximumBinaryString(String binary) {
        int k = binary.indexOf('0');
        if (k == -1) {
            return binary;
        }
        int n = binary.length();
        for (int i = k + 1; i < n; ++i) {
            if (binary.charAt(i) == '0') {
                ++k;
            }
        }
        char[] ans = binary.toCharArray();
        Arrays.fill(ans, '1');
        ans[k] = '0';
        return String.valueOf(ans);
    }
}
```

```cpp
class Solution {
public:
    string maximumBinaryString(string binary) {
        int k = binary.find('0');
        if (k == binary.npos) return binary;
        int n = binary.size();
        for (int i = k + 1; i < n; ++i) {
            if (binary[i] == '0') {
                ++k;
            }
        }
        return string(k, '1') + '0' + string(n - k - 1, '1');
    }
};
```

```go
func maximumBinaryString(binary string) string {
	k := strings.IndexByte(binary, '0')
	if k == -1 {
		return binary
	}
	for _, c := range binary[k+1:] {
		if c == '0' {
			k++
		}
	}
	ans := []byte(binary)
	for i := range ans {
		ans[i] = '1'
	}
	ans[k] = '0'
	return string(ans)
}
```

<!-- tabs:end -->

<!-- end -->

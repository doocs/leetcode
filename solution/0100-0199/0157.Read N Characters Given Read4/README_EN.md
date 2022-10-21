# [157. Read N Characters Given Read4](https://leetcode.com/problems/read-n-characters-given-read4)

[中文文档](/solution/0100-0199/0157.Read%20N%20Characters%20Given%20Read4/README.md)

## Description

<p>Given a <code>file</code> and assume that you can only read the file using a given method <code>read4</code>, implement a method to read <code>n</code> characters.</p>

<p><strong>Method read4: </strong></p>

<p>The API <code>read4</code> reads <strong>four consecutive characters</strong> from <code>file</code>, then writes those characters into the buffer array <code>buf4</code>.</p>

<p>The return value is the number of actual characters read.</p>

<p>Note that <code>read4()</code> has its own file pointer, much like <code>FILE *fp</code> in C.</p>

<p><strong>Definition of read4:</strong></p>

<pre>
    Parameter:  char[] buf4
    Returns:    int

buf4[] is a destination, not a source. The results from read4 will be copied to buf4[].
</pre>

<p>Below is a high-level example of how <code>read4</code> works:</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0157.Read%20N%20Characters%20Given%20Read4/images/157_example.png" style="width: 600px; height: 403px;" />
<pre>
File file(&quot;abcde<code>&quot;); // File is &quot;</code>abcde<code>&quot;, initially file pointer (fp) points to &#39;a&#39;
char[] buf4 = new char[4]; // Create buffer with enough space to store characters
read4(buf4); // read4 returns 4. Now buf4 = &quot;abcd&quot;, fp points to &#39;e&#39;
read4(buf4); // read4 returns 1. Now buf4 = &quot;e&quot;, fp points to end of file
read4(buf4); // read4 returns 0. Now buf4 = &quot;&quot;, fp points to end of file</code>
</pre>

<p>&nbsp;</p>

<p><strong>Method read:</strong></p>

<p>By using the <code>read4</code> method, implement the method read that reads <code>n</code> characters from <code>file</code> and store it in the buffer array <code>buf</code>. Consider that you cannot manipulate <code>file</code> directly.</p>

<p>The return value is the number of actual characters read.</p>

<p><strong>Definition of read: </strong></p>

<pre>
    Parameters:	char[] buf, int n
    Returns:	int

buf[] is a destination, not a source. You will need to write the results to buf[].
</pre>

<p><strong>Note:</strong></p>

<ul>
	<li>Consider that you cannot manipulate the file directly. The file is only accessible for <code>read4</code> but not for <code>read</code>.</li>
	<li>The <code>read</code> function will only be called once for each test case.</li>
	<li>You may assume the destination buffer array, <code>buf</code>, is guaranteed to have enough space for storing <code>n</code> characters.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> file = &quot;abc&quot;, n = 4
<strong>Output:</strong> 3
<strong>Explanation:</strong> After calling your read method, buf should contain &quot;abc&quot;. We read a total of 3 characters from the file, so return 3.
Note that &quot;abc&quot; is the file&#39;s content, not buf. buf is the destination buffer that you will have to write the results to.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> file = &quot;abcde&quot;, n = 5
<strong>Output:</strong> 5
<strong>Explanation:</strong> After calling your read method, buf should contain &quot;abcde&quot;. We read a total of 5 characters from the file, so return 5.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> file = &quot;abcdABCD1234&quot;, n = 12
<strong>Output:</strong> 12
<strong>Explanation:</strong> After calling your read method, buf should contain &quot;abcdABCD1234&quot;. We read a total of 12 characters from the file, so return 12.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= file.length &lt;= 500</code></li>
	<li><code>file</code> consist of English letters and digits.</li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
"""
The read4 API is already defined for you.

    @param buf4, a list of characters
    @return an integer
    def read4(buf4):

# Below is an example of how the read4 API can be called.
file = File("abcdefghijk") # File is "abcdefghijk", initially file pointer (fp) points to 'a'
buf4 = [' '] * 4 # Create buffer with enough space to store characters
read4(buf4) # read4 returns 4. Now buf = ['a','b','c','d'], fp points to 'e'
read4(buf4) # read4 returns 4. Now buf = ['e','f','g','h'], fp points to 'i'
read4(buf4) # read4 returns 3. Now buf = ['i','j','k',...], fp points to end of file
"""


class Solution:
    def read(self, buf, n):
        """
        :type buf: Destination buffer (List[str])
        :type n: Number of characters to read (int)
        :rtype: The number of actual characters read (int)
        """
        i = 0
        buf4 = [0] * 4
        v = 5
        while v >= 4:
            v = read4(buf4)
            for j in range(v):
                buf[i] = buf4[j]
                i += 1
                if i >= n:
                    return n
        return i
```

### **Java**

```java
/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf4);
 */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        char[] buf4 = new char[4];
        int i = 0, v = 5;
        while (v >= 4) {
            v = read4(buf4);
            for (int j = 0; j < v; ++j) {
                buf[i++] = buf4[j];
                if (i >= n) {
                    return n;
                }
            }
        }
        return i;
    }
}
```

### **C++**

```cpp
/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char *buf4);
 */

class Solution {
public:
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    int read(char *buf, int n) {
        char buf4[4];
        int i = 0, v = 5;
        while (v >= 4) {
            v = read4(buf4);
            for (int j = 0; j < v; ++j) {
                buf[i++] = buf4[j];
                if (i >= n) {
                    return n;
                }
            }
        }
        return i;
    }
};
```

### **Go**

```go
/**
 * The read4 API is already defined for you.
 *
 *     read4 := func(buf4 []byte) int
 *
 * // Below is an example of how the read4 API can be called.
 * file := File("abcdefghijk") // File is "abcdefghijk", initially file pointer (fp) points to 'a'
 * buf4 := make([]byte, 4) // Create buffer with enough space to store characters
 * read4(buf4) // read4 returns 4. Now buf = ['a','b','c','d'], fp points to 'e'
 * read4(buf4) // read4 returns 4. Now buf = ['e','f','g','h'], fp points to 'i'
 * read4(buf4) // read4 returns 3. Now buf = ['i','j','k',...], fp points to end of file
 */

var solution = func(read4 func([]byte) int) func([]byte, int) int {
	// implement read below.
	return func(buf []byte, n int) int {
		buf4 := make([]byte, 4)
		i, v := 0, 5
		for v >= 4 {
			v = read4(buf4)
			for j := 0; j < v; j++ {
				buf[i] = buf4[j]
				i++
				if i >= n {
					return n
				}
			}
		}
		return i
	}
}
```

### **...**

```

```

<!-- tabs:end -->

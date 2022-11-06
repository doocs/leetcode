# [158. Read N Characters Given read4 II - Call Multiple Times](https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times)

[中文文档](/solution/0100-0199/0158.Read%20N%20Characters%20Given%20read4%20II%20-%20Call%20Multiple%20Times/README.md)

## Description

<p>Given a <code>file</code> and assume that you can only read the file using a given method <code>read4</code>, implement a method <code>read</code> to read <code>n</code> characters. Your method <code>read</code> may be <strong>called multiple times</strong>.</p>

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
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0158.Read%20N%20Characters%20Given%20read4%20II%20-%20Call%20Multiple%20Times/images/157_example.png" style="width: 600px; height: 403px;" />
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
	<li>The read function may be <strong>called multiple times</strong>.</li>
	<li>Please remember to <strong>RESET</strong> your class variables declared in Solution, as static/class variables are persisted across multiple test cases. Please see <a href="https://leetcode.com/faq/" target="_blank">here</a> for more details.</li>
	<li>You may assume the destination buffer array, <code>buf</code>, is guaranteed to have enough space for storing <code>n</code> characters.</li>
	<li>It is guaranteed that in a given test case the same buffer <code>buf</code> is called by <code>read</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> file = &quot;abc&quot;, queries = [1,2,1]
<strong>Output:</strong> [1,2,0]
<strong>Explanation:</strong> The test case represents the following scenario:
File file(&quot;abc&quot;);
Solution sol;
sol.read(buf, 1); // After calling your read method, buf should contain &quot;a&quot;. We read a total of 1 character from the file, so return 1.
sol.read(buf, 2); // Now buf should contain &quot;bc&quot;. We read a total of 2 characters from the file, so return 2.
sol.read(buf, 1); // We have reached the end of file, no more characters can be read. So return 0.
Assume buf is allocated and guaranteed to have enough space for storing all characters from the file.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> file = &quot;abc&quot;, queries = [4,1]
<strong>Output:</strong> [3,0]
<strong>Explanation:</strong> The test case represents the following scenario:
File file(&quot;abc&quot;);
Solution sol;
sol.read(buf, 4); // After calling your read method, buf should contain &quot;abc&quot;. We read a total of 3 characters from the file, so return 3.
sol.read(buf, 1); // We have reached the end of file, no more characters can be read. So return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= file.length &lt;= 500</code></li>
	<li><code>file</code> consist of English letters and digits.</li>
	<li><code>1 &lt;= queries.length &lt;= 10</code></li>
	<li><code>1 &lt;= queries[i] &lt;= 500</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# The read4 API is already defined for you.
# def read4(buf4: List[str]) -> int:

class Solution:
    def __init__(self):
        self.buf4 = [None] * 4
        self.i = self.size = 0

    def read(self, buf: List[str], n: int) -> int:
        j = 0
        while j < n:
            if self.i == self.size:
                self.size = read4(self.buf4)
                self.i = 0
                if self.size == 0:
                    break
            while j < n and self.i < self.size:
                buf[j] = self.buf4[self.i]
                self.i += 1
                j += 1
        return j
```

### **Java**

```java
/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf4);
 */

public class Solution extends Reader4 {
    private char[] buf4 = new char[4];
    private int i;
    private int size;

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        int j = 0;
        while (j < n) {
            if (i == size) {
                size = read4(buf4);
                i = 0;
                if (size == 0) {
                    break;
                }
            }
            while (j < n && i < size) {
                buf[j++] = buf4[i++];
            }
        }
        return j;
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
        int j = 0;
        while (j < n) {
            if (i == size) {
                size = read4(buf4);
                i = 0;
                if (size == 0) break;
            }
            while (j < n && i < size) buf[j++] = buf4[i++];
        }
        return j;
    }

private:
    char *buf4 = new char[4];
    int i = 0;
    int size = 0;
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
	buf4 := make([]byte, 4)
	i, size := 0, 0
	// implement read below.
	return func(buf []byte, n int) int {
		j := 0
		for j < n {
			if i == size {
				size = read4(buf4)
				i = 0
				if size == 0 {
					break
				}
			}
			for j < n && i < size {
				buf[j] = buf4[i]
				i, j = i+1, j+1
			}
		}
		return j
	}
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->

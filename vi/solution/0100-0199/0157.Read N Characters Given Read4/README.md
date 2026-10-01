---
comments: true
difficulty: Easy
tags:
    - Array
    - Interactive
    - Simulation
---

<!-- problem:start -->

# [157. Read N Characters Given Read4 🔒](https://leetcode.com/problems/read-n-characters-given-read4)

[中文文档](/solution/0100-0199/0157.Read%20N%20Characters%20Given%20Read4/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một <code>file</code> và giả sử rằng bạn chỉ có thể đọc file bằng một phương thức <code>read4</code>, hãy triển khai một phương thức để đọc <code>n</code> ký tự.</p>

<p><strong>Phương thức read4: </strong></p>

<p>API <code>read4</code> đọc <strong>bốn ký tự liên tiếp</strong> từ <code>file</code>, sau đó ghi các ký tự đó vào mảng buffer <code>buf4</code>.</p>

<p>Giá trị trả về là số ký tự thực tế đã đọc.</p>

<p>Lưu ý rằng <code>read4()</code> có con trỏ file riêng, tương tự như <code>FILE *fp</code> trong C.</p>

<p><strong>Định nghĩa của read4:</strong></p>

<pre>
    Tham số:  char[] buf4
    Trả về:    int

buf4[] là đích đến, không phải nguồn. Kết quả từ read4 sẽ được sao chép vào buf4[].
</pre>

<p>Dưới đây là ví dụ ở mức khái quát về cách <code>read4</code> hoạt động:</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0157.Read%20N%20Characters%20Given%20Read4/images/157_example.png" style="width: 600px; height: 403px;" />
<pre>
File file(&quot;abcde<code>&quot;); // File is &quot;</code>abcde<code>&quot;, initially file pointer (fp) points to &#39;a&#39;
char[] buf4 = new char[4]; // Create buffer with enough space to store characters
read4(buf4); // read4 returns 4. Now buf4 = &quot;abcd&quot;, fp points to &#39;e&#39;
read4(buf4); // read4 returns 1. Now buf4 = &quot;e&quot;, fp points to end of file
read4(buf4); // read4 returns 0. Now buf4 = &quot;&quot;, fp points to end of file</code>
</pre>

<p>&nbsp;</p>

<p><strong>Phương thức read:</strong></p>

<p>Bằng cách sử dụng phương thức <code>read4</code>, hãy triển khai phương thức read để đọc <code>n</code> ký tự từ <code>file</code> và lưu chúng vào mảng buffer <code>buf</code>. Hãy lưu ý rằng bạn không thể thao tác trực tiếp với <code>file</code>.</p>

<p>Giá trị trả về là số ký tự thực tế đã đọc.</p>

<p><strong>Định nghĩa của read: </strong></p>

<pre>
    Tham số:    char[] buf, int n
    Trả về:     int

buf[] là đích đến, không phải nguồn. Bạn sẽ cần ghi kết quả vào buf[].
</pre>

<p><strong>Lưu ý:</strong></p>

<ul>
	<li>Hãy lưu ý rằng bạn không thể thao tác trực tiếp với file. file chỉ có thể được truy cập bằng <code>read4</code> nhưng không thể được truy cập bằng <code>read</code>.</li>
	<li>Hàm <code>read</code> sẽ chỉ được gọi một lần cho mỗi test case.</li>
	<li>Bạn có thể giả sử rằng mảng buffer đích <code>buf</code> được đảm bảo có đủ chỗ để lưu <code>n</code> ký tự.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> file = &quot;abc&quot;, n = 4
<strong>Đầu ra:</strong> 3
<strong>Giải thích:</strong> Sau khi gọi phương thức read, buf sẽ chứa &quot;abc&quot;. Chúng ta đã đọc tổng cộng 3 ký tự từ file, vì vậy trả về 3.
Lưu ý rằng &quot;abc&quot; là nội dung của file, không phải buf. buf là mảng buffer đích mà bạn phải ghi vào.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> file = &quot;abcde&quot;, n = 5
<strong>Đầu ra:</strong> 5
<strong>Giải thích:</strong> Sau khi gọi phương thức read, buf sẽ chứa &quot;abcde&quot;. Chúng ta đã đọc tổng cộng 5 ký tự từ file, vì vậy trả về 5.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> file = &quot;abcdABCD1234&quot;, n = 12
<strong>Đầu ra:</strong> 12
<strong>Giải thích:</strong> Sau khi gọi phương thức read, buf sẽ chứa &quot;abcdABCD1234&quot;. Chúng ta đã đọc tổng cộng 12 ký tự từ file, vì vậy trả về 12.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= file.length &lt;= 500</code></li>
	<li><code>file</code> chỉ gồm các chữ cái tiếng Anh và chữ số.</li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Chỉ có thể truy cập file thông qua $\textit{read4}$, và $\textit{read}$ chỉ được gọi một lần. $n$ không nhất thiết là bội số của $4$, và lần gọi cuối có thể trả về ít hơn. Hãy lặp việc gọi $\textit{read4}$ vào một buffer tạm, sao chép vào buffer đích, rồi dừng khi đạt $n$ hoặc EOF.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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
    int read(char* buf, int n) {
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

#### Go

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

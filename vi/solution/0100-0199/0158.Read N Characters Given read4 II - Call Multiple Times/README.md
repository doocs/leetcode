---
comments: true
difficulty: Hard
tags:
    - Array
    - Interactive
    - Simulation
---

<!-- problem:start -->

# [158. Read N Characters Given read4 II - Call Multiple Times 🔒](https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times)

[中文文档](/solution/0100-0199/0158.Read%20N%20Characters%20Given%20read4%20II%20-%20Call%20Multiple%20Times/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một <code>file</code> và giả sử rằng bạn chỉ có thể đọc file bằng phương thức <code>read4</code> đã cho, hãy triển khai một phương thức <code>read</code> để đọc <code>n</code> ký tự. Phương thức <code>read</code> của bạn có thể được <strong>gọi nhiều lần</strong>.</p>

<p><strong>Phương thức read4: </strong></p>

<p>API <code>read4</code> đọc <strong>bốn ký tự liên tiếp</strong> từ <code>file</code>, sau đó ghi các ký tự đó vào mảng bộ đệm <code>buf4</code>.</p>

<p>Giá trị trả về là số ký tự thực tế đã đọc.</p>

<p>Lưu ý rằng <code>read4()</code> có con trỏ file riêng, tương tự như <code>FILE *fp</code> trong C.</p>

<p><strong>Định nghĩa của read4:</strong></p>

<pre>
    Tham số:  char[] buf4
    Trả về:    int

buf4[] là đích, không phải nguồn. Kết quả từ read4 sẽ được sao chép vào buf4[].
</pre>

<p>Dưới đây là ví dụ cấp cao về cách <code>read4</code> hoạt động:</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0158.Read%20N%20Characters%20Given%20read4%20II%20-%20Call%20Multiple%20Times/images/157_example.png" style="width: 600px; height: 403px;" />
<pre>
File file(&quot;abcde<code>&quot;); // File is &quot;</code>abcde<code>&quot;, initially file pointer (fp) points to &#39;a&#39;
char[] buf4 = new char[4]; // Create buffer with enough space to store characters
read4(buf4); // read4 returns 4. Now buf4 = &quot;abcd&quot;, fp points to &#39;e&#39;
read4(buf4); // read4 returns 1. Now buf4 = &quot;e&quot;, fp points to end of file
read4(buf4); // read4 returns 0. Now buf4 = &quot;&quot;, fp points to end of file</code>
</pre>

<p>&nbsp;</p>

<p><strong>Phương thức read:</strong></p>

<p>Sử dụng phương thức <code>read4</code>, hãy triển khai phương thức read để đọc <code>n</code> ký tự từ <code>file</code> và lưu chúng vào mảng bộ đệm <code>buf</code>. Hãy lưu ý rằng bạn không thể thao tác trực tiếp với <code>file</code>.</p>

<p>Giá trị trả về là số ký tự thực tế đã đọc.</p>

<p><strong>Định nghĩa của read: </strong></p>

<pre>
    Tham số:&#9;char[] buf, int n
    Trả về:&#9;int

buf[] là đích, không phải nguồn. Bạn cần ghi kết quả vào buf[].
</pre>

<p><strong>Lưu ý:</strong></p>

<ul>
	<li>Hãy lưu ý rằng bạn không thể thao tác trực tiếp với file. file chỉ có thể được truy cập bởi <code>read4</code>, không phải bởi <code>read</code>.</li>
	<li>Hàm read có thể được <strong>gọi nhiều lần</strong>.</li>
	<li>Hãy nhớ <strong>RESET</strong> các biến lớp được khai báo trong Solution, vì các biến static/class được duy trì qua nhiều test case. Xem <a href="https://leetcode.com/faq/" target="_blank">tại đây</a> để biết thêm chi tiết.</li>
	<li>Bạn có thể giả sử rằng mảng bộ đệm đích, <code>buf</code>, được đảm bảo có đủ chỗ để lưu <code>n</code> ký tự.</li>
	<li>Đảm bảo rằng trong một test case nhất định, cùng một bộ đệm <code>buf</code> được truyền vào <code>read</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> file = &quot;abc&quot;, queries = [1,2,1]
<strong>Đầu ra:</strong> [1,2,0]
<strong>Giải thích:</strong> Test case mô tả kịch bản sau:
File file(&quot;abc&quot;);
Solution sol;
sol.read(buf, 1); // Sau khi gọi phương thức read, buf phải chứa &quot;a&quot;. Ta đã đọc tổng cộng 1 ký tự từ file, nên trả về 1.
sol.read(buf, 2); // Khi đó buf phải chứa &quot;bc&quot;. Ta đã đọc tổng cộng 2 ký tự từ file, nên trả về 2.
sol.read(buf, 1); // Ta đã tới cuối file, không thể đọc thêm ký tự nào. Vì vậy, trả về 0.
Giả sử buf đã được cấp phát và được đảm bảo có đủ chỗ để lưu tất cả ký tự trong file.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> file = &quot;abc&quot;, queries = [4,1]
<strong>Đầu ra:</strong> [3,0]
<strong>Giải thích:</strong> Test case mô tả kịch bản sau:
File file(&quot;abc&quot;);
Solution sol;
sol.read(buf, 4); // Sau khi gọi phương thức read, buf phải chứa &quot;abc&quot;. Ta đã đọc tổng cộng 3 ký tự từ file, nên trả về 3.
sol.read(buf, 1); // Ta đã tới cuối file, không thể đọc thêm ký tự nào. Vì vậy, trả về 0.
</pre>

<p>&nbsp;</p>

<p><strong>Ràng buộc:</strong></p>
<ul>
	<li><code>1 &lt;= file.length &lt;= 500</code></li>
	<li><code>file</code> chỉ gồm các chữ cái tiếng Anh và chữ số.</li>
	<li><code>1 &lt;= queries.length &lt;= 10</code></li>
	<li><code>1 &lt;= queries[i] &lt;= 500</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Khác với bài toán trước, $\textit{read}$ được gọi nhiều lần, vì vậy phải giữ lại các ký tự còn thừa từ lần gọi $\textit{read4}$ cuối cùng. Lưu bộ đệm 4 phần tử cùng với con trỏ và kích thước của nó trong instance: chỉ nạp lại khi bộ đệm rỗng, nếu không thì lấy các ký tự còn thừa ra. Con trỏ file được tăng một lần xuyên suốt các lần gọi.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
# API read4 đã được định nghĩa sẵn cho bạn.
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

#### Java

```java
/**
 * API read4 được định nghĩa trong lớp cha Reader4.
 *     int read4(char[] buf4);
 */

public class Solution extends Reader4 {
    private char[] buf4 = new char[4];
    private int i;
    private int size;

    /**
     * @param buf Bộ đệm đích
     * @param n   Số ký tự cần đọc
     * @return    Số ký tự thực tế đã đọc
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

#### C++

```cpp
/**
 * API read4 được định nghĩa trong lớp cha Reader4.
 *     int read4(char *buf4);
 */

class Solution {
public:
    /**
     * @param buf Bộ đệm đích
     * @param n   Số ký tự cần đọc
     * @return    Số ký tự thực tế đã đọc
     */
    int read(char* buf, int n) {
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
    char* buf4 = new char[4];
    int i = 0;
    int size = 0;
};
```

#### Go

```go
/**
 * API read4 đã được định nghĩa sẵn cho bạn.
 *
 *     read4 := func(buf4 []byte) int
 *
 * // Dưới đây là ví dụ về cách gọi API read4.
 * file := File("abcdefghijk") // File là "abcdefghijk", ban đầu con trỏ file (fp) trỏ tới 'a'
 * buf4 := make([]byte, 4) // Tạo bộ đệm có đủ chỗ để lưu các ký tự
 * read4(buf4) // read4 trả về 4. Khi đó buf = ['a','b','c','d'], fp trỏ tới 'e'
 * read4(buf4) // read4 trả về 4. Khi đó buf = ['e','f','g','h'], fp trỏ tới 'i'
 * read4(buf4) // read4 trả về 3. Khi đó buf = ['i','j','k',...], fp trỏ tới cuối file
 */

var solution = func(read4 func([]byte) int) func([]byte, int) int {
	buf4 := make([]byte, 4)
	i, size := 0, 0
	// triển khai read bên dưới.
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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

---
comments: true
difficulty: Medium
tags:
    - Stack
    - String
---

<!-- problem:start -->

# [71. Simplify Path](https://leetcode.com/problems/simplify-path)

[中文文档](/solution/0000-0099/0071.Simplify%20Path/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cung cấp một <em>đường dẫn tuyệt đối</em> cho hệ thống tệp kiểu Unix, luôn bắt đầu bằng dấu gạch chéo <code>&#39;/&#39;</code>. Nhiệm vụ của bạn là chuyển đổi đường dẫn tuyệt đối này thành <strong>đường dẫn chuẩn tắc đơn giản hóa</strong>.</p>

<p>Các <em>quy tắc</em> của hệ thống tệp kiểu Unix như sau:</p>

<ul>
	<li>Một dấu chấm đơn <code>&#39;.&#39;</code> biểu thị thư mục hiện tại.</li>
	<li>Hai dấu chấm <code>&#39;..&#39;</code> biểu thị thư mục trước đó/thư mục cha.</li>
	<li>Nhiều dấu gạch chéo liên tiếp, chẳng hạn như <code>&#39;//&#39;</code> và <code>&#39;///&#39;</code>, được coi là một dấu gạch chéo duy nhất <code>&#39;/&#39;</code>.</li>
	<li>Bất kỳ chuỗi dấu chấm nào <strong>không khớp</strong> với các quy tắc trên đều được coi là <strong>tên thư mục hoặc</strong> <strong>tên </strong><strong>tệp hợp lệ</strong>. Ví dụ, <code>&#39;...&#39; </code>và <code>&#39;....&#39;</code> là các tên thư mục hoặc tệp hợp lệ.</li>
</ul>

<p>Đường dẫn chuẩn tắc đơn giản hóa phải tuân theo các <em>quy tắc</em> sau:</p>

<ul>
	<li>Đường dẫn phải bắt đầu bằng một dấu gạch chéo duy nhất <code>&#39;/&#39;</code>.</li>
	<li>Các thư mục trong đường dẫn phải được phân tách bằng đúng một dấu gạch chéo <code>&#39;/&#39;</code>.</li>
	<li>Đường dẫn không được kết thúc bằng dấu gạch chéo <code>&#39;/&#39;</code>, trừ khi đó là thư mục gốc.</li>
	<li>Đường dẫn không được có một hoặc hai dấu chấm (<code>&#39;.&#39;</code> và <code>&#39;..&#39;</code>) được dùng để biểu thị thư mục hiện tại hoặc thư mục cha.</li>
</ul>

<p>Trả về <strong>đường dẫn chuẩn tắc đơn giản hóa</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">path = &quot;/home/&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">&quot;/home&quot;</span></p>

<p><strong>Giải thích:</strong></p>

<p>Dấu gạch chéo ở cuối phải được loại bỏ.</p>
</div>

<p><strong class="example">Ví dụ 2:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">path = &quot;/home//foo/&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">&quot;/home/foo&quot;</span></p>

<p><strong>Giải thích:</strong></p>

<p>Nhiều dấu gạch chéo liên tiếp được thay thế bằng một dấu duy nhất.</p>
</div>

<p><strong class="example">Ví dụ 3:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">path = &quot;/home/user/Documents/../Pictures&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">&quot;/home/user/Pictures&quot;</span></p>

<p><strong>Giải thích:</strong></p>

<p>Hai dấu chấm <code>&quot;..&quot;</code> tham chiếu đến thư mục ở cấp cao hơn (thư mục cha).</p>
</div>

<p><strong class="example">Ví dụ 4:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">path = &quot;/../&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">&quot;/&quot;</span></p>

<p><strong>Giải thích:</strong></p>

<p>Không thể đi lên một cấp từ thư mục gốc.</p>
</div>

<p><strong class="example">Ví dụ 5:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">path = &quot;/.../a/../b/c/../d/./&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">&quot;/.../b/d&quot;</span></p>

<p><strong>Giải thích:</strong></p>

<p><code>&quot;...&quot;</code> là một tên thư mục hợp lệ trong bài toán này.</p>
</div>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= path.length &lt;= 3000</code></li>
	<li><code>path</code> chỉ bao gồm các chữ cái tiếng Anh, chữ số, dấu chấm <code>&#39;.&#39;</code>, dấu gạch chéo <code>&#39;/&#39;</code> hoặc <code>&#39;_&#39;</code>.</li>
	<li><code>path</code> là một đường dẫn Unix tuyệt đối hợp lệ.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Ngăn xếp

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là thay thế chuỗi lặp lại của `'//'`, `'/./'` và `'/../'`. Với $n \le 3000$, cách này có thể chạy qua, nhưng các dấu gạch chéo liên tiếp và `'..'` tương tác với nhau, nên rất khó xác định đúng thứ tự thay thế.
>
> Điều chúng ta cần là thao tác hoàn tác: một tên hợp lệ phải có thể bị `'..'` xóa đi, còn các phần rỗng và `'.'` thì không làm chúng ta di chuyển. Đây là quy tắc vào sau, ra trước, nên một ngăn xếp có thể lưu các thư mục từ thư mục gốc đến vị trí hiện tại. Hãy tách theo `'/'`, xử lý từng phần, rồi nối lại thành đường dẫn chuẩn tắc. Chúng ta không được lấy phần tử ra vượt quá thư mục gốc, vì vậy cần kiểm tra ngăn xếp trước khi lấy phần tử.

<!-- thinking:end -->

Trước hết, chúng ta tách đường dẫn thành một số chuỗi con bằng cách tách theo `'/'`. Sau đó, chúng ta duyệt qua từng chuỗi con và thực hiện các thao tác sau dựa trên nội dung của chuỗi con:

- Nếu chuỗi con rỗng hoặc là `'.'`, không thực hiện thao tác nào vì `'.'` biểu thị thư mục hiện tại.
- Nếu chuỗi con là `'..'`, phần tử trên cùng của ngăn xếp được lấy ra vì `'..'` biểu thị thư mục cha.
- Nếu chuỗi con là một chuỗi khác, chuỗi con được đưa vào ngăn xếp vì nó biểu thị thư mục con của thư mục hiện tại.

Cuối cùng, chúng ta nối tất cả phần tử trong ngăn xếp từ đáy lên đỉnh để tạo thành một chuỗi, chính là đường dẫn chuẩn tắc đơn giản hóa.

Độ phức tạp thời gian là $O(n)$ và độ phức tạp không gian là $O(n)$, trong đó $n$ là độ dài của đường dẫn.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def simplifyPath(self, path: str) -> str:
        stk = []
        for s in path.split('/'):
            if not s or s == '.':
                continue
            if s == '..':
                if stk:
                    stk.pop()
            else:
                stk.append(s)
        return '/' + '/'.join(stk)
```

#### Java

```java
class Solution {
    public String simplifyPath(String path) {
        Deque<String> stk = new ArrayDeque<>();
        for (String s : path.split("/")) {
            if ("".equals(s) || ".".equals(s)) {
                continue;
            }
            if ("..".equals(s)) {
                stk.pollLast();
            } else {
                stk.offerLast(s);
            }
        }
        return "/" + String.join("/", stk);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string simplifyPath(string path) {
        deque<string> stk;
        stringstream ss(path);
        string t;
        while (getline(ss, t, '/')) {
            if (t == "" || t == ".") {
                continue;
            }
            if (t == "..") {
                if (!stk.empty()) {
                    stk.pop_back();
                }
            } else {
                stk.push_back(t);
            }
        }
        if (stk.empty()) {
            return "/";
        }
        string ans;
        for (auto& s : stk) {
            ans += "/" + s;
        }
        return ans;
    }
};
```

#### Go

```go
func simplifyPath(path string) string {
	var stk []string
	for _, s := range strings.Split(path, "/") {
		if s == "" || s == "." {
			continue
		}
		if s == ".." {
			if len(stk) > 0 {
				stk = stk[0 : len(stk)-1]
			}
		} else {
			stk = append(stk, s)
		}
	}
	return "/" + strings.Join(stk, "/")
}
```

#### TypeScript

```ts
function simplifyPath(path: string): string {
    const stk: string[] = [];
    for (const s of path.split('/')) {
        if (s === '' || s === '.') {
            continue;
        }
        if (s === '..') {
            if (stk.length) {
                stk.pop();
            }
        } else {
            stk.push(s);
        }
    }
    return '/' + stk.join('/');
}
```

#### Rust

```rust
impl Solution {
    pub fn simplify_path(path: String) -> String {
        let mut stk = Vec::new();
        for s in path.split('/') {
            match s {
                "" | "." => continue,
                ".." => {
                    stk.pop();
                }
                _ => stk.push(s),
            }
        }
        "/".to_string() + &stk.join("/")
    }
}
```

#### C#

```cs
public class Solution {
    public string SimplifyPath(string path) {
        var stk = new Stack<string>();
        foreach (var s in path.Split('/')) {
            if (s == "" || s == ".") {
                continue;
            }
            if (s == "..") {
                if (stk.Count > 0) {
                    stk.Pop();
                }
            } else {
                stk.Push(s);
            }
        }
        var sb = new StringBuilder();
        while (stk.Count > 0) {
            sb.Insert(0, "/" + stk.Pop());
        }
        return sb.Length == 0 ? "/" : sb.ToString();
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

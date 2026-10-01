---
comments: true
difficulty: Medium
tags:
    - String
    - Backtracking
---

<!-- problem:start -->

# [93. Restore IP Addresses](https://leetcode.com/problems/restore-ip-addresses)

[中文文档](/solution/0000-0099/0093.Restore%20IP%20Addresses/README.md)

## Mô tả

<!-- description:start -->

<p>Một <strong>địa chỉ IP hợp lệ</strong> gồm chính xác bốn số nguyên được phân tách bằng các dấu chấm đơn. Mỗi số nguyên nằm trong khoảng từ <code>0</code> đến <code>255</code> (<strong>bao gồm cả hai đầu mút</strong>) và không được có các số 0 ở đầu.</p>

<ul>
	<li>Ví dụ, <code>&quot;0.1.2.201&quot;</code> và <code>&quot;192.168.1.1&quot;</code> là các địa chỉ IP <strong>hợp lệ</strong>, nhưng <code>&quot;0.011.255.245&quot;</code>, <code>&quot;192.168.1.312&quot;</code> và <code>&quot;192.168@1.1&quot;</code> là các địa chỉ IP <strong>không hợp lệ</strong>.</li>
</ul>

<p>Cho một chuỗi <code>s</code> chỉ chứa các chữ số, hãy trả về <em>tất cả các địa chỉ IP hợp lệ có thể tạo thành bằng cách chèn các dấu chấm vào </em><code>s</code>. Bạn <strong>không được phép</strong> sắp xếp lại hoặc xoá bất kỳ chữ số nào trong <code>s</code>. Bạn có thể trả về các địa chỉ IP hợp lệ theo <strong>bất kỳ thứ tự nào</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;25525511135&quot;
<strong>Đầu ra:</strong> [&quot;255.255.11.135&quot;,&quot;255.255.111.35&quot;]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;0000&quot;
<strong>Đầu ra:</strong> [&quot;0.0.0.0&quot;]
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;101023&quot;
<strong>Đầu ra:</strong> [&quot;1.0.10.23&quot;,&quot;1.0.102.3&quot;,&quot;10.1.0.23&quot;,&quot;10.10.2.3&quot;,&quot;101.0.2.3&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 20</code></li>
	<li><code>s</code> chỉ gồm các chữ số.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: DFS

<!-- thinking:start -->

> **Tư duy**
>
> Một IP có đúng bốn đoạn, mỗi đoạn nằm trong khoảng $0$– $255$ và không có số 0 ở đầu. $n \le 20$, vì vậy việc liệt kê ba vị trí cắt là khả thi, nhưng số 0 ở đầu, hiện tượng tràn số và số lượng đoạn bị phân tán trong các vòng lặp lồng nhau.
>
> Quay lui là hình thức tự nhiên: từ chỉ số $i$, thử $1$– $3$ chữ số làm đoạn tiếp theo và chỉ đệ quy nếu hợp lệ. Chỉ thu thập khi có bốn đoạn và chuỗi đã được sử dụng hết; dừng nếu đã có bốn đoạn hoặc vượt quá cuối chuỗi. Cắt tỉa các tiền tố không hợp lệ ngay từ đầu.

<!-- thinking:end -->

Chúng ta định nghĩa một hàm $dfs(i)$, biểu diễn danh sách các địa chỉ IP có thể tạo thành bắt đầu từ vị trí thứ $i$ của chuỗi $s$.

Các bước thực thi của hàm $dfs(i)$ như sau:

Nếu $i$ lớn hơn hoặc bằng độ dài của chuỗi $s$, điều đó có nghĩa là chúng ta đã hoàn tất việc ghép bốn đoạn của địa chỉ IP. Lúc này, chúng ta cần kiểm tra xem nó có đáp ứng các yêu cầu về bốn đoạn của địa chỉ IP hay không. Nếu có, thêm $IP$ hiện tại vào đáp án.

Nếu $i$ nhỏ hơn độ dài của chuỗi $s$, điều đó có nghĩa là chúng ta vẫn cần ghép một đoạn của địa chỉ IP. Lúc này, chúng ta cần xác định giá trị của đoạn này. Nếu giá trị lớn hơn $255$, hoặc vị trí hiện tại $i$ là $0$ và giá trị của một số vị trí sau $i$ lớn hơn $0$, điều đó có nghĩa là nó không đáp ứng yêu cầu, vì vậy chúng ta trả về ngay. Nếu không, thêm nó vào danh sách địa chỉ IP và tiếp tục tìm kiếm đoạn tiếp theo của địa chỉ IP.

Độ phức tạp thời gian là $O(n \times 3^4)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là độ dài của chuỗi $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def restoreIpAddresses(self, s: str) -> List[str]:
        def check(i: int, j: int) -> int:
            if s[i] == "0" and i != j:
                return False
            return 0 <= int(s[i : j + 1]) <= 255

        def dfs(i: int):
            if i >= n and len(t) == 4:
                ans.append(".".join(t))
                return
            if i >= n or len(t) >= 4:
                return
            for j in range(i, min(i + 3, n)):
                if check(i, j):
                    t.append(s[i : j + 1])
                    dfs(j + 1)
                    t.pop()

        n = len(s)
        ans = []
        t = []
        dfs(0)
        return ans
```

#### Java

```java
class Solution {
    private int n;
    private String s;
    private List<String> ans = new ArrayList<>();
    private List<String> t = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        n = s.length();
        this.s = s;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i >= n && t.size() == 4) {
            ans.add(String.join(".", t));
            return;
        }
        if (i >= n || t.size() >= 4) {
            return;
        }
        int x = 0;
        for (int j = i; j < Math.min(i + 3, n); ++j) {
            x = x * 10 + s.charAt(j) - '0';
            if (x > 255 || (s.charAt(i) == '0' && i != j)) {
                break;
            }
            t.add(s.substring(i, j + 1));
            dfs(j + 1);
            t.remove(t.size() - 1);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> restoreIpAddresses(string s) {
        int n = s.size();
        vector<string> ans;
        vector<string> t;
        function<void(int)> dfs = [&](int i) {
            if (i >= n && t.size() == 4) {
                ans.push_back(t[0] + "." + t[1] + "." + t[2] + "." + t[3]);
                return;
            }
            if (i >= n || t.size() >= 4) {
                return;
            }
            int x = 0;
            for (int j = i; j < min(n, i + 3); ++j) {
                x = x * 10 + s[j] - '0';
                if (x > 255 || (j > i && s[i] == '0')) {
                    break;
                }
                t.push_back(s.substr(i, j - i + 1));
                dfs(j + 1);
                t.pop_back();
            }
        };
        dfs(0);
        return ans;
    }
};
```

#### Go

```go
func restoreIpAddresses(s string) (ans []string) {
	n := len(s)
	t := []string{}
	var dfs func(int)
	dfs = func(i int) {
		if i >= n && len(t) == 4 {
			ans = append(ans, strings.Join(t, "."))
			return
		}
		if i >= n || len(t) == 4 {
			return
		}
		x := 0
		for j := i; j < i+3 && j < n; j++ {
			x = x*10 + int(s[j]-'0')
			if x > 255 || (j > i && s[i] == '0') {
				break
			}
			t = append(t, s[i:j+1])
			dfs(j + 1)
			t = t[:len(t)-1]
		}
	}
	dfs(0)
	return
}
```

#### TypeScript

```ts
function restoreIpAddresses(s: string): string[] {
    const n = s.length;
    const ans: string[] = [];
    const t: string[] = [];
    const dfs = (i: number): void => {
        if (i >= n && t.length === 4) {
            ans.push(t.join('.'));
            return;
        }
        if (i >= n || t.length === 4) {
            return;
        }
        let x = 0;
        for (let j = i; j < i + 3 && j < n; ++j) {
            x = x * 10 + s[j].charCodeAt(0) - '0'.charCodeAt(0);
            if (x > 255 || (j > i && s[i] === '0')) {
                break;
            }
            t.push(x.toString());
            dfs(j + 1);
            t.pop();
        }
    };
    dfs(0);
    return ans;
}
```

#### C#

```cs
public class Solution {
    private IList<string> ans = new List<string>();
    private IList<string> t = new List<string>();
    private int n;
    private string s;

    public IList<string> RestoreIpAddresses(string s) {
        n = s.Length;
        this.s = s;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i >= n && t.Count == 4) {
            ans.Add(string.Join(".", t));
            return;
        }
        if (i >= n || t.Count == 4) {
            return;
        }
        int x = 0;
        for (int j = i; j < i + 3 && j < n; ++j) {
            x = x * 10 + (s[j] - '0');
            if (x > 255 || (j > i && s[i] == '0')) {
                break;
            }
            t.Add(x.ToString());
            dfs(j + 1);
            t.RemoveAt(t.Count - 1);
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

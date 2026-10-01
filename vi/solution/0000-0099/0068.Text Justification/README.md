---
comments: true
difficulty: Hard
tags:
    - Array
    - String
    - Simulation
---

<!-- problem:start -->

# [68. Text Justification](https://leetcode.com/problems/text-justification)

[中文文档](/solution/0000-0099/0068.Text%20Justification/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng các chuỗi <code>words</code> và một độ rộng <code>maxWidth</code>, hãy định dạng văn bản sao cho mỗi dòng có chính xác <code>maxWidth</code> ký tự và được căn đều (cả trái và phải).</p>

<p>Bạn nên xếp các từ theo cách tham lam, tức là xếp nhiều từ nhất có thể vào mỗi dòng. Đệm thêm các dấu cách <code>&#39; &#39;</code> khi cần để mỗi dòng có chính xác <code>maxWidth</code> ký tự.</p>

<p>Các dấu cách thêm vào giữa các từ nên được phân bố đều nhất có thể. Nếu số dấu cách trên một dòng không chia hết cho số khoảng trống giữa các từ, các khoảng trống ở bên trái sẽ được gán nhiều dấu cách hơn các khoảng trống ở bên phải.</p>

<p>Đối với dòng cuối cùng của văn bản, dòng đó phải được căn trái và không chèn thêm dấu cách giữa các từ.</p>

<p><strong>Lưu ý:</strong></p>

<ul>
	<li>Một từ được định nghĩa là một dãy ký tự chỉ gồm các ký tự không phải dấu cách.</li>
	<li>Độ dài của mỗi từ được đảm bảo lớn hơn <code>0</code> và không vượt quá <code>maxWidth</code>.</li>
	<li>Mảng đầu vào <code>words</code> chứa ít nhất một từ.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> words = [&quot;This&quot;, &quot;is&quot;, &quot;an&quot;, &quot;example&quot;, &quot;of&quot;, &quot;text&quot;, &quot;justification.&quot;], maxWidth = 16
<strong>Đầu ra:</strong>
[
&nbsp; &nbsp;&quot;This &nbsp; &nbsp;is &nbsp; &nbsp;an&quot;,
&nbsp; &nbsp;&quot;example &nbsp;of text&quot;,
&nbsp; &nbsp;&quot;justification. &nbsp;&quot;
]</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> words = [&quot;What&quot;,&quot;must&quot;,&quot;be&quot;,&quot;acknowledgment&quot;,&quot;shall&quot;,&quot;be&quot;], maxWidth = 16
<strong>Đầu ra:</strong>
[
&nbsp; &quot;What &nbsp; must &nbsp; be&quot;,
&nbsp; &quot;acknowledgment &nbsp;&quot;,
&nbsp; &quot;shall be &nbsp; &nbsp; &nbsp; &nbsp;&quot;
]
<strong>Giải thích:</strong> Lưu ý rằng dòng cuối cùng là &quot;shall be    &quot; thay vì &quot;shall     be&quot;, vì dòng cuối cùng phải được căn trái thay vì căn đều.
Lưu ý rằng dòng thứ hai cũng được căn trái vì nó chỉ chứa một từ.</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> words = [&quot;Science&quot;,&quot;is&quot;,&quot;what&quot;,&quot;we&quot;,&quot;understand&quot;,&quot;well&quot;,&quot;enough&quot;,&quot;to&quot;,&quot;explain&quot;,&quot;to&quot;,&quot;a&quot;,&quot;computer.&quot;,&quot;Art&quot;,&quot;is&quot;,&quot;everything&quot;,&quot;else&quot;,&quot;we&quot;,&quot;do&quot;], maxWidth = 20
<strong>Đầu ra:</strong>
[
&nbsp; &quot;Science &nbsp;is &nbsp;what we&quot;,
  &quot;understand &nbsp; &nbsp; &nbsp;well&quot;,
&nbsp; &quot;enough to explain to&quot;,
&nbsp; &quot;a &nbsp;computer. &nbsp;Art is&quot;,
&nbsp; &quot;everything &nbsp;else &nbsp;we&quot;,
&nbsp; &quot;do &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&quot;
]</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 300</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 20</code></li>
	<li><code>words[i]</code> chỉ gồm các chữ cái và ký hiệu tiếng Anh.</li>
	<li><code>1 &lt;= maxWidth &lt;= 100</code></li>
	<li><code>words[i].length &lt;= maxWidth</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Mô phỏng

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là xếp nhiều từ nhất có thể vào một dòng, sau đó đệm bằng các dấu cách. Có nhiều nhất $300$ từ và độ rộng tối đa là $100$, nên một lần duyệt tuyến tính là đủ; không cần quay lui.
>
> Nút thắt không nằm ở thời gian chạy mà ở các quy tắc phân bố dấu cách: dòng cuối cùng và dòng chỉ có một từ phải được căn trái; mọi dòng khác phải phân bố các dấu cách thêm vào đều nhất có thể, với các dấu cách dư được đưa vào các khoảng trống bên trái trước.
>
> Vì vậy, chúng ta tham lam chọn các từ vừa với dòng, sau đó rẽ nhánh theo trường hợp “dòng cuối cùng / một từ” hoặc phân bố đều. Xử lý các dòng từ trái sang phải.

<!-- thinking:end -->

Chúng ta có thể mô phỏng quá trình theo các yêu cầu của đề bài. Lưu ý rằng nếu đây là dòng cuối cùng hoặc dòng chỉ có một từ, chúng ta nên căn trái. Nếu không, chúng ta nên phân bố các dấu cách đều nhau.

Độ phức tạp thời gian là $O(L)$ và độ phức tạp không gian là $O(L)$. Ở đây, $L$ là tổng độ dài của tất cả các từ.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def fullJustify(self, words: List[str], maxWidth: int) -> List[str]:
        ans = []
        i, n = 0, len(words)
        while i < n:
            t = []
            cnt = len(words[i])
            t.append(words[i])
            i += 1
            while i < n and cnt + 1 + len(words[i]) <= maxWidth:
                cnt += 1 + len(words[i])
                t.append(words[i])
                i += 1
            if i == n or len(t) == 1:
                left = ' '.join(t)
                right = ' ' * (maxWidth - len(left))
                ans.append(left + right)
                continue
            space_width = maxWidth - (cnt - len(t) + 1)
            w, m = divmod(space_width, len(t) - 1)
            row = []
            for j, s in enumerate(t[:-1]):
                row.append(s)
                row.append(' ' * (w + (1 if j < m else 0)))
            row.append(t[-1])
            ans.append(''.join(row))
        return ans
```

#### Java

```java
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        for (int i = 0, n = words.length; i < n;) {
            List<String> t = new ArrayList<>();
            t.add(words[i]);
            int cnt = words[i].length();
            ++i;
            while (i < n && cnt + 1 + words[i].length() <= maxWidth) {
                cnt += 1 + words[i].length();
                t.add(words[i++]);
            }
            if (i == n || t.size() == 1) {
                String left = String.join(" ", t);
                String right = " ".repeat(maxWidth - left.length());
                ans.add(left + right);
                continue;
            }
            int spaceWidth = maxWidth - (cnt - t.size() + 1);
            int w = spaceWidth / (t.size() - 1);
            int m = spaceWidth % (t.size() - 1);
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < t.size() - 1; ++j) {
                row.append(t.get(j));
                row.append(" ".repeat(w + (j < m ? 1 : 0)));
            }
            row.append(t.get(t.size() - 1));
            ans.add(row.toString());
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> fullJustify(vector<string>& words, int maxWidth) {
        vector<string> ans;
        for (int i = 0, n = words.size(); i < n;) {
            vector<string> t = {words[i]};
            int cnt = words[i].size();
            ++i;
            while (i < n && cnt + 1 + words[i].size() <= maxWidth) {
                cnt += 1 + words[i].size();
                t.emplace_back(words[i++]);
            }
            if (i == n || t.size() == 1) {
                string left = t[0];
                for (int j = 1; j < t.size(); ++j) {
                    left += " " + t[j];
                }
                string right = string(maxWidth - left.size(), ' ');
                ans.emplace_back(left + right);
                continue;
            }
            int spaceWidth = maxWidth - (cnt - t.size() + 1);
            int w = spaceWidth / (t.size() - 1);
            int m = spaceWidth % (t.size() - 1);
            string row;
            for (int j = 0; j < t.size() - 1; ++j) {
                row += t[j] + string(w + (j < m ? 1 : 0), ' ');
            }
            row += t.back();
            ans.emplace_back(row);
        }
        return ans;
    }
};
```

#### Go

```go
func fullJustify(words []string, maxWidth int) (ans []string) {
	for i, n := 0, len(words); i < n; {
		t := []string{words[i]}
		cnt := len(words[i])
		i++
		for i < n && cnt+1+len(words[i]) <= maxWidth {
			cnt += 1 + len(words[i])
			t = append(t, words[i])
			i++
		}
		if i == n || len(t) == 1 {
			left := strings.Join(t, " ")
			right := strings.Repeat(" ", maxWidth-len(left))
			ans = append(ans, left+right)
			continue
		}
		spaceWidth := maxWidth - (cnt - len(t) + 1)
		w := spaceWidth / (len(t) - 1)
		m := spaceWidth % (len(t) - 1)
		row := strings.Builder{}
		for j, s := range t[:len(t)-1] {
			row.WriteString(s)
			row.WriteString(strings.Repeat(" ", w))
			if j < m {
				row.WriteString(" ")
			}
		}
		row.WriteString(t[len(t)-1])
		ans = append(ans, row.String())
	}
	return
}
```

#### TypeScript

```ts
function fullJustify(words: string[], maxWidth: number): string[] {
    const ans: string[] = [];
    for (let i = 0, n = words.length; i < n;) {
        const t: string[] = [words[i]];
        let cnt = words[i++].length;
        while (i < n && cnt + 1 + words[i].length <= maxWidth) {
            t.push(words[i]);
            cnt += 1 + words[i++].length;
        }
        if (i === n || t.length === 1) {
            const left: string = t.join(' ');
            const right: string = ' '.repeat(maxWidth - left.length);
            ans.push(left + right);
            continue;
        }
        const spaceWidth: number = maxWidth - (cnt - t.length + 1);
        const w: number = Math.floor(spaceWidth / (t.length - 1));
        const m: number = spaceWidth % (t.length - 1);
        const row: string[] = [];
        for (let j = 0; j < t.length - 1; ++j) {
            row.push(t[j]);
            row.push(' '.repeat(w + (j < m ? 1 : 0)));
        }
        row.push(t[t.length - 1]);
        ans.push(row.join(''));
    }
    return ans;
}
```

#### C#

```cs
public class Solution {
    public IList<string> FullJustify(string[] words, int maxWidth) {
        var ans = new List<string>();
        for (int i = 0, n = words.Length; i < n;) {
            var t = new List<string>();
            t.Add(words[i]);
            int cnt = words[i].Length;
            ++i;
            while (i < n && cnt + 1 + words[i].Length <= maxWidth) {
                t.Add(words[i]);
                cnt += 1 + words[i].Length;
                ++i;
            }
            if (i == n || t.Count == 1) {
                string left = string.Join(" ", t);
                string right = new string(' ', maxWidth - left.Length);
                ans.Add(left + right);
                continue;
            }
            int spaceWidth = maxWidth - (cnt - t.Count + 1);
            int w = spaceWidth / (t.Count - 1);
            int m = spaceWidth % (t.Count - 1);
            var row = new StringBuilder();
            for (int j = 0; j < t.Count - 1; ++j) {
                row.Append(t[j]);
                row.Append(new string(' ', w + (j < m ? 1 : 0)));
            }
            row.Append(t[t.Count - 1]);
            ans.Add(row.ToString());
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

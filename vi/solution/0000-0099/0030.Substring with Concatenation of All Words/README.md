---
comments: true
difficulty: Hard
tags:
    - Hash Table
    - String
    - Sliding Window
---

<!-- problem:start -->

# [30. Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words)

[中文文档](/solution/0000-0099/0030.Substring%20with%20Concatenation%20of%20All%20Words/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cung cấp một chuỗi <code>s</code> và một mảng các chuỗi <code>words</code>. Tất cả các chuỗi trong <code>words</code> đều có <strong>cùng độ dài</strong>.</p>

<p>Một <strong>chuỗi được nối</strong> là một chuỗi chứa chính xác tất cả các chuỗi trong một hoán vị bất kỳ của <code>words</code> được nối lại.</p>

<ul>
	<li>Ví dụ, nếu <code>words = [&quot;ab&quot;,&quot;cd&quot;,&quot;ef&quot;]</code>, thì <code>&quot;abcdef&quot;</code>, <code>&quot;abefcd&quot;</code>, <code>&quot;cdabef&quot;</code>, <code>&quot;cdefab&quot;</code>, <code>&quot;efabcd&quot;</code> và <code>&quot;efcdab&quot;</code> đều là các chuỗi được nối. <code>&quot;acdbef&quot;</code> không phải là một chuỗi được nối vì nó không phải là phép nối của bất kỳ hoán vị nào của <code>words</code>.</li>
</ul>

<p>Trả về một mảng chứa <em>các chỉ số bắt đầu</em> của tất cả các chuỗi con được nối trong <code>s</code>. Bạn có thể trả về đáp án theo <strong>bất kỳ thứ tự nào</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">s = &quot;barfoothefoobarman&quot;, words = [&quot;foo&quot;,&quot;bar&quot;]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">[0,9]</span></p>

<p><strong>Giải thích:</strong></p>

<p>Chuỗi con bắt đầu tại 0 là <code>&quot;barfoo&quot;</code>. Đây là phép nối của <code>[&quot;bar&quot;,&quot;foo&quot;]</code>, là một hoán vị của <code>words</code>.<br />
Chuỗi con bắt đầu tại 9 là <code>&quot;foobar&quot;</code>. Đây là phép nối của <code>[&quot;foo&quot;,&quot;bar&quot;]</code>, là một hoán vị của <code>words</code>.</p>
</div>

<p><strong class="example">Ví dụ 2:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">s = &quot;wordgoodgoodgoodbestword&quot;, words = [&quot;word&quot;,&quot;good&quot;,&quot;best&quot;,&quot;word&quot;]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">[]</span></p>

<p><strong>Giải thích:</strong></p>

<p>Không có chuỗi con được nối nào.</p>
</div>

<p><strong class="example">Ví dụ 3:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">s = &quot;barfoofoobarthefoobarman&quot;, words = [&quot;bar&quot;,&quot;foo&quot;,&quot;the&quot;]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">[6,9,12]</span></p>

<p><strong>Giải thích:</strong></p>

<p>Chuỗi con bắt đầu tại 6 là <code>&quot;foobarthe&quot;</code>. Đây là phép nối của <code>[&quot;foo&quot;,&quot;bar&quot;,&quot;the&quot;]</code>.<br />
Chuỗi con bắt đầu tại 9 là <code>&quot;barthefoo&quot;</code>. Đây là phép nối của <code>[&quot;bar&quot;,&quot;the&quot;,&quot;foo&quot;]</code>.<br />
Chuỗi con bắt đầu tại 12 là <code>&quot;thefoobar&quot;</code>. Đây là phép nối của <code>[&quot;the&quot;,&quot;foo&quot;,&quot;bar&quot;]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words.length &lt;= 5000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 30</code></li>
	<li><code>s</code> và <code>words[i]</code> chỉ gồm các chữ cái tiếng Anh viết thường.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Bảng băm + Cửa sổ trượt

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là thử mọi vị trí bắt đầu và kiểm tra xem cửa sổ có độ dài $n\cdot k$ có phải là một hoán vị nào đó của $words$ hay không. Với $m \le 10^4$, $n \le 5000$, $k \le 30$, việc cắt $n$ từ cho mỗi vị trí bắt đầu có độ phức tạp trong trường hợp xấu nhất là $O(mnk)$ và sẽ không đạt.
>
> Nút thắt nằm ở việc đếm lại từ đầu các cửa sổ chồng lấn lên nhau rất nhiều. Các từ có cùng độ dài $k$, nên cửa sổ trượt theo từng bước $k$ và tần suất có thể được duy trì tăng dần trong một bảng băm.
>
> Chỉ có $k$ cách căn chỉnh (bắt đầu từ $0,1,\ldots,k-1$). Với mỗi cách, đầu phải lấy vào một token có độ dài $k$: một từ nằm ngoài $cnt$ sẽ đặt lại cửa sổ; một từ bị sử dụng quá số lần cho phép sẽ bị đẩy ra từ bên trái cho đến khi các số đếm hợp lệ; khi có đúng $n$ từ, chúng ta ghi nhận chỉ số bên trái.
>
> Mỗi ký tự đi vào và rời khỏi một cửa sổ một số lần cố định, nên tổng thời gian là $O(mk)$.

<!-- thinking:end -->

Chúng ta sử dụng một bảng băm $cnt$ để đếm số lần mỗi từ xuất hiện trong $words$, và sử dụng một bảng băm $cnt1$ để đếm số lần mỗi từ xuất hiện trong cửa sổ trượt hiện tại. Chúng ta ký hiệu độ dài của chuỗi $s$ là $m$, số lượng từ trong mảng chuỗi $words$ là $n$, và độ dài của mỗi từ là $k$.

Chúng ta có thể liệt kê điểm bắt đầu $i$ của cửa sổ trượt, trong đó $0 \lt i < k$. Với mỗi điểm bắt đầu, chúng ta duy trì một cửa sổ trượt có biên trái là $l$, biên phải là $r$, và số lượng từ trong cửa sổ trượt là $t$. Ngoài ra, chúng ta sử dụng một bảng băm $cnt1$ để đếm số lần mỗi từ xuất hiện trong cửa sổ trượt.

Mỗi lần, chúng ta trích xuất chuỗi $s[r:r+k]$. Nếu $s[r:r+k]$ không có trong bảng băm $cnt$, điều đó có nghĩa là các từ trong cửa sổ trượt hiện tại không hợp lệ. Chúng ta cập nhật biên trái $l$ thành $r$, xóa bảng băm $cnt1$, và đặt lại số lượng từ $t$ về 0. Nếu $s[r:r+k]$ có trong bảng băm $cnt$, điều đó có nghĩa là các từ trong cửa sổ trượt hiện tại hợp lệ. Chúng ta tăng số lượng từ $t$ lên 1, và tăng số đếm của $s[r:r+k]$ trong bảng băm $cnt1$ lên 1. Nếu $cnt1[s[r:r+k]]$ lớn hơn $cnt[s[r:r+k]]$, điều đó có nghĩa là $s[r:r+k]$ xuất hiện quá nhiều lần trong cửa sổ trượt hiện tại. Chúng ta cần dịch biên trái $l$ sang phải cho đến khi $cnt1[s[r:r+k]] = cnt[s[r:r+k]]$. Nếu $t = n$, điều đó có nghĩa là các từ trong cửa sổ trượt hiện tại hoàn toàn hợp lệ, và chúng ta thêm biên trái $l$ vào mảng đáp án.

Độ phức tạp thời gian là $O(m \times k)$, và độ phức tạp không gian là $O(n \times k)$. Ở đây, $m$ và $n$ lần lượt là độ dài của chuỗi $s$ và mảng chuỗi $words$, còn $k$ là độ dài của các từ trong mảng chuỗi $words$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        cnt = Counter(words)
        m, n = len(s), len(words)
        k = len(words[0])
        ans = []
        for i in range(k):
            l = r = i
            cnt1 = Counter()
            while r + k <= m:
                t = s[r : r + k]
                r += k
                if cnt[t] == 0:
                    l = r
                    cnt1.clear()
                    continue
                cnt1[t] += 1
                while cnt1[t] > cnt[t]:
                    rem = s[l : l + k]
                    l += k
                    cnt1[rem] -= 1
                if r - l == n * k:
                    ans.append(l)
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> cnt = new HashMap<>();
        for (var w : words) {
            cnt.merge(w, 1, Integer::sum);
        }
        List<Integer> ans = new ArrayList<>();
        int m = s.length(), n = words.length, k = words[0].length();
        for (int i = 0; i < k; ++i) {
            int l = i, r = i;
            Map<String, Integer> cnt1 = new HashMap<>();
            while (r + k <= m) {
                var t = s.substring(r, r + k);
                r += k;
                if (!cnt.containsKey(t)) {
                    cnt1.clear();
                    l = r;
                    continue;
                }
                cnt1.merge(t, 1, Integer::sum);
                while (cnt1.get(t) > cnt.get(t)) {
                    String w = s.substring(l, l + k);
                    if (cnt1.merge(w, -1, Integer::sum) == 0) {
                        cnt1.remove(w);
                    }
                    l += k;
                }
                if (r - l == n * k) {
                    ans.add(l);
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> findSubstring(string s, vector<string>& words) {
        unordered_map<string, int> cnt;
        for (const auto& w : words) {
            cnt[w]++;
        }

        vector<int> ans;
        int m = s.length(), n = words.size(), k = words[0].length();

        for (int i = 0; i < k; ++i) {
            int l = i, r = i;
            unordered_map<string, int> cnt1;
            while (r + k <= m) {
                string t = s.substr(r, k);
                r += k;

                if (!cnt.contains(t)) {
                    cnt1.clear();
                    l = r;
                    continue;
                }

                cnt1[t]++;

                while (cnt1[t] > cnt[t]) {
                    string w = s.substr(l, k);
                    if (--cnt1[w] == 0) {
                        cnt1.erase(w);
                    }
                    l += k;
                }

                if (r - l == n * k) {
                    ans.push_back(l);
                }
            }
        }

        return ans;
    }
};
```

#### Go

```go
func findSubstring(s string, words []string) (ans []int) {
	cnt := make(map[string]int)
	for _, w := range words {
		cnt[w]++
	}
	m, n, k := len(s), len(words), len(words[0])
	for i := 0; i < k; i++ {
		l, r := i, i
		cnt1 := make(map[string]int)
		for r+k <= m {
			t := s[r : r+k]
			r += k

			if _, exists := cnt[t]; !exists {
				cnt1 = make(map[string]int)
				l = r
				continue
			}
			cnt1[t]++
			for cnt1[t] > cnt[t] {
				w := s[l : l+k]
				cnt1[w]--
				if cnt1[w] == 0 {
					delete(cnt1, w)
				}
				l += k
			}
			if r-l == n*k {
				ans = append(ans, l)
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function findSubstring(s: string, words: string[]): number[] {
    const cnt: Map<string, number> = new Map();
    for (const w of words) {
        cnt.set(w, (cnt.get(w) || 0) + 1);
    }
    const ans: number[] = [];
    const [m, n, k] = [s.length, words.length, words[0].length];
    for (let i = 0; i < k; i++) {
        let [l, r] = [i, i];
        const cnt1: Map<string, number> = new Map();
        while (r + k <= m) {
            const t = s.substring(r, r + k);
            r += k;
            if (!cnt.has(t)) {
                cnt1.clear();
                l = r;
                continue;
            }
            cnt1.set(t, (cnt1.get(t) || 0) + 1);
            while (cnt1.get(t)! > cnt.get(t)!) {
                const w = s.substring(l, l + k);
                cnt1.set(w, cnt1.get(w)! - 1);
                if (cnt1.get(w) === 0) {
                    cnt1.delete(w);
                }
                l += k;
            }
            if (r - l === n * k) {
                ans.push(l);
            }
        }
    }
    return ans;
}
```

#### C#

```cs
public class Solution {
    public IList<int> FindSubstring(string s, string[] words) {
        var cnt = new Dictionary<string, int>();
        foreach (var w in words) {
            if (cnt.ContainsKey(w)) {
                cnt[w]++;
            } else {
                cnt[w] = 1;
            }
        }

        var ans = new List<int>();
        int m = s.Length, n = words.Length, k = words[0].Length;

        for (int i = 0; i < k; ++i) {
            int l = i, r = i;
            var cnt1 = new Dictionary<string, int>();
            while (r + k <= m) {
                var t = s.Substring(r, k);
                r += k;

                if (!cnt.ContainsKey(t)) {
                    cnt1.Clear();
                    l = r;
                    continue;
                }

                if (cnt1.ContainsKey(t)) {
                    cnt1[t]++;
                } else {
                    cnt1[t] = 1;
                }

                while (cnt1[t] > cnt[t]) {
                    var w = s.Substring(l, k);
                    cnt1[w]--;
                    if (cnt1[w] == 0) {
                        cnt1.Remove(w);
                    }
                    l += k;
                }

                if (r - l == n * k) {
                    ans.Add(l);
                }
            }
        }

        return ans;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @param String[] $words
     * @return Integer[]
     */
    function findSubstring($s, $words) {
        $cnt = [];
        foreach ($words as $w) {
            if (isset($cnt[$w])) {
                $cnt[$w]++;
            } else {
                $cnt[$w] = 1;
            }
        }

        $ans = [];
        $m = strlen($s);
        $n = count($words);
        $k = strlen($words[0]);

        for ($i = 0; $i < $k; $i++) {
            $l = $i;
            $r = $i;
            $cnt1 = [];
            while ($r + $k <= $m) {
                $t = substr($s, $r, $k);
                $r += $k;

                if (!isset($cnt[$t])) {
                    $cnt1 = [];
                    $l = $r;
                    continue;
                }

                if (isset($cnt1[$t])) {
                    $cnt1[$t]++;
                } else {
                    $cnt1[$t] = 1;
                }

                while ($cnt1[$t] > $cnt[$t]) {
                    $w = substr($s, $l, $k);
                    $cnt1[$w]--;
                    if ($cnt1[$w] == 0) {
                        unset($cnt1[$w]);
                    }
                    $l += $k;
                }

                if ($r - $l == $n * $k) {
                    $ans[] = $l;
                }
            }
        }

        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

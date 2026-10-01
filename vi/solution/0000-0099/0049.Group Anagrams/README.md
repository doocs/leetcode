---
comments: true
difficulty: Medium
tags:
    - Array
    - Hash Table
    - String
    - Sorting
---

<!-- problem:start -->

# [49. Group Anagrams](https://leetcode.com/problems/group-anagrams)

[中文文档](/solution/0000-0099/0049.Group%20Anagrams/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng các chuỗi <code>strs</code>, hãy nhóm các <span data-keyword="anagram">anagram</span> lại với nhau. Bạn có thể trả về đáp án theo <strong>bất kỳ thứ tự nào</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">strs = [&quot;eat&quot;,&quot;tea&quot;,&quot;tan&quot;,&quot;ate&quot;,&quot;nat&quot;,&quot;bat&quot;]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">[[&quot;bat&quot;],[&quot;nat&quot;,&quot;tan&quot;],[&quot;ate&quot;,&quot;eat&quot;,&quot;tea&quot;]]</span></p>

<p><strong>Giải thích:</strong></p>

<ul>
	<li>Không có chuỗi nào trong strs có thể được sắp xếp lại để tạo thành <code>&quot;bat&quot;</code>.</li>
	<li>Các chuỗi <code>&quot;nat&quot;</code> và <code>&quot;tan&quot;</code> là các anagram vì chúng có thể được sắp xếp lại để tạo thành chuỗi kia.</li>
	<li>Các chuỗi <code>&quot;ate&quot;</code>, <code>&quot;eat&quot;</code> và <code>&quot;tea&quot;</code> là các anagram vì chúng có thể được sắp xếp lại để tạo thành chuỗi kia.</li>
</ul>
</div>

<p><strong class="example">Ví dụ 2:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">strs = [&quot;&quot;]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">[[&quot;&quot;]]</span></p>
</div>

<p><strong class="example">Ví dụ 3:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">strs = [&quot;a&quot;]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">[[&quot;a&quot;]]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= strs[i].length &lt;= 100</code></li>
	<li><code>strs[i]</code> gồm các chữ cái tiếng Anh viết thường.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Bảng băm

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là kiểm tra từng cặp: sắp xếp từng chuỗi rồi so sánh. Cách này đúng, nhưng với $n \le 10^4$ và $k \le 100$, độ phức tạp $O(n^2 \cdot k \log k)$ quá chậm.
>
> Điểm nghẽn là việc so sánh từng cặp. Các anagram có cùng một dạng đã sắp xếp — chuỗi đó là mã định danh của nhóm.
>
> Dùng chuỗi đã sắp xếp làm key và danh sách các chuỗi ban đầu làm value. Một lượt duyệt qua bảng băm sẽ gom chúng thành các nhóm; không cần ghép cặp để so sánh.

<!-- thinking:end -->

1. Duyệt qua mảng chuỗi, sắp xếp từng chuỗi theo **thứ tự từ điển của các ký tự** để nhận được một chuỗi mới.
2. Dùng chuỗi mới làm `key` và `[str]` làm `value`, rồi lưu chúng vào bảng băm (`HashMap<String, List<String>>`).
3. Khi gặp cùng `key` trong các lượt duyệt tiếp theo, thêm chuỗi đó vào `value` tương ứng.

Xét `strs = ["eat", "tea", "tan", "ate", "nat", "bat"]` làm ví dụ. Khi kết thúc quá trình duyệt, trạng thái của bảng băm là:

| key     | value                   |
| ------- | ----------------------- |
| `"aet"` | `["eat", "tea", "ate"]` |
| `"ant"` | `["tan", "nat"] `       |
| `"abt"` | `["bat"] `              |

Cuối cùng, trả về danh sách `value` của bảng băm.

Độ phức tạp thời gian là $O(n\times k\times \log k)$, trong đó $n$ và $k$ lần lượt là độ dài của mảng chuỗi và độ dài lớn nhất của một chuỗi.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        d = defaultdict(list)
        for s in strs:
            k = ''.join(sorted(s))
            d[k].append(s)
        return list(d.values())
```

#### Java

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> d = new HashMap<>();
        for (String s : strs) {
            char[] t = s.toCharArray();
            Arrays.sort(t);
            String k = String.valueOf(t);
            d.computeIfAbsent(k, key -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(d.values());
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        unordered_map<string, vector<string>> d;
        for (auto& s : strs) {
            string k = s;
            sort(k.begin(), k.end());
            d[k].emplace_back(s);
        }
        vector<vector<string>> ans;
        for (auto& [_, v] : d) ans.emplace_back(v);
        return ans;
    }
};
```

#### Go

```go
func groupAnagrams(strs []string) (ans [][]string) {
	d := map[string][]string{}
	for _, s := range strs {
		t := []byte(s)
		sort.Slice(t, func(i, j int) bool { return t[i] < t[j] })
		k := string(t)
		d[k] = append(d[k], s)
	}
	for _, v := range d {
		ans = append(ans, v)
	}
	return
}
```

#### TypeScript

```ts
function groupAnagrams(strs: string[]): string[][] {
    const d: Map<string, string[]> = new Map();
    for (const s of strs) {
        const k = s.split('').sort().join('');
        if (!d.has(k)) {
            d.set(k, []);
        }
        d.get(k)!.push(s);
    }
    return Array.from(d.values());
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn group_anagrams(strs: Vec<String>) -> Vec<Vec<String>> {
        let mut d = HashMap::new();
        for s in strs {
            let mut t: Vec<char> = s.chars().collect();
            t.sort_unstable();
            let k: String = t.into_iter().collect();
            d.entry(k).or_insert_with(Vec::new).push(s);
        }
        d.into_values().collect()
    }
}
```

#### C#

```cs
public class Solution {
    public IList<IList<string>> GroupAnagrams(string[] strs) {
        var d = new Dictionary<string, List<string>>();
        foreach (string s in strs) {
            char[] t = s.ToCharArray();
            Array.Sort(t);
            string k = new string(t);
            if (!d.ContainsKey(k)) {
                d[k] = new List<string>();
            }
            d[k].Add(s);
        }
        return new List<IList<string>>(d.Values);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Đếm

<!-- thinking:start -->

> **Tư duy**
>
> Giải pháp 1 sắp xếp mọi chuỗi, với chi phí $O(k \log k)$ cho mỗi chuỗi. Bảng chữ cái có $26$ chữ cái thường; với $k \le 100$, hệ số $\log k$ là không cần thiết.
>
> Điều còn thiếu là một key rẻ hơn. Đếm từng chữ cái và dùng bộ $26$ phần tử làm key. Vẫn là nhóm bằng hashing, nhưng thời gian xử lý mỗi chuỗi là tuyến tính.

<!-- thinking:end -->

Chúng ta cũng có thể thay phần sắp xếp trong Giải pháp 1 bằng cách đếm, tức là sử dụng các ký tự trong mỗi chuỗi $s$ và số lần xuất hiện của chúng làm `key`, rồi sử dụng chuỗi $s$ làm `value` để lưu vào bảng băm.

Độ phức tạp thời gian là $O(n\times (k + C))$, trong đó $n$ và $k$ lần lượt là độ dài của mảng chuỗi và độ dài lớn nhất của một chuỗi, còn $C$ là kích thước của tập ký tự. Trong bài toán này, $C = 26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        d = defaultdict(list)
        for s in strs:
            cnt = [0] * 26
            for c in s:
                cnt[ord(c) - ord('a')] += 1
            d[tuple(cnt)].append(s)
        return list(d.values())
```

#### Java

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> d = new HashMap<>();
        for (String s : strs) {
            int[] cnt = new int[26];
            for (int i = 0; i < s.length(); ++i) {
                ++cnt[s.charAt(i) - 'a'];
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; ++i) {
                if (cnt[i] > 0) {
                    sb.append((char) ('a' + i)).append(cnt[i]);
                }
            }
            String k = sb.toString();
            d.computeIfAbsent(k, key -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(d.values());
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        unordered_map<string, vector<string>> d;
        for (auto& s : strs) {
            int cnt[26] = {0};
            for (auto& c : s) ++cnt[c - 'a'];
            string k;
            for (int i = 0; i < 26; ++i) {
                if (cnt[i]) {
                    k += 'a' + i;
                    k += to_string(cnt[i]);
                }
            }
            d[k].emplace_back(s);
        }
        vector<vector<string>> ans;
        for (auto& [_, v] : d) ans.emplace_back(v);
        return ans;
    }
};
```

#### Go

```go
func groupAnagrams(strs []string) (ans [][]string) {
	d := map[[26]int][]string{}
	for _, s := range strs {
		cnt := [26]int{}
		for _, c := range s {
			cnt[c-'a']++
		}
		d[cnt] = append(d[cnt], s)
	}
	for _, v := range d {
		ans = append(ans, v)
	}
	return
}
```

#### TypeScript

```ts
function groupAnagrams(strs: string[]): string[][] {
    const d = new Map<string, string[]>();
    for (const s of strs) {
        const cnt = new Array(26).fill(0);
        for (const c of s) {
            cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)]++;
        }
        const key = cnt.join(',');
        if (!d.has(key)) {
            d.set(key, []);
        }
        d.get(key)!.push(s);
    }
    return Array.from(d.values());
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn group_anagrams(strs: Vec<String>) -> Vec<Vec<String>> {
        let mut d = HashMap::new();
        for s in strs {
            let mut cnt = [0; 26];
            for c in s.chars() {
                cnt[(c as usize) - ('a' as usize)] += 1;
            }
            d.entry(cnt).or_insert_with(Vec::new).push(s);
        }
        d.into_values().collect()
    }
}
```

#### C#

```cs
public class Solution {
    public IList<IList<string>> GroupAnagrams(string[] strs) {
        var d = new Dictionary<string, List<string>>();
        foreach (string s in strs) {
            int[] cnt = new int[26];
            foreach (char c in s) {
                cnt[c - 'a']++;
            }
            string key = string.Join(",", cnt);
            if (!d.ContainsKey(key)) {
                d[key] = new List<string>();
            }
            d[key].Add(s);
        }
        return new List<IList<string>>(d.Values);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

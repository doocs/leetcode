---
comments: true
difficulty: Easy
tags:
    - Hash Table
    - Math
    - String
---

<!-- problem:start -->

# [13. Roman to Integer](https://leetcode.com/problems/roman-to-integer)

[中文文档](/solution/0000-0099/0013.Roman%20to%20Integer/README.md)

## Mô tả

<!-- description:start -->

<p>Các chữ số La Mã được biểu diễn bằng bảy ký hiệu khác nhau:&nbsp;<code>I</code>, <code>V</code>, <code>X</code>, <code>L</code>, <code>C</code>, <code>D</code> và <code>M</code>.</p>

<pre>
<strong>Ký hiệu</strong>       <strong>Giá trị</strong>
I             1
V             5
X             10
L             50
C             100
D             500
M             1000</pre>

<p>Ví dụ, <code>2</code> được viết là <code>II</code>&nbsp;trong chữ số La Mã, tức là cộng hai số một lại với nhau. <code>12</code> được viết là&nbsp;<code>XII</code>, đơn giản là <code>X + II</code>. Số <code>27</code> được viết là <code>XXVII</code>, tức là <code>XX + V + II</code>.</p>

<p>Các chữ số La Mã thường được viết từ lớn đến nhỏ theo thứ tự từ trái sang phải. Tuy nhiên, chữ số của bốn không phải là <code>IIII</code>. Thay vào đó, số bốn được viết là <code>IV</code>. Vì một đứng trước năm nên ta trừ nó để được bốn. Nguyên tắc tương tự áp dụng cho số chín, được viết là <code>IX</code>. Có sáu trường hợp sử dụng phép trừ:</p>

<ul>
	<li><code>I</code> có thể được đặt trước <code>V</code> (5) và <code>X</code> (10) để tạo thành 4 và 9.&nbsp;</li>
	<li><code>X</code> có thể được đặt trước <code>L</code> (50) và <code>C</code> (100) để tạo thành 40 và 90.&nbsp;</li>
	<li><code>C</code> có thể được đặt trước <code>D</code> (500) và <code>M</code> (1000) để tạo thành 400 và 900.</li>
</ul>

<p>Cho một chữ số La Mã, hãy chuyển nó thành một số nguyên.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;III&quot;
<strong>Đầu ra:</strong> 3
<strong>Giải thích:</strong> III = 3.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;LVIII&quot;
<strong>Đầu ra:</strong> 58
<strong>Giải thích:</strong> L = 50, V= 5, III = 3.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;MCMXCIV&quot;
<strong>Đầu ra:</strong> 1994
<strong>Giải thích:</strong> M = 1000, CM = 900, XC = 90 và IV = 4.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 15</code></li>
	<li><code>s</code> chỉ chứa các ký tự&nbsp;<code>(&#39;I&#39;, &#39;V&#39;, &#39;X&#39;, &#39;L&#39;, &#39;C&#39;, &#39;D&#39;, &#39;M&#39;)</code>.</li>
	<li><strong>Đảm bảo</strong>&nbsp;rằng <code>s</code> là một chữ số La Mã hợp lệ trong phạm vi <code>[1, 3999]</code>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Bảng băm + Mô phỏng

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là duyệt với các trường hợp đặc biệt cho sáu cặp trừ ($\textit{IV}$, $\textit{IX}$, …), và cộng trong các trường hợp còn lại. Vì $|s|\le 15$, cách này sẽ vượt qua, nhưng các ngoại lệ rất dễ bị bỏ sót.
>
> Điểm khó là quyết định xem ký tự hiện tại được cộng hay trừ. Một ký tự được trừ khi và chỉ khi nó nhỏ hơn ký tự tiếp theo; ký tự cuối cùng luôn được cộng. Chúng ta không cần liệt kê sáu cặp.
>
> Vì vậy, chúng ta lưu giá trị của mỗi ký tự trong một bảng băm, so sánh các ký tự kề nhau để chọn dấu, rồi cộng ký tự cuối cùng.

<!-- thinking:end -->

Đầu tiên, chúng ta sử dụng một bảng băm $d$ để ghi lại giá trị số tương ứng với mỗi ký tự. Sau đó, chúng ta duyệt chuỗi $s$ từ trái sang phải. Nếu giá trị số tương ứng với ký tự hiện tại nhỏ hơn giá trị số tương ứng với ký tự bên phải, chúng ta trừ giá trị số tương ứng với ký tự hiện tại. Nếu không, chúng ta cộng giá trị số tương ứng với ký tự hiện tại.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(m)$. Ở đây, $n$ và $m$ lần lượt là độ dài của chuỗi $s$ và kích thước của tập ký tự.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def romanToInt(self, s: str) -> int:
        d = {'I': 1, 'V': 5, 'X': 10, 'L': 50, 'C': 100, 'D': 500, 'M': 1000}
        return sum((-1 if d[a] < d[b] else 1) * d[a] for a, b in pairwise(s)) + d[s[-1]]
```

#### Java

```java
class Solution {
    public int romanToInt(String s) {
        String cs = "IVXLCDM";
        int[] vs = {1, 5, 10, 50, 100, 500, 1000};
        Map<Character, Integer> d = new HashMap<>();
        for (int i = 0; i < vs.length; ++i) {
            d.put(cs.charAt(i), vs[i]);
        }
        int n = s.length();
        int ans = d.get(s.charAt(n - 1));
        for (int i = 0; i < n - 1; ++i) {
            int sign = d.get(s.charAt(i)) < d.get(s.charAt(i + 1)) ? -1 : 1;
            ans += sign * d.get(s.charAt(i));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int romanToInt(string s) {
        unordered_map<char, int> nums{
            {'I', 1},
            {'V', 5},
            {'X', 10},
            {'L', 50},
            {'C', 100},
            {'D', 500},
            {'M', 1000},
        };
        int ans = nums[s.back()];
        for (int i = 0; i < s.size() - 1; ++i) {
            int sign = nums[s[i]] < nums[s[i + 1]] ? -1 : 1;
            ans += sign * nums[s[i]];
        }
        return ans;
    }
};
```

#### Go

```go
func romanToInt(s string) (ans int) {
	d := map[byte]int{'I': 1, 'V': 5, 'X': 10, 'L': 50, 'C': 100, 'D': 500, 'M': 1000}
	for i := 0; i < len(s)-1; i++ {
		if d[s[i]] < d[s[i+1]] {
			ans -= d[s[i]]
		} else {
			ans += d[s[i]]
		}
	}
	ans += d[s[len(s)-1]]
	return
}
```

#### TypeScript

```ts
function romanToInt(s: string): number {
    const d: Map<string, number> = new Map([
        ['I', 1],
        ['V', 5],
        ['X', 10],
        ['L', 50],
        ['C', 100],
        ['D', 500],
        ['M', 1000],
    ]);
    let ans: number = d.get(s[s.length - 1])!;
    for (let i = 0; i < s.length - 1; ++i) {
        const sign = d.get(s[i])! < d.get(s[i + 1])! ? -1 : 1;
        ans += sign * d.get(s[i])!;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn roman_to_int(s: String) -> i32 {
        let d = vec![
            ('I', 1),
            ('V', 5),
            ('X', 10),
            ('L', 50),
            ('C', 100),
            ('D', 500),
            ('M', 1000),
        ]
        .into_iter()
        .collect::<std::collections::HashMap<_, _>>();

        let s: Vec<char> = s.chars().collect();
        let mut ans = 0;
        let len = s.len();

        for i in 0..len - 1 {
            if d[&s[i]] < d[&s[i + 1]] {
                ans -= d[&s[i]];
            } else {
                ans += d[&s[i]];
            }
        }

        ans += d[&s[len - 1]];
        ans
    }
}
```

#### JavaScript

```js
const romanToInt = function (s) {
    const d = {
        I: 1,
        V: 5,
        X: 10,
        L: 50,
        C: 100,
        D: 500,
        M: 1000,
    };
    let ans = d[s[s.length - 1]];
    for (let i = 0; i < s.length - 1; ++i) {
        const sign = d[s[i]] < d[s[i + 1]] ? -1 : 1;
        ans += sign * d[s[i]];
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int RomanToInt(string s) {
        Dictionary<char, int> d = new Dictionary<char, int>();
        d.Add('I', 1);
        d.Add('V', 5);
        d.Add('X', 10);
        d.Add('L', 50);
        d.Add('C', 100);
        d.Add('D', 500);
        d.Add('M', 1000);
        int ans = d[s[s.Length - 1]];
        for (int i = 0; i < s.Length - 1; ++i) {
            int sign = d[s[i]] < d[s[i + 1]] ? -1 : 1;
            ans += sign * d[s[i]];
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
     * @return Integer
     */
    function romanToInt($s) {
        $d = [
            'I' => 1,
            'V' => 5,
            'X' => 10,
            'L' => 50,
            'C' => 100,
            'D' => 500,
            'M' => 1000,
        ];
        $ans = 0;
        $len = strlen($s);

        for ($i = 0; $i < $len - 1; $i++) {
            if ($d[$s[$i]] < $d[$s[$i + 1]]) {
                $ans -= $d[$s[$i]];
            } else {
                $ans += $d[$s[$i]];
            }
        }

        $ans += $d[$s[$len - 1]];
        return $ans;
    }
}
```

#### Ruby

```rb
# @param {String} s
# @return {Integer}
def roman_to_int(s)
  d = {
      'I' => 1, 'V' => 5, 'X' => 10,
      'L' => 50, 'C' => 100,
      'D' => 500, 'M' => 1000
  }
  ans = 0
  len = s.length

  (0...len-1).each do |i|
      if d[s[i]] < d[s[i + 1]]
          ans -= d[s[i]]
      else
          ans += d[s[i]]
      end
  end

  ans += d[s[len - 1]]
  ans
end
```

#### C

```c
int nums(char c) {
    switch (c) {
    case 'I': return 1;
    case 'V': return 5;
    case 'X': return 10;
    case 'L': return 50;
    case 'C': return 100;
    case 'D': return 500;
    case 'M': return 1000;
    default: return 0;
    }
}

int romanToInt(char* s) {
    int ans = nums(s[strlen(s) - 1]);
    for (int i = 0; i < (int) strlen(s) - 1; ++i) {
        int sign = nums(s[i]) < nums(s[i + 1]) ? -1 : 1;
        ans += sign * nums(s[i]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

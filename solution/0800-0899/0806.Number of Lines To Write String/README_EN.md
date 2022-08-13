# [806. Number of Lines To Write String](https://leetcode.com/problems/number-of-lines-to-write-string)

[中文文档](/solution/0800-0899/0806.Number%20of%20Lines%20To%20Write%20String/README.md)

## Description

<p>You are given a string <code>s</code> of lowercase English letters and an array <code>widths</code> denoting <strong>how many pixels wide</strong> each lowercase English letter is. Specifically, <code>widths[0]</code> is the width of <code>&#39;a&#39;</code>, <code>widths[1]</code> is the width of <code>&#39;b&#39;</code>, and so on.</p>

<p>You are trying to write <code>s</code> across several lines, where <strong>each line is no longer than </strong><code>100</code><strong> pixels</strong>. Starting at the beginning of <code>s</code>, write as many letters on the first line such that the total width does not exceed <code>100</code> pixels. Then, from where you stopped in <code>s</code>, continue writing as many letters as you can on the second line. Continue this process until you have written all of <code>s</code>.</p>

<p>Return <em>an array </em><code>result</code><em> of length 2 where:</em></p>

<ul>
	<li><code>result[0]</code><em> is the total number of lines.</em></li>
	<li><code>result[1]</code><em> is the width of the last line in pixels.</em></li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> widths = [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10], s = &quot;abcdefghijklmnopqrstuvwxyz&quot;
<strong>Output:</strong> [3,60]
<strong>Explanation:</strong> You can write s as follows:
abcdefghij  // 100 pixels wide
klmnopqrst  // 100 pixels wide
uvwxyz      // 60 pixels wide
There are a total of 3 lines, and the last line is 60 pixels wide.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> widths = [4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10], s = &quot;bbbcccdddaaa&quot;
<strong>Output:</strong> [2,4]
<strong>Explanation:</strong> You can write s as follows:
bbbcccdddaa  // 98 pixels wide
a            // 4 pixels wide
There are a total of 2 lines, and the last line is 4 pixels wide.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>widths.length == 26</code></li>
	<li><code>2 &lt;= widths[i] &lt;= 10</code></li>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> contains only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numberOfLines(self, widths: List[int], s: str) -> List[int]:
        last, row = 0, 1
        for c in s:
            w = widths[ord(c) - ord('a')]
            if last + w <= 100:
                last += w
            else:
                row += 1
                last = w
        return [row, last]
```

### **Java**

```java
class Solution {
    private static final int MAX_WIDTH = 100;

    public int[] numberOfLines(int[] widths, String s) {
        int last = 0, row = 1;
        for (char c : s.toCharArray()) {
            int w = widths[c - 'a'];
            if (last + w <= MAX_WIDTH) {
                last += w;
            } else {
                ++row;
                last = w;
            }
        }
        return new int[]{row, last};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int MAX_WIDTH = 100;

    vector<int> numberOfLines(vector<int>& widths, string s) {
        int last = 0, row = 1;
        for (char c : s) {
            int w = widths[c - 'a'];
            if (last + w <= MAX_WIDTH)
                last += w;
            else {
                ++row;
                last = w;
            }
        }
        return {row, last};
    }
};
```

### **Go**

```go
func numberOfLines(widths []int, s string) []int {
	last, row := 0, 1
	for _, c := range s {
		w := widths[c-'a']
		if last+w <= 100 {
			last += w
		} else {
			row++
			last = w
		}
	}
	return []int{row, last}
}
```

### **Rust**

```rust
impl Solution {
    pub fn number_of_lines(widths: Vec<i32>, s: String) -> Vec<i32> {
        let mut count = 1;
        let mut sum = 0;
        for c in s.as_bytes() {
            let width = widths[(c - b'a') as usize];
            if sum + width > 100 {
                sum = 0;
                count += 1;
            }
            sum += width;
        }
        vec![count, sum]
    }
}
```

### **...**

```

```

<!-- tabs:end -->

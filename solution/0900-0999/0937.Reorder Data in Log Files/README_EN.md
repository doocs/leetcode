# [937. Reorder Data in Log Files](https://leetcode.com/problems/reorder-data-in-log-files)

[中文文档](/solution/0900-0999/0937.Reorder%20Data%20in%20Log%20Files/README.md)

## Description

<p>You are given an array of <code>logs</code>. Each log is a space-delimited string of words, where the first word is the <strong>identifier</strong>.</p>

<p>There are two types of logs:</p>

<ul>
	<li><b>Letter-logs</b>: All words (except the identifier) consist of lowercase English letters.</li>
	<li><strong>Digit-logs</strong>: All words (except the identifier) consist of digits.</li>
</ul>

<p>Reorder these logs so that:</p>

<ol>
	<li>The <strong>letter-logs</strong> come before all <strong>digit-logs</strong>.</li>
	<li>The <strong>letter-logs</strong> are sorted lexicographically by their contents. If their contents are the same, then sort them lexicographically by their identifiers.</li>
	<li>The <strong>digit-logs</strong> maintain their relative ordering.</li>
</ol>

<p>Return <em>the final order of the logs</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> logs = [&quot;dig1 8 1 5 1&quot;,&quot;let1 art can&quot;,&quot;dig2 3 6&quot;,&quot;let2 own kit dig&quot;,&quot;let3 art zero&quot;]
<strong>Output:</strong> [&quot;let1 art can&quot;,&quot;let3 art zero&quot;,&quot;let2 own kit dig&quot;,&quot;dig1 8 1 5 1&quot;,&quot;dig2 3 6&quot;]
<strong>Explanation:</strong>
The letter-log contents are all different, so their ordering is &quot;art can&quot;, &quot;art zero&quot;, &quot;own kit dig&quot;.
The digit-logs have a relative order of &quot;dig1 8 1 5 1&quot;, &quot;dig2 3 6&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> logs = [&quot;a1 9 2 3 1&quot;,&quot;g1 act car&quot;,&quot;zo4 4 7&quot;,&quot;ab1 off key dog&quot;,&quot;a8 act zoo&quot;]
<strong>Output:</strong> [&quot;g1 act car&quot;,&quot;a8 act zoo&quot;,&quot;ab1 off key dog&quot;,&quot;a1 9 2 3 1&quot;,&quot;zo4 4 7&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= logs.length &lt;= 100</code></li>
	<li><code>3 &lt;= logs[i].length &lt;= 100</code></li>
	<li>All the tokens of <code>logs[i]</code> are separated by a <strong>single</strong> space.</li>
	<li><code>logs[i]</code> is guaranteed to have an identifier and at least one word after the identifier.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reorderLogFiles(self, logs: List[str]) -> List[str]:
        def cmp(x):
            a, b = x.split(' ', 1)
            return (0, b, a) if b[0].isalpha() else (1,)

        return sorted(logs, key=cmp)
```

### **Java**

```java
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, this::cmp);
        return logs;
    }

    private int cmp(String a, String b) {
        String[] t1 = a.split(" ", 2);
        String[] t2 = b.split(" ", 2);
        boolean d1 = Character.isDigit(t1[1].charAt(0));
        boolean d2 = Character.isDigit(t2[1].charAt(0));
        if (!d1 && !d2) {
            int v = t1[1].compareTo(t2[1]);
            return v == 0 ? t1[0].compareTo(t2[0]) : v;
        }
        if (d1 && d2) {
            return 0;
        }
        return d1 ? 1 : -1;
    }
}
```

### **TypeScript**

```ts
function reorderLogFiles(logs: string[]): string[] {
    const isDigit = (c: string) => c >= '0' && c <= '9';
    return logs.sort((a, b) => {
        const end1 = a[a.length - 1];
        const end2 = b[b.length - 1];
        if (isDigit(end1) && isDigit(end2)) {
            return 0;
        }
        if (isDigit(end1)) {
            return 1;
        }
        if (isDigit(end2)) {
            return -1;
        }
        const content1 = a.split(' ').slice(1).join(' ');
        const content2 = b.split(' ').slice(1).join(' ');
        if (content1 === content2) {
            return a < b ? -1 : 1;
        }
        return content1 < content2 ? -1 : 1;
    });
}
```

### **Rust**

```rust
impl Solution {
    pub fn reorder_log_files(mut logs: Vec<String>) -> Vec<String> {
        logs.sort_by(|s1, s2| {
            let (start1, content1) = s1.split_once(' ').unwrap();
            let (start2, content2) = s2.split_once(' ').unwrap();
            match (
                content1.chars().nth(0).unwrap().is_digit(10),
                content2.chars().nth(0).unwrap().is_digit(10),
            ) {
                (true, true) => std::cmp::Ordering::Equal,
                (true, false) => std::cmp::Ordering::Greater,
                (false, true) => std::cmp::Ordering::Less,
                (false, false) => content1.cmp(&content2).then(start1.cmp(&start2)),
            }
        });
        logs
    }
}
```

### **...**

```

```

<!-- tabs:end -->

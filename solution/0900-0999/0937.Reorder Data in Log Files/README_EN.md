---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0937.Reorder%20Data%20in%20Log%20Files/README_EN.md
tags:
    - Array
    - String
    - Sorting
---

<!-- problem:start -->

# [937. Reorder Data in Log Files](https://leetcode.com/problems/reorder-data-in-log-files)

[中文文档](/solution/0900-0999/0937.Reorder%20Data%20in%20Log%20Files/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Custom Sorting

We can use a custom sorting method to divide the logs into two categories: letter logs and digit logs.

For letter logs, we need to sort them according to the problem requirements, i.e., first by content and then by identifier.

For digit logs, we only need to maintain their original relative order.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Where $n$ is the number of logs.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reorderLogFiles(self, logs: List[str]) -> List[str]:
        def f(log: str):
            id_, rest = log.split(" ", 1)
            return (0, rest, id_) if rest[0].isalpha() else (1,)

        return sorted(logs, key=f)
```

#### Java

```java
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);

            boolean isLetter1 = Character.isLetter(split1[1].charAt(0));
            boolean isLetter2 = Character.isLetter(split2[1].charAt(0));

            if (isLetter1 && isLetter2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) {
                    return cmp;
                }
                return split1[0].compareTo(split2[0]);
            }

            return isLetter1 ? -1 : (isLetter2 ? 1 : 0);
        });

        return logs;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> reorderLogFiles(vector<string>& logs) {
        stable_sort(logs.begin(), logs.end(), [](const string& log1, const string& log2) {
            int idx1 = log1.find(' ');
            int idx2 = log2.find(' ');
            string id1 = log1.substr(0, idx1);
            string id2 = log2.substr(0, idx2);
            string content1 = log1.substr(idx1 + 1);
            string content2 = log2.substr(idx2 + 1);

            bool isLetter1 = isalpha(content1[0]);
            bool isLetter2 = isalpha(content2[0]);

            if (isLetter1 && isLetter2) {
                if (content1 != content2) {
                    return content1 < content2;
                }
                return id1 < id2;
            }

            return isLetter1 > isLetter2;
        });

        return logs;
    }
};
```

#### Go

```go
func reorderLogFiles(logs []string) []string {
	sort.SliceStable(logs, func(i, j int) bool {
		log1, log2 := logs[i], logs[j]
		idx1 := strings.IndexByte(log1, ' ')
		idx2 := strings.IndexByte(log2, ' ')
		id1, content1 := log1[:idx1], log1[idx1+1:]
		id2, content2 := log2[:idx2], log2[idx2+1:]

		isLetter1 := 'a' <= content1[0] && content1[0] <= 'z'
		isLetter2 := 'a' <= content2[0] && content2[0] <= 'z'

		if isLetter1 && isLetter2 {
			if content1 != content2 {
				return content1 < content2
			}
			return id1 < id2
		}

		return isLetter1 && !isLetter2
	})

	return logs
}
```

#### TypeScript

```ts
function reorderLogFiles(logs: string[]): string[] {
    return logs.sort((log1, log2) => {
        const [id1, content1] = log1.split(/ (.+)/);
        const [id2, content2] = log2.split(/ (.+)/);

        const isLetter1 = isNaN(Number(content1[0]));
        const isLetter2 = isNaN(Number(content2[0]));

        if (isLetter1 && isLetter2) {
            const cmp = content1.localeCompare(content2);
            if (cmp !== 0) {
                return cmp;
            }
            return id1.localeCompare(id2);
        }

        return isLetter1 ? -1 : isLetter2 ? 1 : 0;
    });
}
```

#### Rust

```rust
use std::cmp::Ordering;

impl Solution {
    pub fn reorder_log_files(logs: Vec<String>) -> Vec<String> {
        let mut logs = logs;

        logs.sort_by(|log1, log2| {
            let split1: Vec<&str> = log1.splitn(2, ' ').collect();
            let split2: Vec<&str> = log2.splitn(2, ' ').collect();

            let is_letter1 = split1[1].chars().next().unwrap().is_alphabetic();
            let is_letter2 = split2[1].chars().next().unwrap().is_alphabetic();

            if is_letter1 && is_letter2 {
                let cmp = split1[1].cmp(split2[1]);
                if cmp != Ordering::Equal {
                    return cmp;
                }
                return split1[0].cmp(split2[0]);
            }

            if is_letter1 {
                Ordering::Less
            } else if is_letter2 {
                Ordering::Greater
            } else {
                Ordering::Equal
            }
        });

        logs
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

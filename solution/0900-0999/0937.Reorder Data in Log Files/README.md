---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0937.Reorder%20Data%20in%20Log%20Files/README.md
tags:
    - 数组
    - 字符串
    - 排序
---

<!-- problem:start -->

# [937. 重新排列日志文件](https://leetcode.cn/problems/reorder-data-in-log-files)

[English Version](/solution/0900-0999/0937.Reorder%20Data%20in%20Log%20Files/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个日志数组 <code>logs</code>。每条日志都是以空格分隔的字串，其第一个字为字母与数字混合的<em> </em><strong>标识符 </strong>。</p>

<p>有两种不同类型的日志：</p>

<ul>
	<li><strong>字母日志</strong>：除标识符之外，所有字均由小写字母组成</li>
	<li><strong>数字日志</strong>：除标识符之外，所有字均由数字组成</li>
</ul>

<p>请按下述规则将日志重新排序：</p>

<ul>
	<li>所有 <strong>字母日志</strong> 都排在 <strong>数字日志</strong> 之前。</li>
	<li><strong>字母日志</strong> 在内容不同时，忽略标识符后，按内容字母顺序排序；在内容相同时，按标识符排序。</li>
	<li><strong>数字日志</strong> 应该保留原来的相对顺序。</li>
</ul>

<p>返回日志的最终顺序。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
<strong>输出：</strong>["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
<strong>解释：</strong>
字母日志的内容都不同，所以顺序为 "art can", "art zero", "own kit dig" 。
数字日志保留原来的相对顺序 "dig1 8 1 5 1", "dig2 3 6" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>logs = ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
<strong>输出：</strong>["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= logs.length <= 100</code></li>
	<li><code>3 <= logs[i].length <= 100</code></li>
	<li><code>logs[i]</code> 中，字与字之间都用 <strong>单个</strong> 空格分隔</li>
	<li>题目数据保证 <code>logs[i]</code> 都有一个标识符，并且在标识符之后至少存在一个字</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：自定义排序

我们可以使用自定义排序的方法，将日志分为两类：字母日志和数字日志。

对于字母日志，我们需要按照题目要求进行排序，即先按内容排序，再按标识符排序。

对于数字日志，我们只需要保留原来的相对顺序。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是日志的数量。

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

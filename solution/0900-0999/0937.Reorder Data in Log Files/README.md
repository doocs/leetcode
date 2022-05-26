# [937. 重新排列日志文件](https://leetcode.cn/problems/reorder-data-in-log-files)

[English Version](/solution/0900-0999/0937.Reorder%20Data%20in%20Log%20Files/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：自定义排序**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def reorderLogFiles(self, logs: List[str]) -> List[str]:
        def cmp(x):
            a, b = x.split(' ', 1)
            return (0, b, a) if b[0].isalpha() else (1,)

        return sorted(logs, key=cmp)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

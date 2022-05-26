# [1181. 前后拼接](https://leetcode.cn/problems/before-and-after-puzzle)

[English Version](/solution/1100-1199/1181.Before%20and%20After%20Puzzle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个「短语」列表&nbsp;<code>phrases</code>，请你帮忙按规则生成拼接后的「新短语」列表。</p>

<p>「短语」（phrase）是仅由小写英文字母和空格组成的字符串。「短语」的开头和结尾都不会出现空格，「短语」中的空格不会连续出现。</p>

<p>「前后拼接」（Before and After&nbsp;puzzles）是合并两个「短语」形成「新短语」的方法。我们规定拼接时，<strong>第一个短语的最后一个单词</strong> 和 <strong>第二个短语的第一个单词</strong> 必须相同。</p>

<p>返回每两个「短语」&nbsp;<code>phrases[i]</code>&nbsp;和&nbsp;<code>phrases[j]</code>（<code>i != j</code>）进行「前后拼接」得到的「新短语」。</p>

<p>注意，两个「短语」拼接时的顺序也很重要，我们需要同时考虑这两个「短语」。另外，同一个「短语」可以多次参与拼接，但「新短语」不能再参与拼接。</p>

<p>请你按字典序排列并返回「新短语」列表，列表中的字符串应该是 <strong>不重复的</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>phrases = [&quot;writing code&quot;,&quot;code rocks&quot;]
<strong>输出：</strong>[&quot;writing code rocks&quot;]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>phrases = [&quot;mission statement&quot;,
                &quot;a quick bite to eat&quot;,
&nbsp;               &quot;a chip off the old block&quot;,
&nbsp;               &quot;chocolate bar&quot;,
&nbsp;               &quot;mission impossible&quot;,
&nbsp;               &quot;a man on a mission&quot;,
&nbsp;               &quot;block party&quot;,
&nbsp;               &quot;eat my words&quot;,
&nbsp;               &quot;bar of soap&quot;]
<strong>输出：</strong>[&quot;a chip off the old block party&quot;,
&nbsp;     &quot;a man on a mission impossible&quot;,
&nbsp;     &quot;a man on a mission statement&quot;,
&nbsp;     &quot;a quick bite to eat my words&quot;,
      &quot;chocolate bar of soap&quot;]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>phrases = [&quot;a&quot;,&quot;b&quot;,&quot;a&quot;]
<strong>输出：</strong>[&quot;a&quot;]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= phrases.length &lt;= 100</code></li>
	<li><code>1 &lt;= phrases[i].length &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“哈希表 + 排序”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def beforeAndAfterPuzzles(self, phrases: List[str]) -> List[str]:
        same_first_word = defaultdict(set)
        for i, phrase in enumerate(phrases):
            same_first_word[phrase.split()[0]].add(i)
        res = set()
        for i, phrase in enumerate(phrases):
            words = phrase.split()
            last_word = words[-1]
            if last_word in same_first_word:
                for j in same_first_word[last_word]:
                    if i != j:
                        res.add(' '.join(words[:-1] + phrases[j].split()))
        return sorted(list(res))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> beforeAndAfterPuzzles(String[] phrases) {
        Map<String, Set<Integer>> sameFirstWord = new HashMap<>();
        for (int i = 0; i < phrases.length; ++i) {
            String phrase = phrases[i];
            String word = phrase.split(" ")[0];
            sameFirstWord.computeIfAbsent(word, k -> new HashSet<>()).add(i);
        }
        Set<String> res = new HashSet<>();
        for (int i = 0; i < phrases.length; ++i) {
            String phrase = phrases[i];
            String[] words = phrase.split(" ");
            String lastWord = words[words.length - 1];
            if (sameFirstWord.containsKey(lastWord)) {
                for (int j : sameFirstWord.get(lastWord)) {
                    if (i != j) {
                        List<String> t = new ArrayList<>();
                        for (int k = 0; k < words.length - 1; ++k) {
                            t.add(words[k]);
                        }
                        for (String word : phrases[j].split(" ")) {
                            t.add(word);
                        }
                        res.add(String.join(" ", t));
                    }
                }
            }
        }
        List<String> output = new ArrayList<>(res);
        Collections.sort(output);
        return output;
    }
}
```

### **...**

```

```

<!-- tabs:end -->

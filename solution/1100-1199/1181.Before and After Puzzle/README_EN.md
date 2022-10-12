# [1181. Before and After Puzzle](https://leetcode.com/problems/before-and-after-puzzle)

[中文文档](/solution/1100-1199/1181.Before%20and%20After%20Puzzle/README.md)

## Description

<p>Given a list of <code>phrases</code>, generate a list of&nbsp;Before and After puzzles.</p>

<p>A <em>phrase</em> is a string that consists of lowercase English letters and spaces only. No space appears in the start or the end of a phrase. There are&nbsp;no consecutive spaces&nbsp;in a phrase.</p>

<p><em>Before and After&nbsp;puzzles</em> are phrases that are formed by merging&nbsp;two phrases where the <strong>last&nbsp;word of the first&nbsp;phrase</strong> is the same as the <strong>first word of the second phrase</strong>.</p>

<p>Return the&nbsp;Before and After&nbsp;puzzles that can be formed by every two phrases&nbsp;<code>phrases[i]</code>&nbsp;and&nbsp;<code>phrases[j]</code>&nbsp;where&nbsp;<code>i != j</code>. Note that the order of matching two phrases matters, we want to consider both orders.</p>

<p>You should return a list of&nbsp;<strong>distinct</strong>&nbsp;strings <strong>sorted&nbsp;lexicographically</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> phrases = [&quot;writing code&quot;,&quot;code rocks&quot;]
<strong>Output:</strong> [&quot;writing code rocks&quot;]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> phrases = [&quot;mission statement&quot;,
                  &quot;a quick bite to eat&quot;,
&nbsp;                 &quot;a chip off the old block&quot;,
&nbsp;                 &quot;chocolate bar&quot;,
&nbsp;                 &quot;mission impossible&quot;,
&nbsp;                 &quot;a man on a mission&quot;,
&nbsp;                 &quot;block party&quot;,
&nbsp;                 &quot;eat my words&quot;,
&nbsp;                 &quot;bar of soap&quot;]
<strong>Output:</strong> [&quot;a chip off the old block party&quot;,
&nbsp;        &quot;a man on a mission impossible&quot;,
&nbsp;        &quot;a man on a mission statement&quot;,
&nbsp;        &quot;a quick bite to eat my words&quot;,
         &quot;chocolate bar of soap&quot;]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> phrases = [&quot;a&quot;,&quot;b&quot;,&quot;a&quot;]
<strong>Output:</strong> [&quot;a&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= phrases.length &lt;= 100</code></li>
	<li><code>1 &lt;= phrases[i].length &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

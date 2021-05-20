# [692. 前 K 个高频单词](https://leetcode-cn.com/problems/top-k-frequent-words)

[English Version](/solution/0600-0699/0692.Top%20K%20Frequent%20Words/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给一非空的单词列表，返回前&nbsp;<em>k&nbsp;</em>个出现次数最多的单词。</p>

<p>返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> [&quot;i&quot;, &quot;love&quot;, &quot;leetcode&quot;, &quot;i&quot;, &quot;love&quot;, &quot;coding&quot;], k = 2
<strong>输出:</strong> [&quot;i&quot;, &quot;love&quot;]
<strong>解析:</strong> &quot;i&quot; 和 &quot;love&quot; 为出现次数最多的两个单词，均为2次。
    注意，按字母顺序 &quot;i&quot; 在 &quot;love&quot; 之前。
</pre>

<p>&nbsp;</p>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入:</strong> [&quot;the&quot;, &quot;day&quot;, &quot;is&quot;, &quot;sunny&quot;, &quot;the&quot;, &quot;the&quot;, &quot;the&quot;, &quot;sunny&quot;, &quot;is&quot;, &quot;is&quot;], k = 4
<strong>输出:</strong> [&quot;the&quot;, &quot;is&quot;, &quot;sunny&quot;, &quot;day&quot;]
<strong>解析:</strong> &quot;the&quot;, &quot;is&quot;, &quot;sunny&quot; 和 &quot;day&quot; 是出现次数最多的四个单词，
    出现次数依次为 4, 3, 2 和 1 次。
</pre>

<p>&nbsp;</p>

<p><strong>注意：</strong></p>

<ol>
	<li>假定 <em>k</em> 总为有效值， 1 &le; <em>k</em> &le; 集合元素数。</li>
	<li>输入的单词均由小写字母组成。</li>
</ol>

<p>&nbsp;</p>

<p><strong>扩展练习：</strong></p>

<ol>
	<li>尝试以&nbsp;<em>O</em>(<em>n</em> log <em>k</em>) 时间复杂度和&nbsp;<em>O</em>(<em>n</em>) 空间复杂度解决。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def topKFrequent(self, words: List[str], k: int) -> List[str]:
        counter = collections.Counter(words)
        res = sorted(counter, key=lambda word: (-counter[word], word))
        return res[:k]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> counter = new HashMap<>();
        for (String word : words) {
            counter.put(word, counter.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> minHeap = new PriorityQueue<>((a, b) -> {
            if (counter.get(a).equals(counter.get(b))) {
                return b.compareTo(a);
            }
            return counter.get(a) - counter.get(b);
        });
        for (String word : counter.keySet()) {
            minHeap.offer(word);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        List<String> res = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            res.add(minHeap.poll());
        }
        Collections.reverse(res);
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
